package org.study.langchain4jaidemo.service;

import dev.langchain4j.service.spring.AiService;
import reactor.core.publisher.Flux;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-27
 * Time: 22:03
 */

public interface Assistant {
    String chat(String userMessage);

    // 流式输出
    Flux<String> chatFlux(String userMessage);
}
