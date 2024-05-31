package com.example.SpringSecurity.controller;

import com.example.SpringSecurity.dto.ArticleHtFormDto;
import com.example.SpringSecurity.entity.ArticleHtEntity;
import com.example.SpringSecurity.repository.ArticleHtRepository;
import com.example.SpringSecurity.service.NewService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j // logging 을 위한 골뱅이 어노테이션
public class ArticleHTController {

    @Autowired
    private ArticleHtRepository articleHtRepository;
    @Autowired
    private NewService newService;

    @GetMapping("/articlesht/new")
    public String newArticleForm(){
        return "/articlesht/new";
    }

    @PostMapping("/articlesht/create")
    public String createArticle(ArticleHtFormDto dto) {

        ArticleHtEntity data = new ArticleHtEntity();
        data.setXingming(dto.getXingming());
        data.setShoujihao(dto.getShoujihao());
        data.setSweixin(dto.getSweixin());
        data.setYouxiang(dto.getYouxiang());
        data.setAnjiangaishu(dto.getAnjiangaishu());

        data.setRole("ROLE_USER");

        ArticleHtEntity saved = articleHtRepository.save(data);

        return "redirect:/articlesht/" +  saved.getId();
    }


    @GetMapping("/articlesht/{id}")
    public String show(@PathVariable("id") Long id, Model model){
        log.info("id =" + id);

        // 1. id 로 DB entity 데이터를 가져옴
        // 데이터가 없을때 null 처리 한다
        ArticleHtEntity actentity =articleHtRepository.findById(id).orElse(null);

        // 2. 가져온 entity 데이터를 모델에 등록
        model.addAttribute("actentity", actentity);

        // 3. 보여준 페이지를 설정 !
        return "articlesht/show";
    }

    @GetMapping("/articlesht")
    public String redirectIndex(){ return "redirect:/index";}


    @GetMapping("/index")
    public String articleshtP(Model model){
        // 1. 모든 article 을 가져온다
        List<ArticleHtEntity> articleHtEntityList = (List<ArticleHtEntity>) articleHtRepository.findAll();

        // 2: 가져온 Article 를 묶음을 뷰로 전달한다, 모델을 사용한다 , entity > model 에 데이터 등록
        model.addAttribute("articleHtEntityList", articleHtEntityList);

        // 3. 뷰 페이지를 설정 !
//        return "articlesht/articles"; // articles/index.mustache
        return "index"; // articles/index.mustache
    }


    @GetMapping("/articlesht/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model){

        // 수정할 데이터를 가져와야 한다 , db repository 로 부터
//        Article articleEntity = articleRepository.findById(id).orElse(null);
        ArticleHtEntity articleHtEntity = articleHtRepository.findById(id).orElse(null);

        // 모델에 데이터를 등록
        model.addAttribute("articleHtEntity" , articleHtEntity);

        // 뷰 페이지 설정
        return "articlesht/edit";
    }

    @PostMapping("/articlesht/update")
    public String update(ArticleHtFormDto dto, HttpServletRequest rq ){
        // 1: dto > entity 로 변환

        ArticleHtEntity data = new ArticleHtEntity();

        data.setId(Long.valueOf(rq.getParameter("id")));
        data.setXingming(dto.getXingming());
        data.setShoujihao(dto.getShoujihao());
        data.setSweixin(dto.getSweixin());
        data.setYouxiang(dto.getYouxiang());
        data.setAnjiangaishu(dto.getAnjiangaishu());
        data.setRole("ROLE_USER");

        if (data.getId() != null){
            articleHtRepository.save(data); // entity 가 db  로 갱신 됨
        }
        // 3: 수정결과 페이지로 redirect 한다
        return "redirect:/articlesht/" + data.getId();

    }

    @GetMapping("/articlesht/{id}/delete")
    public String delete(@PathVariable("id") Long id, RedirectAttributes rttr){
        log.info("삭제 요청이 들어 왓습니다.");

        // 1: 삭제 대상을 가져온다 .
        ArticleHtEntity target = articleHtRepository.findById(id).orElse(null);
        log.info(target.toString());

        // 2: 그 대상을 삭제 한다 .
        if(target != null){
            articleHtRepository.delete(target);
            // 한번쓰고 사라지는 휘발성 데이터, alerts
            rttr.addFlashAttribute("msg", "该数据已删除");
        }

        // 3: 결과 페이지를 redirect 한다.
        return "redirect:/articlesht";
    }

}
