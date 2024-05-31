package com.example.SpringSecurity.controller;

import com.example.SpringSecurity.dto.ArticleHtFormDto;
import com.example.SpringSecurity.entity.ArticleHtEntity;
import com.example.SpringSecurity.entity.ArticleXwEntity;
import com.example.SpringSecurity.repository.ArticleHtRepository;
import com.example.SpringSecurity.repository.ArticleXwRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
public class FudaController {
    @Autowired
    private ArticleHtRepository articleHtRepository;

    @Autowired
    private ArticleXwRepository articleXwRepository;


    @GetMapping("/fuda/testing")
    public String niceMeetYou(Model model){
        model.addAttribute("username", "孚达客户");
        return "greetings";
    }

    @GetMapping("/fuda")                public String niceMeetYoufuda(){      return "fudas/fdshouye";    }
    @GetMapping("/fuda/jieshao")             public String dianjieshao(){          return "fudas/diancaidan/02fdjieshao";}
    @GetMapping("/fuda/02indexCleanBlog")             public String indexCleanBlog(){          return "fudas/diancaidan/02indexCleanBlog";}
    @GetMapping("/fuda/huanjijng")           public String huanjijng(){            return "fudas/diancaidan/03fdhuanjing";}
    @GetMapping("/fuda/chengyuan")           public String dianchengyuan(){        return "fudas/diancaidan/04fdchengyuan";}
    @GetMapping("/fuda/anli")                public String diananli(){             return "fudas/diancaidan/05fdanli";}
    @GetMapping("/fuda/dangjiantuanjian")    public String diandangjiantuanjian(){ return "fudas/diancaidan/07fddangjiantuanjian";}
    @GetMapping("/fuda/lianxiwomen")         public String dianlianxiwomen(){      return "fudas/diancaidan/08lianxiwomen";}
    @GetMapping("/fuda/new")        public String newP(){ return "fudas/diancaidan/09new"; } /*CRUD*/

    @PostMapping("/fuda/fdarticles/create")
    public String createFuda(ArticleHtFormDto dto){
        ArticleHtEntity data = new ArticleHtEntity();
        data.setXingming(dto.getXingming());
        data.setShoujihao(dto.getShoujihao());
        data.setSweixin(dto.getSweixin());
        data.setYouxiang(dto.getYouxiang());
        data.setAnjiangaishu(dto.getAnjiangaishu());

        data.setRole("ROLE_USER");

        ArticleHtEntity saved = articleHtRepository.save(data);

        return "redirect:/fuda/new";
    }

    @GetMapping("/fuda/xinwen")
    public String fudaxinwenP(Model model){
        // 1. 모든 article 을 가져온다
        List<ArticleXwEntity> articleXwEntityList = (List<ArticleXwEntity>) articleXwRepository.findAll();

        // 2: 가져온 Article 를 묶음을 뷰로 전달한다, 모델을 사용한다 , entity > model 에 데이터 등록
        model.addAttribute("articleXwEntityList", articleXwEntityList);

        // 3. 뷰 페이지를 설정 !
//        return "articlesxw/articles"; // articles/index.mustache
        return "fudas/diancaidan/06fdxinwen"; // articles/index.mustache
    }

    @GetMapping("/fuda/xinwen/{id}")
    public String showxinwen(@PathVariable("id") Long id, Model model){
        // 1. id 로 DB entity 데이터를 가져옴
        // 데이터가 없을때 null 처리 한다
        ArticleXwEntity articleXwEntityList  = articleXwRepository.findById(id).orElse(null);

        // 2. 가져온 entity 데이터를 모델에 등록
        model.addAttribute("articleXwEntityList", articleXwEntityList);

        // 3. 보여준 페이지를 설정 !
        return "fudas/diancaidan/006fdxinwenshow";
    }



    @GetMapping("/fuda/caidan")
    public String indexCaidan(){
        return "fudas/caidan/indexCaidan";
    }


    @GetMapping("/fuda/indexBusinessFromt")
    public String indexBusinessFromt(){
        return "fudas/caidan/indexBusinessFromt";
    }


}

