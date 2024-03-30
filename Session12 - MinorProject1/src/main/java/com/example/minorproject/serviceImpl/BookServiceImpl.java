package com.example.minorproject.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
