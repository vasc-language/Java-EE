package org.example.springblogdemo2.common.util;

import org.example.springblogdemo2.pojo.dataobject.BlogInfo;
import org.example.springblogdemo2.pojo.response.BlogInfoResponse;
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
}
