package com.example.minorproject.serviceImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.minorproject.enums.BookFilterType;
import com.example.minorproject.enums.Genre;
import com.example.minorproject.models.Author;
import com.example.minorproject.models.Book;
import com.example.minorproject.repository.BookRepositoryInterf;
import com.example.minorproject.requests.BookCreateRequest;
import com.example.minorproject.service.AuthorServiceInterf;
import com.example.minorproject.service.BookServiceInterf;

@Service
public class BookServiceImpl implements BookServiceInterf {

    @Autowired
    BookRepositoryInterf bookRepositoryInterf;

    @Autowired
    AuthorServiceInterf authorServiceInterf;

    @Autowired
    EntityManager entityManager;

    @Override
    public Book saveBook(BookCreateRequest bookCreateRequest) {
        Book book = bookCreateRequest.toBook();

        //Logic to check if book is duplicate

        Author author = book.getAuthor();

        //Logic to check if Author is duplicate
        Author authorFromDb = authorServiceInterf.findByEmail(author.getEmail());

        if(authorFromDb == null){
            authorFromDb = authorServiceInterf.saveAuthor(author);
        }

        book.setAuthor(authorFromDb);
        return bookRepositoryInterf.save(book);
    }

    @Override
    public List<Book> findBooks(BookFilterType bookFilterType, String value) {

        switch(bookFilterType) {
            case NAME:
                return bookRepositoryInterf.findByName(value);
            case AUTHOR_NAME:
                return bookRepositoryInterf.findByAuthor_name(value);
            case GENRE:
                return bookRepositoryInterf.findByGenre(Genre.valueOf(value));
            case COST:
                return bookRepositoryInterf.findByCost(Integer.parseInt(value));
            case ID:
                return bookRepositoryInterf.findAllById(new ArrayList<Integer>(Integer.parseInt(value)));
            default:
                return new ArrayList<Book>();

        }
    }

    @Override
    public Optional<Book> findById(Integer id) {
        return bookRepositoryInterf.findById(id);
    }

    @Override
    public Book save(Book book) {
        return bookRepositoryInterf.save(book);
    }

    public List<Book> getBooksByCriteria(BookCreateRequest bookCreateRequest){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);

        Root<Book> book = cq.from(Book.class);
        List<Predicate> predicates = new ArrayList<>();

        // select * from book where name like %java%
        if(bookCreateRequest.getName() != null){
            predicates.add(cb.like(book.get("name"),"%"+bookCreateRequest.getName()+"%"));
        }

        if(bookCreateRequest.getCost() != 0){
            predicates.add(cb.ge(book.get("cost"),bookCreateRequest.getCost()));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(cq).getResultList();
    }
}
