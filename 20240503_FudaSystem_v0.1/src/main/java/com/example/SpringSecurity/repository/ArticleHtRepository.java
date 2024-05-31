package com.example.SpringSecurity.repository;


import com.example.SpringSecurity.entity.ArticleHtEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleHtRepository extends CrudRepository<ArticleHtEntity,Long> {

    @Override
    ArrayList<ArticleHtEntity> findAll();

}
