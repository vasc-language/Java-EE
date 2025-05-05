package org.example.springblogdemo.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.springblogdemo.pojo.response.BlogInfoResponse;
import org.example.springblogdemo.service.BlogService;
import org.example.springblogdemo.service.impl.BlogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-05
 * Time: 10:57
 */
@Slf4j
@RequestMapping("/blog")
@RestController
public class BlogController {
    @Resource(name = "blogServiceImpl")
    private BlogService blogService;

    //@Autowired
    // private BlogServiceImpl blogService;

    /**
     * 获取博客列表
     * @return
     */
    @RequestMapping("/getList")
    public List<BlogInfoResponse> getList() {
        log.info("获取博客列表~");
        List<BlogInfoResponse> blogInfos = blogService.getList();
        return blogInfos;
    }
}
