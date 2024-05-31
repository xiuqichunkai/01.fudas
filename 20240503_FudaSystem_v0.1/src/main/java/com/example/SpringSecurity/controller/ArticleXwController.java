package com.example.SpringSecurity.controller;

import com.example.SpringSecurity.dto.ArticleHtFormDto;
import com.example.SpringSecurity.dto.ArticleXwFormDto;
import com.example.SpringSecurity.entity.ArticleHtEntity;
import com.example.SpringSecurity.entity.ArticleXwEntity;
import com.example.SpringSecurity.repository.ArticleXwRepository;
import com.example.SpringSecurity.service.NewService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j // logging 을 위한 골뱅이 어노테이션
public class ArticleXwController {

    @Autowired
    private ArticleXwRepository articleXwRepository;

    @Autowired
    private NewService newService;

    @GetMapping("/articlesxw/new")
    public String newArticleForm(){
        return "/articlesxw/new";
    }

    @PostMapping("/articlesxw/create")
    public String createArticle(ArticleXwFormDto dto) {

        ArticleXwEntity data = new ArticleXwEntity();

        data.setXingming(dto.getXingming());
        data.setAnjiangaishu(dto.getAnjiangaishu());

        data.setRole("ROLE_USER");
//        ArticleXwEntity saved = articleHtRepository.save(data);
        ArticleXwEntity saved = articleXwRepository.save(data);

        return "redirect:/articlesxw/" +  saved.getId();
    }

    @GetMapping("/articlesxw/{id}")
    public String show(@PathVariable("id") Long id, Model model){

        // 1. id 로 DB entity 데이터를 가져옴
        // 데이터가 없을때 null 처리 한다
        ArticleXwEntity actentity  = articleXwRepository.findById(id).orElse(null);

        // 2. 가져온 entity 데이터를 모델에 등록
        model.addAttribute("actentity", actentity);

        // 3. 보여준 페이지를 설정 !
        return "articlesxw/show";
    }
//    @GetMapping("/articlesxw")
//    public String redirectIndex(){ return "redirect:/index02";}

    @GetMapping("/articlesxw/bianxie")
    public String articlesxwbxP(Model model){
        // 1. 모든 article 을 가져온다
//        List<ArticleXwEntity> articleXwEntityList = (List<ArticleXwEntity>) articleXwRepository.findAll();
//
//        // 2: 가져온 Article 를 묶음을 뷰로 전달한다, 모델을 사용한다 , entity > model 에 데이터 등록
//        model.addAttribute("articleXwEntityList", articleXwEntityList);

        // 3. 뷰 페이지를 설정 !
//        return "articlesxw/articles"; // articles/index.mustache
        return "index03"; // articles/index.mustache
    }
    @GetMapping("/articlesxw")
    public String articlesxwP(Model model){
        // 1. 모든 article 을 가져온다
        List<ArticleXwEntity> articleXwEntityList = (List<ArticleXwEntity>) articleXwRepository.findAll();

        // 2: 가져온 Article 를 묶음을 뷰로 전달한다, 모델을 사용한다 , entity > model 에 데이터 등록
        model.addAttribute("articleXwEntityList", articleXwEntityList);

        // 3. 뷰 페이지를 설정 !
//        return "articlesxw/articles"; // articles/index.mustache
        return "index02"; // articles/index.mustache
    }

    @GetMapping("/articlesxw/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model){

        // 수정할 데이터를 가져와야 한다 , db repository 로 부터
//        Article articleEntity = articleRepository.findById(id).orElse(null);
        ArticleXwEntity articleXwEntity = articleXwRepository.findById(id).orElse(null);

        // 모델에 데이터를 등록
        model.addAttribute("articleXwEntity" , articleXwEntity);

        // 뷰 페이지 설정
        return "articlesxw/edit";
    }

    @PostMapping("/articlesxw/update")
    public String update(ArticleXwFormDto dto, HttpServletRequest rq ){
        // 1: dto > entity 로 변환

        ArticleXwEntity data = new ArticleXwEntity();

        data.setId(Long.valueOf(rq.getParameter("id")));
        data.setXingming(dto.getXingming());
        data.setAnjiangaishu(dto.getAnjiangaishu());
        data.setRole("ROLE_USER");

        if (data.getId() != null){
            articleXwRepository.save(data); // entity 가 db  로 갱신 됨
        }
        // 3: 수정결과 페이지로 redirect 한다
        return "redirect:/articlesxw/" + data.getId();

    }


    @GetMapping("/articlesxw/{id}/delete")
    public String delete(@PathVariable("id") Long id, RedirectAttributes rttr){
        log.info("삭제 요청이 들어 왓습니다.");

        // 1: 삭제 대상을 가져온다 .
        ArticleXwEntity target = articleXwRepository.findById(id).orElse(null);
        log.info(target.toString());

        // 2: 그 대상을 삭제 한다 .
        if(target != null){
            articleXwRepository.delete(target);
            // 한번쓰고 사라지는 휘발성 데이터, alerts
            rttr.addFlashAttribute("msg", "该数据已删除");
        }

        // 3: 결과 페이지를 redirect 한다.
        return "redirect:/articlesxw";
    }

}

