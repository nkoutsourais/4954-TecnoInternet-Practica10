package com.practica2.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonView;


@Entity
public class Author {

    public interface Basic {
	}

	public interface Extended {
	}

	public interface Full extends Basic, Extended {
	}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Basic.class)
    private long id;
    @JsonView(Basic.class)
    private String name;
    @JsonView(Extended.class)
    private int age;

    public long getId() {
		return id;
	}

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}