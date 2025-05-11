package org.example.springblogdemo2.controller;

import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.example.springblogdemo2.pojo.request.AddBlogRequest;
import org.example.springblogdemo2.pojo.request.UpdateRequest;
import org.example.springblogdemo2.pojo.response.BlogInfoResponse;
import org.example.springblogdemo2.service.BlogService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
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

    /**
     * 获取博客详情
     * @param blogId
     * @return
     */
    @RequestMapping("/getBlogDetail")
    public BlogInfoResponse getBlogDetail(@NotNull(message = "blogId 不能为 null") Integer blogId) {
        log.info("获取博客详情，blogId: {}", blogId);
        return blogService.getBlogDetail(blogId);
    }

    /**
     * 添加博客
     * @param addBlogRequest
     * @return
     */
    @RequestMapping("/addBlog")
    public Boolean addBlog(@RequestBody @Validated AddBlogRequest addBlogRequest) {
        log.info("发布博客，userId: {}, title: {}", addBlogRequest.getUserId(), addBlogRequest.getTitle());
        return blogService.addBlog(addBlogRequest);
    }

    /**
     * 更新博客
     * @param updateRequest
     * @return
     */
    @RequestMapping("/update")
    public Boolean update(@RequestBody @Validated UpdateRequest updateRequest) {
        log.info("更新博客，request：{}", updateRequest);
        return blogService.updateBlog(updateRequest);
    }

    /**
     * 根据 blogId 来删除博客
     * @return
     */
    @RequestMapping("/delete")
    public Boolean delete(@NotNull(message = "blogId 不能为 null") Integer blogId) {
        log.info("删除博客，blogId: {}", blogId);
        return blogService.deleteBlog(blogId);
    }
}
