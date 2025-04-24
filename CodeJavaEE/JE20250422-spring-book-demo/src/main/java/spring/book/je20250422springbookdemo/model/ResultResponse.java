package spring.book.je20250422springbookdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-23
 * Time: 20:34
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResultResponse<T> {
    // total、records、pageRequest
    private Integer total; // 总记录数
    private List<T> records; // 当前页面所有内容
    private PageRequest pageRequest; // 当前页面所有的属性 被封装成一个对象
}
