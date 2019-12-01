package com.practica2.api;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonView;
import com.practica2.models.Author;
import com.practica2.models.Comment;
import com.practica2.repositories.AuthorRepository;
import com.practica2.repositories.CommentRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authors")
public class AuthorApiController {

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    CommentRepository commentRepository;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Author> newAuthor(@RequestBody Author author) {
        return new ResponseEntity<>(authorRepository.save(author), HttpStatus.CREATED);
    }

    @JsonView(Comment.BasicWithPost.class)
    @GetMapping("/{authorId}/comments")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Comment>> getComments(@PathVariable long authorId) {
        Optional<Author> author = authorRepository.findById(authorId);
        if(!author.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(commentRepository.findByAuthor(author.get()));
    }
}