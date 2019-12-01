package com.practica2.repositories;

import java.util.List;

import com.practica2.models.Author;
import com.practica2.models.Comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>
{
    List<Comment> findByAuthor(Author author);
}