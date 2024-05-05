package com.example.minorproject.serviceImpl;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.minorproject.enums.AccountStatus;
import com.example.minorproject.enums.Genre;
import com.example.minorproject.enums.TransactionType;
import com.example.minorproject.exceptions.TransactionServiceException;
import com.example.minorproject.models.Book;
import com.example.minorproject.models.Student;
import com.example.minorproject.models.Transaction;
import com.example.minorproject.repository.TransactionRepositoryInterf;
import com.example.minorproject.service.BookServiceInterf;
import com.example.minorproject.service.StudentServiceInterf;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class TransactionServiceImplTest {

//    Diff between InjectMocks and Mock is that InjectMocks creates an actual object of that particular class
//    on top of which this annotation is written whereas Mock just creates a dummy object of type <class>$<MockitoMock>$--
//    and associates the mock with actual object creatd by InjectMockts

    @InjectMocks // similar to auto wired
    TransactionServiceImpl transactionService;

    @Mock
    StudentServiceInterf studentServiceInterf;

    @Mock
    BookServiceInterf bookServiceInterf;

    @Mock
    TransactionRepositoryInterf transactionRepositoryInterf;

    @Test
    public void transact_test() {
        int bookId = 1;
        int studentId = 1;
        String externalTransactionId = UUID.randomUUID().toString();

        Student student = Student.builder()
                .id(studentId).name("James")
                .email("studnet@gmail.com")
                .contact("9876567656")
                .accountStatus(AccountStatus.ACTIVE)
                .build();

        Book book = Book.builder()
                .id(bookId).name("Introduction to Java")
                .cost(300).genre(Genre.NON_FICTIONAL)
                .build();

        Book assignedBook = Book.builder()
                .id(bookId).name("Introduction to Java")
                .cost(300).genre(Genre.NON_FICTIONAL)
                .student(student)
                .build();

        Transaction transaction = Transaction.builder()
                .id(1)
                .externalId(externalTransactionId)
                .transactionType(TransactionType.ISSUE)
                .student(student)
                .book(book)
                .build();

        // stubbing
        when(studentServiceInterf.findByIdAndAccountStatus(anyInt(), any())).thenReturn(student);
        when(bookServiceInterf.findById(bookId)).thenReturn(Optional.ofNullable(book));
        when(transactionRepositoryInterf.save(any())).thenReturn(transaction);
        when(bookServiceInterf.save(any())).thenReturn(assignedBook);

        String transactionIdReturned = transactionService.transact(
                bookId, studentId, TransactionType.ISSUE.name());

        Assertions.assertEquals(externalTransactionId, transactionIdReturned);

        Mockito.verify(studentServiceInterf,times(1))
                .findByIdAndAccountStatus(anyInt(), any());
    }

    @Test
    public void transact_tansactionServiceException(){
        int bookId = 1;
        int studentId = 1;

        Student student = Student.builder()
                .id(studentId).name("James")
                .email("studnet@gmail.com")
                .contact("9876567656")
                .accountStatus(AccountStatus.ACTIVE)
                .build();

        // stubbing
        when(studentServiceInterf.findByIdAndAccountStatus(anyInt(), any())).thenReturn(student);
        when(bookServiceInterf.findById(bookId)).thenReturn(Optional.empty());

        Assertions.assertThrows(TransactionServiceException.class, () -> {
            transactionService.transact(
                    bookId, studentId, TransactionType.ISSUE.name());
        });
    }
}
