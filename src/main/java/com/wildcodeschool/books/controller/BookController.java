package com.wildcodeschool.books.controller;

import com.wildcodeschool.books.entity.Book;
import com.wildcodeschool.books.repository.BookRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

    @RestController
    public class BookController {

        @Autowired
        BookRespository bookRespository;

        @GetMapping("/books")
        public List<Book> index(){
            return bookRespository.findAll();
        }

        @GetMapping("/books/{id}")
        public Book show(@PathVariable int id){
            return bookRespository.findById(id).get();
        }

        @PostMapping("/books/search")
        public List<Book> search(@RequestBody Map<String, String> body){
            String searchTerm = body.get("text");
            return bookRespository.findByTitleOrAuthorOrDescriptionContaining(searchTerm, searchTerm, searchTerm);
        }

        @PostMapping("/books")
        public Book create(@RequestBody Book book){
            return bookRespository.save(book);
        }

        @PutMapping("/books/{id}")
        public Book update(@PathVariable int id, @RequestBody Book book){
            // getting book
            Book bookToUpdate = bookRespository.findById(id).get();
            bookToUpdate.setTitle(book.getTitle());
            bookToUpdate.setAuthor(book.getAuthor());
            bookToUpdate.setDescription(book.getDescription());
            return bookRespository.save(bookToUpdate);
        }

        @DeleteMapping("books/{id}")
        @ResponseBody
        public String delete(@PathVariable int id){
            bookRespository.deleteById(id);
            return "is deleted";
        }

    }
