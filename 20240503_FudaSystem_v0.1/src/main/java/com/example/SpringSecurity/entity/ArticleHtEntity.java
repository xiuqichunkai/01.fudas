package com.example.SpringSecurity.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity // 해당 클래스로 테이블을 만든다 .
@Getter
@Setter
public class ArticleHtEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String xingming;
    private String shoujihao;
    private String sweixin;
    private String youxiang;
    private String anjiangaishu;

    private String role;

}
