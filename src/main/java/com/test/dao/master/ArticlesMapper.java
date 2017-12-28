package com.test.dao.master;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticlesMapper {

    String getArticles(@Param("type") String type);
}
