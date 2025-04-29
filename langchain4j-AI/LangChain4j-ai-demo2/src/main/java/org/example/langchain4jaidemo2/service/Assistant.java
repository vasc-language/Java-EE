package org.example.langchain4jaidemo2.service;

import reactor.core.publisher.Flux;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-29
 * Time: 22:27
 */
public interface Assistant {
    String chat(String userMessage);

    Flux<String> chat2(String userMessage);
}
