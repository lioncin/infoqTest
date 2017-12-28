package com.test.serviceImpl;

import com.test.dao.master.ArticlesMapper;
import com.test.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Add by jianhan on 2017/12/28
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticlesMapper articlesMapper;

    @Override
    public String getArticles(String type){
        return articlesMapper.getArticles(type);
    }
}
