package org.example.springblogdemo2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.example.springblogdemo2.common.constants.Constants;
import org.example.springblogdemo2.common.exception.BlogException;
import org.example.springblogdemo2.mapper.BlogInfoMapper;
import org.example.springblogdemo2.pojo.dataobject.BlogInfo;
import org.example.springblogdemo2.pojo.request.AddBlogRequest;
import org.example.springblogdemo2.pojo.request.UpdateRequest;
import org.example.springblogdemo2.pojo.response.BlogInfoResponse;
import org.example.springblogdemo2.service.BlogService;
import org.example.springblogdemo2.common.util.BeanTransUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-08
 * Time: 21:43
 */
@Slf4j
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogInfoMapper blogInfoMapper;

    @Override
    public List<BlogInfoResponse> getList() {
        QueryWrapper<BlogInfo> queryWrapper  =new QueryWrapper<>();
        queryWrapper.lambda().eq(BlogInfo::getDeleteFlag, Constants.BLOG_NORMAL);

        List<BlogInfo> blogInfos = blogInfoMapper.selectList(queryWrapper);
        // 将blogInfo 进行二次封装 变成 BlogInfoResponse
        List<BlogInfoResponse> responses = blogInfos
                .stream()
                .map(blogInfo -> BeanTransUtils.trans(blogInfo))
                .collect(Collectors.toList());
        return responses;
    }

    /**
     * 获取博客详情
     * @param blogId
     * @return
     */
    @Override
    public BlogInfoResponse getBlogDetail(Integer blogId) {
        return BeanTransUtils.trans(getBlogInfo(blogId));
    }

    /**
     * 根据 blogId 来获取博客信息（里面包含了作者ID）
     * @param blogId
     * @return
     */
    @Override
    public BlogInfo getBlogInfo(Integer blogId){
        QueryWrapper<BlogInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(BlogInfo::getDeleteFlag, Constants.BLOG_NORMAL)
                .eq(BlogInfo::getId, blogId);
        return blogInfoMapper.selectOne(queryWrapper);
    }

    /**
     * 添加博客
     * @param addBlogRequest
     * @return
     */
    @Override
    public boolean addBlog(AddBlogRequest addBlogRequest) {
        BlogInfo blogInfo = new BlogInfo();
        BeanUtils.copyProperties(addBlogRequest, blogInfo);
        try {
            Integer result = blogInfoMapper.insert(blogInfo); // 插入博客
            if (result == 1) {
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("博客插入失败，e: ", e);
            throw new BlogException("内部错误，请联系管理员");
        }
    }

    /**
     * 编辑博客
     * @param updateRequest
     * @return
     */
    @Override
    public Boolean updateBlog(UpdateRequest updateRequest) {
        BlogInfo blogInfo = BeanTransUtils.trans(updateRequest);
        try {
            Integer result = blogInfoMapper.updateById(blogInfo);
            return result == 1;
        } catch (Exception exception) {
            log.error("编辑博客失败，e: ", exception);
            throw new BlogException("内部错误，请联系管理员");
        }
    }

    /**
     * 删除博客（逻辑删除）
     * @param blogId
     * @return
     */
    @Override
    public Boolean deleteBlog(Integer blogId) {
        BlogInfo blogInfo = new BlogInfo();
        // 明确指定了要更新的博客记录。如果不设置 ID。MyBatis-plus 将无法知道应该更新数据库中哪一条数据
        blogInfo.setId(blogId); // 要删除的博客（用 blogId 进行定位）
        blogInfo.setDeleteFlag(Constants.BLOG_DELETE);
        try {
            // UPDATE blog_info SET delete_flag = ? WHERE id = ?
            Integer result = blogInfoMapper.updateById(blogInfo);
            return result == 1;
        } catch (Exception exception) {
            log.error("删除博客失败，e: ", exception);
            throw new BlogException("内部错误，请联系管理员");
        }
    }

    /*@Override
    public BlogInfoResponse getBlogDetail(Integer blogId) {
        QueryWrapper<BlogInfo> queryWrapper  =new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(BlogInfo::getDeleteFlag, 0)
                .eq(BlogInfo::getId, blogId);
        BlogInfo blogInfo = blogInfoMapper.selectOne(queryWrapper);
        return BeanTransUtils.trans(blogInfo);
    }*/
}
