package org.example.springblogdemo.util;

import org.example.springblogdemo.pojo.dataobject.BlogInfo;
import org.example.springblogdemo.pojo.response.BlogInfoResponse;
import org.springframework.beans.BeanUtils;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-06
 * Time: 8:43
 */
public class BeanTransUtils {
    public static BlogInfoResponse trans(BlogInfo blogInfo) {
        if (blogInfo == null) {
            // TODO 待做事情
            return null;
        }
        BlogInfoResponse response = new BlogInfoResponse();
        BeanUtils.copyProperties(blogInfo, response);
        return response;
    }
}
