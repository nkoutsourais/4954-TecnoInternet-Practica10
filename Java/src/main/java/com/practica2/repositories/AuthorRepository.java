package com.practica2.repositories;

import com.practica2.models.Author;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long>
{
}