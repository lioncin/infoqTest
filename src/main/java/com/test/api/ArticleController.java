package com.test.api;

import com.test.service.ArticleService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Add by jianhan on 2017/12/28
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    private Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;

    @ApiOperation(value = "获取全部文章")
    @GetMapping
    public String getArticles(@RequestParam("type") String type) {
        logger.info("try to get articles ");
        return articleService.getArticles(type);
    }

    @ApiOperation(value = "根据id获取文章")
    @GetMapping("/{id}")
    public String getArticleById(@PathVariable("id") int id){
        return "";
    }

    @ApiOperation("保存文章")
    @PostMapping
    public String saveArticle(@RequestBody String articleDTO){
        return "";
    }
}
