package com.example.SpringSecurity.service;

import com.example.SpringSecurity.dto.ArticleHtFormDto;
import com.example.SpringSecurity.entity.ArticleHtEntity;
import com.example.SpringSecurity.repository.ArticleHtRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service // 서비스 선언! (서비스 객체를 스프링부트에 생성)
@Slf4j
public class ArticleHtService {
    @Autowired
    private ArticleHtRepository articleHtRepository;
    public void insertProcess(ArticleHtFormDto dto){

        ArticleHtEntity data = new ArticleHtEntity();

        data.setXingming(dto.getXingming());
        data.setShoujihao(dto.getShoujihao());
        data.setSweixin(dto.getSweixin());
        data.setYouxiang(dto.getYouxiang());
        data.setAnjiangaishu(dto.getAnjiangaishu());

        data.setRole("ROLE_USER");

        articleHtRepository.save(data);
    }
}
