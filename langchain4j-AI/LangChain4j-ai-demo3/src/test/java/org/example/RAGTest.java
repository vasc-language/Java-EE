package org.example;

import dev.langchain4j.community.model.dashscope.QwenTokenizer;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.data.document.parser.apache.pdfbox.ApachePdfBoxDocumentParser;
import dev.langchain4j.data.document.splitter.DocumentByParagraphSplitter;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.onnx.HuggingFaceTokenizer;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 测试检索增强生成的各个步骤
 * User: 姚东名
 * Date: 2025-05-04
 * Time: 9:06
 */
@SpringBootTest
public class RAGTest {
    /**
     * 文档加载器 DocumentLoader
     * 加载 .txt 文件
     */
    @Test
    public void testReadDocument() {
/*        // 使用默认的文档加载器 DocumentLoader
        // 1. 使用 FileSystemDocumentLoader 读取指定目录下的知识库文档
        // 2. 使用默认的文档解析器 loadDocumentParser 对文档进行解析
        Document document = FileSystemDocumentLoader.loadDocument("D:/Github/Project/langchain4j/knowledge/测试.txt");
        System.out.println(document.text());

        // 加载单个文档
        Document document2 = FileSystemDocumentLoader.loadDocument("D:/Github/Project/langchain4j/knowledge/file.txt", new TextDocumentParser());

        // 从一个目录中加载所有的文档
        Document document3 = FileSystemDocumentLoader.loadDocument("D:/Github/Project/langchain4j/knowledge", new TextDocumentParser());

        // 从一个目录及其子目录中加载所有文档
        List<Document> documents2 = FileSystemDocumentLoader.loadDocumentsRecursively("D:/Github/Project/langchain4j/knowledge", new TextDocumentParser());*/

        /**
         * 从一个目录中加载所有的 .txt 文档
         * FileSystems.getDefault()
         * 功能：获取 Java  运行时环境中默认文件系统实例
         * 实现原理：通过 JavaNIO 包提供的工具类获取当前 JVM 可访问的文件系统
         * 返回值：返回 FileSystem 类型的实例，代表操作系统的主文件系统
         * 使用场景：当需要访问文件系统进行文件操作时，这是获取获取文件系统引用的入口点
         *
         * getPathMatcher("glob:*.txt")
         * 功能：创建一个用于匹配特定模式路径的 PathMatcher 对象
         * 参数解析：
         *   - "glob:*.txt"：指定使用 glob 语法模式匹配以 .txt 结尾的所有文件
         *   - "glob:"：表示使用 glob 匹配语法
         *   - "*"：通配符，匹配任意字符序列
         *   - ".txt"：匹配具有.tx扩展名的文件
         * 返回值：返回可用于测试路径是否匹配指定模式的 PathMatcher 实例
         *
         * FileSystemDocumentLoader.loadDocuments("目录路径", pathMatcher, new TextDocumentParser()()
         * - 功能：从指定目录加载符合条件的文档
         * - 参数分析：
         *   - 第一个参数：指定要搜索文档的目录路径（"D:/Github/Project/langchain4j/knowledge")
         *   - 第二个参数：前面创建的 pathMatcher ，用于筛选只加载 .txt 文件
         *   - 第三个参数：TextDocumentParser 实例，负责将文件内容解析为文档对象
         * 返回值：返回 List<Document>，包含所有匹配的文档对象
         * 特点：LangChain4j 库提供的方法，用于文档检索系统的数据加载阶段
         *
         *
         * document.metadata()
         * - 功能：获取文档的元数据信息
         * - 返回内容：文件的相关信息，如文件路径、创建时间、修改时间
         * - 数据格式：通常是键值对形式的集合
         * - 用途：提供关于文档来源和特性的上下文信息
         *
         * document.text()
         * - 功能：获取文档的文本内容
         * - 返回内容：文档的实际内容，经过 TestDocumentParser 解析
         * - 特点：对于文本文件，返回的是文件的纯文本内容
         * - 用途：获取文档的主体内容用于后续处理（如向量化、搜索等）
         */
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:*.txt");
        List<Document> documents = FileSystemDocumentLoader.loadDocuments("D:/Github/Project/langchain4j/knowledge", pathMatcher, new TextDocumentParser());

        for (Document document : documents) {
            System.out.println("========================================================================================" +
                    "==================================");
            System.out.println(document.metadata());
            System.out.println(document.text());
        }
    }

    /**
     * 文档解析器 DocumentParser
     * 解析 .PDF 文件
     */
    @Test
    public void testParserPDF() {
        Document document = FileSystemDocumentLoader.loadDocument(
                "D:/Github/Project/langchain4j/knowledge/医院信息.pdf",
                new ApachePdfBoxDocumentParser()
        );

        System.out.println(document.metadata());

        System.out.println(document.text());
    }

