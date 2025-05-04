package org.example.config;


import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.example.store.MongoChatMemoryStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 配置小智Agent的持久化和记忆隔离
 * User: 姚东名
 * Date: 2025-05-03
 * Time: 16:57
 */
@Configuration
public class XiaozhiAgentConfig {
    // 持久化
    @Autowired
    private MongoChatMemoryStore mongoChatMemoryStore;

    /**
     * 记忆隔离
     * @return
     */
    @Bean
    ChatMemoryProvider chatMemoryProviderXiaozhi() {
        return memoryId -> MessageWindowChatMemory.builder().
                id(memoryId)
                .chatMemoryStore(mongoChatMemoryStore) // 配置持久化
                .maxMessages(100)
                .build();
    }

    /**
     * 配置 RAG 相关的
     * ContentRetriever 是 Langchain4j 中检索增强生成（RAG）流程中的一个关键组件，负责从底层数据源中检索与用户查询相关的内容
     */
    @Bean
    ContentRetriever contentRetrieverXiaozhi() {
        // 读取指定目录下的知识库文档
        // 使用默认的文档解析器对文档进行解析
        // D:/Github/Project/langchain4j/knowledge/
        Document document1 = FileSystemDocumentLoader.loadDocument("D:/Github/Project/langchain4j/knowledge/医院信息.md");
        Document document2 = FileSystemDocumentLoader.loadDocument("D:/Github/Project/langchain4j/knowledge/科室信息.md");
        Document document3 = FileSystemDocumentLoader.loadDocument("D:/Github/Project/langchain4j/knowledge/神经内科.md");

        List<Document> documents = Arrays.asList(document1, document2, document3);

        // 使用内存向量存储
        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();

        // 使用默认的文档分割器
        EmbeddingStoreIngestor.ingest(documents, embeddingStore);

        // 从嵌入存储（EmbeddingStore）里检索和查询内容相关的信息
        return EmbeddingStoreContentRetriever.from(embeddingStore);
    }
}
