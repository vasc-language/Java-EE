package org.example.springblogdemo2.common.util;

import org.example.springblogdemo2.pojo.dataobject.BlogInfo;
import org.example.springblogdemo2.pojo.dataobject.UserInfo;
import org.example.springblogdemo2.pojo.request.UpdateRequest;
import org.example.springblogdemo2.pojo.response.BlogInfoResponse;
import org.example.springblogdemo2.pojo.response.UserInfoResponse;
import org.springframework.beans.BeanUtils;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-08
 * Time: 21:55
 */
public class BeanTransUtils {
    public static BlogInfoResponse trans(BlogInfo blogInfo) {
        if (blogInfo == null) {
            // TODO 待做事项
            return null;
        }
        BlogInfoResponse blogInfoResponse = new BlogInfoResponse();
        BeanUtils.copyProperties(blogInfo, blogInfoResponse);
        return blogInfoResponse;
    }

    public static UserInfoResponse trans(UserInfo userInfo) {
        if (userInfo == null) {
            // TODO 待做事项
            return null;
        }
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        BeanUtils.copyProperties(userInfo, userInfoResponse);
        return userInfoResponse;
    }

    public static BlogInfo trans(UpdateRequest updateRequest) {
        if (updateRequest == null) {
            return null;
        }
        BlogInfo blogInfo = new BlogInfo();
        BeanUtils.copyProperties(updateRequest, blogInfo);
        return blogInfo;
    }
}
