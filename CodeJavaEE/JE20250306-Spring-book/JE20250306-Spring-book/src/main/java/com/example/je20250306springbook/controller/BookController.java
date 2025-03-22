package com.example.je20250306springbook.controller;

import com.example.je20250306springbook.model.BookInfo;
import com.example.je20250306springbook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-03-06
 * Time: 9:13
 */
@RequestMapping("/book")
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/getList")
    public List<BookInfo> getList() {
        return bookService.getList();
    }
}

