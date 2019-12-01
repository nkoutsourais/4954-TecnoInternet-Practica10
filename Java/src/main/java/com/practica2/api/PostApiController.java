package com.practica2.api;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonView;
import com.practica2.models.Author;
import com.practica2.models.Comment;
import com.practica2.models.Post;
import com.practica2.repositories.AuthorRepository;
import com.practica2.repositories.CommentRepository;
import com.practica2.repositories.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostApiController {

    interface ViewPost extends Post.Full, Comment.Full, Author.Basic {}

    @Autowired
    PostRepository postRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    AuthorRepository authorRepository;

    @JsonView(Post.Basic.class)
    @GetMapping("/")
    public List<Post> getBlog() {
        return postRepository.findAll();
    }

    @JsonView(ViewPost.class)
    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPost(@PathVariable long postId) {
        Optional<Post> post = postRepository.findById(postId);
        if(!post.isPresent())
            return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(post.get());
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Post> newPost(@RequestBody Post post) {
        return new ResponseEntity<>(postRepository.save(post), HttpStatus.CREATED);
    }

    @JsonView(Comment.Full.class)
    @PostMapping("/{postId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Comment> newComment(@PathVariable long postId, @RequestBody Comment comment) {
        Optional<Post> post = postRepository.findById(postId);
        if(!post.isPresent())
            return new ResponseEntity<Comment>(HttpStatus.NOT_FOUND);
        if(comment.getAuthor() == null)
            return new ResponseEntity<Comment>(HttpStatus.BAD_REQUEST);
        Optional<Author> author = authorRepository.findById(comment.getAuthor().getId());
        if(!author.isPresent())
            return new ResponseEntity<Comment>(HttpStatus.BAD_REQUEST);
        comment.setPost(post.get());
        return new ResponseEntity<>(commentRepository.save(comment), HttpStatus.CREATED);
    }

    @JsonView(Comment.Full.class)
    @DeleteMapping("/{postId}/comments/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Comment> delComment(@PathVariable long postId, @PathVariable long commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if(!comment.isPresent())
            return new ResponseEntity<Comment>(HttpStatus.NOT_FOUND);
        if(comment.get().getPost().getId() != postId)
            return new ResponseEntity<Comment>(HttpStatus.BAD_REQUEST);
        commentRepository.delete(comment.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}