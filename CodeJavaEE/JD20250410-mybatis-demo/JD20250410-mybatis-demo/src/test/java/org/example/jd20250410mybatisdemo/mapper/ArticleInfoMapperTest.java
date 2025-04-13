package org.example.jd20250410mybatisdemo.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import org.example.jd20250410mybatisdemo.model.ArticleInfo;

/**
 * ArticleInfoMapper测试类
 * 用于测试ArticleInfoMapper中的方法
 */
@SpringBootTest
class ArticleInfoMapperTest {
    @Autowired
    private ArticleInfoMapper articleInfoMapper;

    /**
     * 测试selectArticleInfo方法
     * 该方法用于查询文章信息，包括关联的用户信息
     */
    @Test
    void testSelectArticleInfo() {
        // 查询ID为1的文章
        Integer articleId = 1;
        
        // 调用mapper方法
        ArticleInfo articleInfo = articleInfoMapper.selectArticleInfo(articleId);
        
        // 输出查询结果
        System.out.println("文章信息: " + articleInfo);
        
        // 添加断言验证结果
        assertNotNull(articleInfo, "应该能找到ID为" + articleId + "的文章");
        assertEquals(articleId, articleInfo.getId(), "文章ID应该匹配");
        
        // 验证关联信息
        assertNotNull(articleInfo.getTitle(), "文章标题不应为空");
        assertNotNull(articleInfo.getContent(), "文章内容不应为空");
    }
} 