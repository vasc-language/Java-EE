package org.example.jd20250417springbookdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-19
 * Time: 18:40
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseResult<T> {
    // 三个参数：页码总数、一页有多少页码、页面数目
    // 让前端知道有多少条数据
    private Integer total; // 页码总数

    // 是当前页面要展示的数据内容
    private List<T> records; // 页面对象
    // 记录了本次请求的参数（如第几页、每页多少条）
    private PageRequest pageRequest;
}