    /**
     * 加载文档并存入向量数据库中
     */
    @Test
    public void testReadDocumentAndStore() {
        // 1. 使用 FileSystemDocumentLoader 读取指定目录下的知识库文档
        // 并且使用默认的文档解析器对文档进行解析（TextDocumentParser）
        Document document = FileSystemDocumentLoader.loadDocument("D:/Github/Project/langchain4j/knowledge/人工智能.md");

        // 2. 使用基于内存的向量存储（LangChain4j 框架自带的）
        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();

        /**、
         * 3. 使用 ingest 完成：文档分割、文本向量化、向量存储
         * 一、整体架构
         * EmbeddingStoreIngestor 采用了建造者模式（Builder pattern）进行初始化
         * 1. 文档转换器（DocumentTransforms）：可选组件，用于在处理前对文档进行预处理
         * 2. 文档分割器（DocumentSplitter）：将文档分割成更小的的文本片段
         * 3. 文本片段转换器（TextSegmentTransformer）：可选组件，用于处理分割后的文本片段
         * 4. 嵌入模型（EmbeddingModel）：将文本转换为向量表示
         * 5. 嵌入存储（EmbeddingStore）：存储文本片段及其向量
         *
         * 二、ingest 方法详细工作流程
         * 1. 文档分割（Document Splitting）
         * 当调用 EmbeddingStoreIngestor.ingest(document, embeddingStore) 时，首先会将文档分割成更小的的文本片段：
         * - 如果在构建 EmbeddingStoreIngestor 时指定了自定义的 DocumentSplitting，则使用该分割器
         * - 如果未指定，则使用默认的分割器，通常是 DocumentSplitters.recursive() ，它会根据文本结构递归地将文档分割成更易管理的片段
         * - 分割的主要目的是将长文档切分成适合嵌入模型处理的较小文本片段，确保每个片段都能被完整编码
         * 2. 文本向量化（Text Embedding）
         * 对于每个分割后的文本片段进行向量化：
         * - 使用配置的 EmbeddingModel 将文本片段转换为高维向量表示
         * - 如果未在构建器中指定嵌入模型，会使用一个默认的内置嵌入模型（如AllMiniLmL6V2EmbeddingModel）
         * - 向量化过程是并行执行的，提高了处理效率，特别是对于大文档
         * - 如果指定了自定义的 TextSegmentTransformer，会在向量化前对文本片段进行处理，例如添加元数据或格式化文本
         * 向量核心代码类类似于：
         *   Embedding embedding = embeddingModel.embed(textSegment).content();
         *
         * 3. 向量存储（Vector Storage）
         * 最后，将文本片段及其向量表示存储到嵌入存储中：
         * - 使用 embeddingStore.add(embedding, textSegment) 方法存储每个文本片段及其向量
         * - 对于 InMemoryEmbeddingStore ，数据存储在 Java 堆内存中，只是适用于临时或小型应用场景
         * - 存储过程保留了原始文本和元数据信息，便于后续检索时获取完成上下文
         * - 存储的每一个向量都有唯一的标识符，用于后续检索和管理
         */
        EmbeddingStoreIngestor.ingest(document, embeddingStore);

        // 4. 查看向量数据库内容
        System.out.println(embeddingStore);
    }

    /**
     * 文档分割器 DocumentSplitter
     */
    @Test
    public void testDocumentSplitter() {
        // 加载知识库文档
        Document document = FileSystemDocumentLoader.loadDocument("D:/Github/Project/langchain4j/knowledge/人工智能.md");
        // 使用基于内存的向量存储
        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();
        // 自定义分割器
        // 按段落分割文档：每个片段包含不超过 300个 token，并且有30个 token 的重叠部分保证连贯性
        DocumentByParagraphSplitter documentByParagraphSplitter = new DocumentByParagraphSplitter(
                300,
                30,
                new HuggingFaceTokenizer() // token 分词器，以 token 为单位
        );
        // 按字符计算
        // DocumentByParagraphSplitter documentSplitter = new DocumentByParagraphSplitter(300, 30);

        // EmbeddingStoreIngestor 是 Langchain4j 中用于将文档内容转换为嵌入（Embeddings）并存储到嵌入存储（EmbeddingStore）中的工具
        EmbeddingStoreIngestor.builder()
                .embeddingStore(embeddingStore)
                .documentSplitter(documentByParagraphSplitter)
                .build()
                .ingest(document);
    }

    @Test
    public void testTokenCount() {
        String text = "这是一个示例文本，用于测试 token 长度的计算。";
        UserMessage userMessage = UserMessage.userMessage(text);

        // LANGCHAIN4J_KEY
        QwenTokenizer tokenizer = new QwenTokenizer(System.getenv("LANGCHAIN4J_KEY"), "qwen-max");
        // HuggingFaceTokenizer huggingFaceTokenizer = new HuggingFaceTokenizer();
        int count = tokenizer.estimateTokenCountInMessage(userMessage);
        System.out.println("token 长度：" + count);
    }
}
