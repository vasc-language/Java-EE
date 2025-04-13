package org.example.jd20250410mybatisdemo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.jd20250410mybatisdemo.model.ArticleInfo;

@Mapper
public interface ArticleInfoMapper {
    // 多表联合查询
    ArticleInfo selectArticleInfo(Integer id);
}
