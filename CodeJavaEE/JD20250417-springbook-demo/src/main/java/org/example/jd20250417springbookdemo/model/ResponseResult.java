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

/**
 * ResponseResult 是分页结果的统一封装类，用于返回给前端完整的分页数据
 * - total：满足条件的总记录数，用于计算总页数
 * - records：当前页的数据记录列表
 * - pageRequest：原始分页请求参数（currentPage、pageSize、offset）
 *
 * 在项目中的作用
 * 1. 承载分页数据：包含了当前页的所有图书记录信息
 * 2. 提供分页元信息：通过 total 和 pagePage 可以计算出总页数（30 / 10 = 3）
 * 3. 前端分页组件使用：为 jqPaginator 等前端分页组件提供必要的数据
 * @param <T>
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
