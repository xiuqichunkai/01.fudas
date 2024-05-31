package com.example.SpringSecurity.service;

import com.example.SpringSecurity.dto.ArticleHtFormDto;
import com.example.SpringSecurity.dto.ArticleXwFormDto;
import com.example.SpringSecurity.entity.ArticleHtEntity;
import com.example.SpringSecurity.entity.ArticleXwEntity;
import com.example.SpringSecurity.repository.ArticleHtRepository;
import com.example.SpringSecurity.repository.ArticleXwRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service // 서비스 선언! (서비스 객체를 스프링부트에 생성)
@Slf4j
public class ArticleXwService {

    @Autowired
    private ArticleXwRepository articleXwRepository;
    public void insertsProcess(ArticleXwFormDto dto){

        ArticleXwEntity data = new ArticleXwEntity();

        data.setXingming(dto.getXingming());
        data.setAnjiangaishu(dto.getAnjiangaishu());
        data.setRole("ROLE_USER");

        articleXwRepository.save(data);
    }
}
