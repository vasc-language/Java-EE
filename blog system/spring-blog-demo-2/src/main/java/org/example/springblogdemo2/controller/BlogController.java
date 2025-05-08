package org.example.springblogdemo2.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.springblogdemo2.pojo.response.BlogInfoResponse;
import org.example.springblogdemo2.service.BlogService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Date: 2025-05-08
 * Time: 21:36
 */
@Slf4j
@RequestMapping("/blog")
@RestController
public class BlogController {
    @Resource(name = "blogServiceImpl")
    private BlogService blogService;

    @RequestMapping("/getList")
    public List<BlogInfoResponse> getList() {
        log.info("获取博客列表~");
        List<BlogInfoResponse> blogInfos = blogService.getList();
        return blogInfos;
    }

    @RequestMapping("/getBlogDetail")
    public BlogInfoResponse getBlogDetail(Integer blogId) {
        log.info("获取博客详情，blogId: {}", blogId);
        return blogService.getBlogDetail(blogId);
    }
}
