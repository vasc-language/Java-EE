package org.example.je20250307springbook.dao;

import org.example.je20250307springbook.model.UserInfo;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * Description: 模拟数据库的数据
 * User: 姚东名
 * Date: 2025-03-06
 * Time: 20:24
 */
@Repository
public class BookDao {
    public List<UserInfo> mockData() {
        List<UserInfo> userInfoList = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setBookId(i);
            userInfo.setBookName("bookName" + i);
            userInfo.setAuthor("author" + i);
            userInfo.setNum(new Random().nextInt(100));
            userInfo.setPrice(new BigDecimal(new Random().nextInt(100)));
            userInfo.setPublish("publish" + i);
            userInfo.setStatus(i % 5 == 0 ? 2 : 1); // 1-可借阅  2-不可借阅
            // 把数据放到列表中存储起来
            userInfoList.add(userInfo);
        }
        return userInfoList;
    }
}
