package org.example.springblogdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.example.springblogdemo.mapper.BlogInfoMapper;
import org.example.springblogdemo.pojo.dataobject.BlogInfo;
import org.example.springblogdemo.pojo.response.BlogInfoResponse;
import org.example.springblogdemo.service.BlogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-05
 * Time: 10:39
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogInfoMapper blogInfoMapper;
    @Override
    public List<BlogInfoResponse> getList() {
        QueryWrapper<BlogInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(BlogInfo::getDeleteFlag, 0); // 判断是否等于 0，表示未被逻辑删除的博客
        // select * from blog_info where delete_flag = 0
        List<BlogInfo> blogInfos = blogInfoMapper.selectList(queryWrapper); // 查询语句

        /**
         * 将 BlogInfo 的属性进行二次封装，变成 BlogInfoResponse
         * - 使用 Java8 的 Stream API 将 BlogInfo 对象转换为 BlogInfoResponse 对象
         * - .stream().map(...).collect(Collectors.toList())：对列表中的每个元素进行转换并收集结果
         * - 转换的过程中：
         * - 创建新的 BlogInfoResponse 对象
         *   - 使用 BeanUtils.copyProperties(blogInfo, response) 将 BlogInfo 的属性值复制到 BlogInfoResponse
         *   - BeanUtils.copyProperties 是 Spring 提供的的工具方法，会自动匹配同名属性值并复制值
         *   - 这种方法有效分离数据库实体类和 API 响应对象，保护敏感并按照前端需求格式化数据
         */
        List<BlogInfoResponse> blogInfoResponses = blogInfos.stream().map(blogInfo -> {
            BlogInfoResponse response = new BlogInfoResponse();
            BeanUtils.copyProperties(blogInfo, response); // Object source, Object target
            return response;
        }).collect(Collectors.toList());

        return blogInfoResponses;
    }
}
