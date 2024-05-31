package com.example.SpringSecurity.repository;


import com.example.SpringSecurity.entity.ArticleHtEntity;
import com.example.SpringSecurity.entity.ArticleXwEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleXwRepository extends CrudRepository<ArticleXwEntity,Long> {

    @Override
    ArrayList<ArticleXwEntity> findAll();

}
