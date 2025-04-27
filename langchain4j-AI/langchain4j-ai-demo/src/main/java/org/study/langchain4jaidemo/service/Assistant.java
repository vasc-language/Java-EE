package org.study.langchain4jaidemo.service;

import dev.langchain4j.service.spring.AiService;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-27
 * Time: 22:03
 */

public interface Assistant {
    String chat(String userMessage);
}
