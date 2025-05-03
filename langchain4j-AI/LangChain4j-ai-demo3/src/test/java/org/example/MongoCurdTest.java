package org.example;


import org.apache.poi.ss.formula.functions.T;
import org.example.bean.ChatMessages;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;


/**
 * Created with IntelliJ IDEA.
 * Description: 测试 MongoDB 的增删改查
 * User: 姚东名
 * Date: 2025-05-03
 * Time: 9:22
 */
@SpringBootTest
public class MongoCurdTest {
    /**
     * MongoTemplate 这个类是 Spring Data MongoDB 框架的一个核心类
     * 被用来执行 MongoDB 的各种数据库操作
     */
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 插入文档
     */
    @Test
    public void testInsert() {
        ChatMessages chatMessages = new ChatMessages();
        chatMessages.setContent("聊天记录列表");
        mongoTemplate.insert(chatMessages); // 插入 insert
    }

    /**
     * 根据 ID 主键查询
     */
    @Test
    public void testFindId() {
        ChatMessages chatMessages = mongoTemplate.findById("68157216811ccc631f212546", ChatMessages.class);
        System.out.println(chatMessages); // 查找 find
    }

    /**
     * 修改文档
     */
    @Test
    public void testUpdateById() {
        // 作用：用于定义查询的过滤条件。它指定了你想要的查找或操作哪些文档，可以看成 SQL 语句中的 WHERE
        Criteria criteria = Criteria.where("_id").is("68157216811ccc631f212546");
        // 作用：代表一个完整的数据库请求。它封装了 Criteria（查询条件），还可以包含其他的查询参数，比如排序(Sort)、分页（limit，skip）、选择要返回的字段（投影 fields）
        Query query = new Query(criteria);
        // 作用：用于定义要对找到的文档执行 那些修改操作，可以把它类比成 SQL 语句中的 SET 子句
        Update update = new Update();
        update.set("content", "新的聊天记录列表");
        // 新增或修改
        // 更新那个文档？如何更新？在哪个集合中更新？
        mongoTemplate.upsert(query, update, ChatMessages.class); // 修改 upset
    }

    /**
     * 新增文档
     */
    @Test
    public void testUpdateById2() {
        Criteria criteria = Criteria.where("_id").is("100");
        Query query = new Query(criteria);
        Update update = new Update();
        update.set("content", "新的聊天记录列表");
        // collection 中没有就直接添加，有就更新
        mongoTemplate.upsert(query, update, ChatMessages.class); // 增加 upset
    }

    /**
     * 删除文档
     */
    @Test
    public void testDelete() {
        Criteria criteria = Criteria.where("_id").is("100");
        Query query = new Query(criteria);
        mongoTemplate.remove(query, ChatMessages.class); // 删除 remove
    }
}
