package com.practica2.repositories;

import com.practica2.models.Post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>
{
}