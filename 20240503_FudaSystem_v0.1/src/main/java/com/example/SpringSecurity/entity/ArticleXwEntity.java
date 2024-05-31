package com.example.SpringSecurity.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity // 해당 클래스로 테이블을 만든다 .
@Getter
@Setter
public class ArticleXwEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String xingming;
    @Column(length = 10000)
    private String anjiangaishu;
    @Column
    private String role;
}
