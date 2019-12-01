package com.practica2.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Post {

    public interface Mini {
	}

    public interface Basic extends Mini {
	}

	public interface Extended {
	}

	public interface Full extends Basic, Extended {
	}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Mini.class)
    private long id;
    @JsonView(Basic.class)
    private String title;
    @JsonView(Extended.class)
    private String content;
    @JsonView(Extended.class)
    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}