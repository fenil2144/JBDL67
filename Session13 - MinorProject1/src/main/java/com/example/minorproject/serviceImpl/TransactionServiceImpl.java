package com.example.minorproject.serviceImpl;

import jakarta.validation.Valid;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.minorproject.enums.AccountStatus;
import com.example.minorproject.enums.TransactionType;
import com.example.minorproject.exceptions.TransactionServiceException;
import com.example.minorproject.models.Book;
import com.example.minorproject.models.Student;
import com.example.minorproject.models.Transaction;
import com.example.minorproject.repository.StudentRespositoryInterf;
import com.example.minorproject.repository.TransactionRepositoryInterf;
import com.example.minorproject.service.BookServiceInterf;
import com.example.minorproject.service.StudentServiceInterf;
import com.example.minorproject.service.TransactionServiceInterf;

@Service
public class TransactionServiceImpl implements TransactionServiceInterf {

    @Autowired
    StudentServiceInterf studentServiceInterf;

    @Autowired
    BookServiceInterf bookServiceInterf;

    @Autowired
    TransactionRepositoryInterf transactionRepositoryInterf;

    @Value("${book.return.due_date}")
    int maxAllowedDays;


    @Override
    public String transact(int studentId, int bookId, String transactionType) {

//        Issue
//        1) Student is Valid and Active
//        2) Book is Valid
//        3) Book is Available
//        4) Make the Transaction
//        5) Make the Book Unavailable

//        Return
//        1) Student is Valid and Active
//        2) Book is Valid
//        3) Book is issued to this student only
//        4) Calculate Fine if any
//        5) Make the Transaction
//        6) Make the Book Available

        Student student = studentServiceInterf.findByIdAndAccountStatus(studentId, AccountStatus.ACTIVE);

        if(student == null){
            throw new TransactionServiceException("Student is not valid");
        }

        Optional<Book> book = bookServiceInterf.findById(bookId);
        if(book.isEmpty()){
            throw new TransactionServiceException("Book is not valid");
        }

        //Issue Transaction
        if(transactionType.equalsIgnoreCase(TransactionType.ISSUE.toString())){

            if(book.get().getStudent() != null){
                throw new TransactionServiceException("Book is not available");
            }

            Transaction transaction = Transaction.builder()
                    .externalId(UUID.randomUUID().toString())
                    .transactionType(TransactionType.ISSUE)
                    .payment(book.get().getCost())
                    .book(book.get()).student(student)
                    .build();

            transactionRepositoryInterf.save(transaction);

            //Make Book Unavailable
            book.get().setStudent(student);
            bookServiceInterf.save(book.get());

            return transaction.getExternalId();
        }
        // Return Transaction
        else if(transactionType.equalsIgnoreCase(TransactionType.RETURN.toString())){

            if(book.get().getStudent() == null){
                throw new TransactionServiceException("Book is not issued to any student");
            }

            if(book.get().getStudent().getId() != studentId){
                throw new TransactionServiceException("Book is not issued to this student");
            }

            //select top 1 from transaction where book_id=bookId and student_id=studentId and transaction_type=0 order by transaction_date desc
            Transaction issueTxn = transactionRepositoryInterf
                    .findTopByBookAndStudentAndTransactionTypeOrderByIdDesc(book.get(),student,TransactionType.ISSUE);

            // 400 , 390, 400
            Transaction transaction = Transaction.builder()
                    .externalId(UUID.randomUUID().toString())
                    .transactionType(TransactionType.RETURN)
                    .payment(book.get().getCost()-calculateFine(issueTxn))
                    .book(book.get()).student(student)
                    .build();

            transactionRepositoryInterf.save(transaction);

            //Make Book Available
            book.get().setStudent(null);
            bookServiceInterf.save(book.get());

            return transaction.getExternalId();
        }
        return null;
    }

    private double calculateFine(Transaction issueTransaction){
        long issueTime = issueTransaction.getCreatedOn().getTime();
        long returnTime = System.currentTimeMillis();

        long diff = returnTime-issueTime;

        long daysPassed = TimeUnit.DAYS.convert(diff,TimeUnit.MILLISECONDS);
        if(daysPassed > maxAllowedDays){
            return ((daysPassed-maxAllowedDays)*10);
        }
        return 0.0;
    }
}
