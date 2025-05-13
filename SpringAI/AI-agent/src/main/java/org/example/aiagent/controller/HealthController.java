package org.example.aiagent.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-10
 * Time: 22:43
 */
@RestController
@RequestMapping("/health")
public class HealthController {
    @GetMapping("/healthCheck")
    public String healthCheck() {
        return "ok~";
    }
}
