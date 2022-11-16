package com.wildcodeschool.books.repository;

import com.wildcodeschool.books.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRespository extends JpaRepository<Book, Integer> {

    List<Book> findByTitleOrAuthorOrDescriptionContaining(String text, String textAgain, String needAnotherOne);
}
