package org.study.langchain4jaidemo.controller;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.model.StreamingResponseHandler;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.study.langchain4jaidemo.service.Assistant;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-27
 * Time: 22:06
 */

@RestController
@Slf4j
public class StreamChatLanguageModelController {
//    @Resource
//    private StreamingChatLanguageModel streamingChatLanguageModel;
    @Resource
    private Assistant chatAssistant;


    /*@GetMapping(value = "/chatstream/chat2")
    public Flux<String> chat2(@RequestParam("prompt") String prompt, HttpServletResponse response) {
        // 设置响应的字符编码
        response.setCharacterEncoding("UTF-8");

        chatAssistant.chatFlux(prompt).subscribe(
                System.out::println,  // onNext
                Throwable::printStackTrace,  // onError
                () -> System.out.println("onComplete")  // onComplete
        );

        /*chatAssistant.chat2(prompt).subscribe(
                str -> {
                    // 这里的代码会在每次有新的String时执行
                    System.out.println(str);
                },
                error -> {
                    // 这里的代码会在发生错误时执行
                    System.err.println("Error occurred: " + error.getMessage());
                },
                () -> {
                    // 这里的代码会在Flux完成时执行
                    System.out.println("All strings processed onComplete.");
                }
        );

        //暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(7);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return chatAssistant.chatFlux(prompt);
    }

    @GetMapping(value = "/chatstream/assistant2")
    public void assistant2(@RequestParam(value = "prompt", defaultValue = "hello, 北京有什么好吃的？") String prompt) {
        streamingChatLanguageModel.generate(prompt, new StreamingResponseHandler<AiMessage>() {
            @Override
            public void onNext(String token) {
                System.out.println(token);
            }

            @Override
            public void onError(Throwable error) {

            }
        });
        //暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @GetMapping(value = "/chatstream/chat2", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> chat2(@RequestParam("prompt") String prompt) {
        log.info("接收到用户消息: {}", prompt);
        return chatAssistant.chatFlux(prompt);
    }*/

    @GetMapping("/chatstream/stream")
    public void streamChat(@RequestParam("prompt") String prompt, HttpServletResponse response) throws IOException, IOException {
        log.info("接收到用户消息: {}", prompt);

        response.setContentType("text/event-stream");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Connection", "keep-alive");

        PrintWriter writer = response.getWriter();

        chatAssistant.chatFlux(prompt)
                .subscribe(
                        chunk -> {
                            writer.write("data: " + chunk + "\n\n");
                            writer.flush();
                        },
                        error -> {
                            log.error("流式输出错误", error);
                            writer.write("event: error\ndata: " + error.getMessage() + "\n\n");
                            writer.flush();
                        },
                        () -> {
                            writer.write("event: complete\ndata: \n\n");
                            writer.flush();
                            writer.close();
                        }
                );
    }

}

