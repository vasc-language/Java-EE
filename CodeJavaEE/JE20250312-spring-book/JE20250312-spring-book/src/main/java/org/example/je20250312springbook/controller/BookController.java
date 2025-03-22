package org.example.je20250312springbook.controller;

import org.example.je20250312springbook.mode.BookInfo;
import org.example.je20250312springbook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-03-12
 * Time: 19:54
 */
@RequestMapping("/book")
@RestController
public class BookController {
    @Autowired
    private BookService bookService;
    @PostMapping("/getList")
    public List<BookInfo> getList() {
        return bookService.getList();
    }
}
