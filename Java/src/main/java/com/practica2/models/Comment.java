package com.practica2.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Comment {

    public interface Basic {
	}

	public interface Extended extends Author.Basic {
	}

	public interface Full extends Basic, Extended {
    }
    
    public interface BasicWithPost extends Basic, Post.Mini {
	}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Basic.class)
    private long id;
    @JsonView(Basic.class)
    private String comment;
    @OneToOne
    @JsonView(Extended.class)
    private Author author;
    @ManyToOne
    @JsonView(BasicWithPost.class)
    private Post post;

    public long getId() {
		return id;
	}

    public String getComment() {
        return comment;
    }

    public Author getAuthor() {
        return author;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}