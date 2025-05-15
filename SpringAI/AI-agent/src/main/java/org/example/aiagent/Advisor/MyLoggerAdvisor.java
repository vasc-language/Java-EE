package org.example.aiagent.Advisor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import org.springframework.ai.chat.client.advisor.api.AdvisedRequest;
import org.springframework.ai.chat.client.advisor.api.AdvisedResponse;
import org.springframework.ai.chat.client.advisor.api.CallAroundAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAroundAdvisorChain;
import org.springframework.ai.chat.client.advisor.api.StreamAroundAdvisor;
import org.springframework.ai.chat.client.advisor.api.StreamAroundAdvisorChain;
import org.springframework.ai.chat.model.MessageAggregator;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-14
 * Time: 19:47
 */

/**
 * 自定义拦截器 Advisor
 * 打印 info 级别日志、只输出单次用户提示词和 AI 回复的文本
 * 1. 拦截与增强：能够在请求发送前和响应接收后执行特定逻辑
 * 2. 链式处理：多个 Advisor 按顺序组成处理链，依次处理请求和响应
 * 3. 上下文传递：允许在处理链中传递请求上下文和参数，实现跨 Advisor 通信
 */
@Slf4j
public class MyLoggerAdvisor implements CallAroundAdvisor, StreamAroundAdvisor {
    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
    private AdvisedRequest before(AdvisedRequest request) {
        log.info("AI Request: {}", request.userText());
        return request;
    }

    private void observeAfter(AdvisedResponse advisedResponse) {
        log.info("AI Response: {}", advisedResponse.response().getResult().getOutput().getText());
    }

    /**
     * 用于处理同步请求和响应（）
     * @param advisedRequest the advised request
     * @param chain the advisor chain
     * @return
     */
    @Override
    public AdvisedResponse aroundCall(AdvisedRequest advisedRequest, CallAroundAdvisorChain chain) {
        advisedRequest = this.before(advisedRequest);
        // 核心
        AdvisedResponse advisedResponse = chain.nextAroundCall(advisedRequest);

        this.observeAfter(advisedResponse);
        return advisedResponse;
    }

    /**
     *用于处理流式请求和响应
     * @param advisedRequest the advised request
     * @param chain the chain of advisors to execute
     * @return
     */
    @Override
    public Flux<AdvisedResponse> aroundStream(AdvisedRequest advisedRequest, StreamAroundAdvisorChain chain) {
        advisedRequest = this.before(advisedRequest);
        Flux<AdvisedResponse> advisedResponseFlux = chain.nextAroundStream(advisedRequest);
        return (new MessageAggregator()).aggregateAdvisedResponse(advisedResponseFlux, this::observeAfter);
    }


    /**
     * 值越小优先级越高，越先执行
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
