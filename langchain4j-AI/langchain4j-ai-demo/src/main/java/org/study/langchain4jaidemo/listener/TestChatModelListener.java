package org.study.langchain4jaidemo.listener;

import cn.hutool.core.util.IdUtil;
import dev.langchain4j.model.chat.listener.ChatModelErrorContext;
import dev.langchain4j.model.chat.listener.ChatModelListener;
import dev.langchain4j.model.chat.listener.ChatModelRequestContext;
import dev.langchain4j.model.chat.listener.ChatModelResponseContext;
import lombok.extern.slf4j.Slf4j;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-29
 * Time: 18:51
 */
@Slf4j
public class TestChatModelListener implements ChatModelListener {
    @Override
    public void onRequest(ChatModelRequestContext requestContext) {
        // ChatModelListener.super.onRequest(requestContext);
        // onRequest 配置的 Key-Value 键值对，在 onResponse 阶段可以获取，上下文传递参数好用
        String uuidValue = IdUtil.simpleUUID();
        requestContext.attributes().put("TraceID", uuidValue);
        log.info("请求参数 requestContext：{}", requestContext + "\t" + uuidValue);
    }

    @Override
    public void onResponse(ChatModelResponseContext responseContext) {
        // ChatModelListener.super.onResponse(responseContext);
        Object traceID = responseContext.attributes().get("TraceID");
        log.info("返回结果 responseContext：{}", traceID);
    }

    @Override
    public void onError(ChatModelErrorContext errorContext) {
        // ChatModelListener.super.onError(errorContext);
        log.error("请求异常 ChatModelErrorContext：{}", errorContext);
    }
}
