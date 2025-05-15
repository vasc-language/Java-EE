package org.example.aiagent.Advisor;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-14
 * Time: 20:46
 */

import org.springframework.ai.chat.client.advisor.api.*;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义R2拦截器（ReReaderAdvisor）
 * {Input_Query}
 * Read the question again: {Input_Query}
 * 重复一遍
 */
@Component
public class ReReadingAdvisor implements CallAroundAdvisor, StreamAroundAdvisor {

    private AdvisedRequest before(AdvisedRequest advisedRequest) {

        Map<String, Object> advisedUserParams = new HashMap<>(advisedRequest.userParams());
        advisedUserParams.put("re2_input_query", advisedRequest.userText());

        /**
         * from 方法
         * 1. 创建构造器实例：从现有的 AdvisedRequest 对象创建一个新的 构造器（Builder）以便进行修改
         * 2. 保留原始对象的属性：它会复制请求对象的所有属性到新的构造器中，这样新对象可以继承原始对象的基本属性
         * 3. 支持不可变对象模式：SpringAI 中的 AdvisedRequest 对象遵循不可变的设计模式，from 方法允许我们
         *      基于现有对象创建新对象而不修改原始对象
         * 4. 链式调用支持：返回的构造器支持调用（fluent API）可以连续设置多个属性
         */
        return AdvisedRequest.from(advisedRequest)
                .userText("""
                        {re2_input_query}
                        Read the question again: {re2_input_query}
                        """)
                .userParams(advisedUserParams)
                .build();
    }

    @Override
    public AdvisedResponse aroundCall(AdvisedRequest advisedRequest, CallAroundAdvisorChain chain) {
        return chain.nextAroundCall(this.before(advisedRequest));
    }

    @Override
    public Flux<AdvisedResponse> aroundStream(AdvisedRequest advisedRequest, StreamAroundAdvisorChain chain) {
        return chain.nextAroundStream(this.before(advisedRequest));
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}

