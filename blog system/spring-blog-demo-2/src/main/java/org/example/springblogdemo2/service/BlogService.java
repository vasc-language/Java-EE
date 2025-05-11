package org.example.springblogdemo2.service;

import org.example.springblogdemo2.pojo.dataobject.BlogInfo;
import org.example.springblogdemo2.pojo.request.AddBlogRequest;
import org.example.springblogdemo2.pojo.request.UpdateRequest;
import org.example.springblogdemo2.pojo.response.BlogInfoResponse;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-08
 * Time: 21:39
 */
public interface BlogService {
    List<BlogInfoResponse> getList();

    BlogInfoResponse getBlogDetail(Integer blogId);

    BlogInfo getBlogInfo(Integer blogId);

    boolean addBlog(AddBlogRequest addBlogRequest);

    Boolean updateBlog(UpdateRequest updateRequest);

    Boolean deleteBlog(Integer blogId);
}
