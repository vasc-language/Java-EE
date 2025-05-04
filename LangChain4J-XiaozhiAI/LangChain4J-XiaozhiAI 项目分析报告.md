# LangChain4J-XiaozhiAI 项目分析报告

## 1. 项目概述

本项目名为 “硅谷小智（医疗版）”，是一个基于 Java 技术栈和 LangChain4J 框架构建的 AI 聊天助手应用。其核心目标是利用大型语言模型（LLM）的能力，结合本地知识库和外部工具，提供一个专注于医疗领域的智能问答和辅助服务。项目采用了前后端分离的架构，后端使用 Spring Boot 框架，深度集成了 LangChain4J 库来实现与大模型的交互、记忆管理、工具调用（函数调用）以及检索增强生成（RAG）等功能。前端则采用 Vue.js 构建用户交互界面。

根据项目提供的 `尚硅谷-Java+大模型-硅谷小智（医疗版）.md` 文档，该项目似乎是一个教学或演示项目，旨在展示如何使用 LangChain4J 将大模型能力集成到 Java 应用中，特别是针对医疗场景。项目配置使用了阿里云的百炼平台（DashScope）提供的通义千问（Qwen）系列模型，同时也展示了如何接入其他模型（如 DeepSeek）以及本地部署模型（通过 Ollama）。

## 2. 技术栈

**后端:**

*   **核心框架:** Spring Boot 3.2.6
*   **AI 框架:** LangChain4J 1.0.0-beta3
*   **语言:** Java 17
*   **构建工具:** Maven
*   **数据库:** 
    *   MySQL (使用 Mybatis-Plus 操作，存储如预约信息等业务数据)
    *   MongoDB (存储聊天记忆)
*   **大模型平台:** 阿里云百炼 (DashScope)，支持 Qwen 系列模型，配置中也提到了 OpenAI 兼容接口（可用于 DeepSeek 等）
*   **API 文档:** Knife4j
*   **其他:** Spring WebFlux (用于流式响应), Reactor

**前端:**

*   **框架:** Vue.js (具体版本需查看 `package.json`)
*   **构建工具:** Vite
*   **UI 组件库:** (可能使用，需查看 `package.json` 和代码)

## 3. 项目结构分析

项目主要包含以下几个模块：

*   **`LangChain4j-ai-demo3`:** 后端 Spring Boot + LangChain4J 核心模块。
*   **`xiaozhi-ui`:** 前端 Vue.js 应用模块。
*   **`knowledge`:** 存放用于 RAG 的本地知识库文件（包含 .md, .pdf, .txt 格式）。
*   **`images`:** 存放项目相关的图片资源，包括文档中的截图。
*   **`尚硅谷-Java+大模型-硅谷小智（医疗版）.md`:** 项目的说明和教程文档。

### 3.1 后端 (`LangChain4j-ai-demo3`) 结构

后端遵循典型的 Spring Boot 项目结构：

*   **`src/main/java/org/example`:** Java 源代码根目录。
    *   `XiaozhiApp.java`: Spring Boot 应用启动类。
    *   `assistant`: 包含 LangChain4J 的 AI 服务接口和可能的实现类。
        *   `XiaozhiAgent.java`: 核心 AI 服务接口，使用 `@AiService` 注解配置模型、记忆、工具和 RAG。
    *   `bean`: 数据传输对象 (DTO)，如 `ChatForm`, `ChatMessages`。
    *   `config`: Spring 配置类，用于配置 LangChain4J 组件，如 EmbeddingStore, ChatMemoryProvider 等。
    *   `controller`: Spring MVC 控制器，处理 HTTP 请求。
        *   `XiaozhiController.java`: 提供 `/xiaozhi/chat` API 端点，接收用户消息并调用 `XiaozhiAgent`。
    *   `entity`: JPA/Mybatis-Plus 实体类，映射数据库表，如 `Appointment`。
    *   `mapper`: Mybatis-Plus Mapper 接口，定义数据库操作，如 `AppointmentMapper`。
    *   `service`: 业务逻辑服务层接口和实现。
        *   `AppointmentService.java` / `impl`: 处理预约相关的业务逻辑。
    *   `store`: 数据存储相关实现。
        *   `MongoChatMemoryStore.java`: 使用 MongoDB 实现 LangChain4J 的 `ChatMemoryStore`。
    *   `tools`: LangChain4J 工具类，用于函数调用。
        *   `AppointmentTools.java`: 提供预约相关的工具方法供 LLM 调用。
        *   `CalculatorTools.java`: 提供计算器工具方法供 LLM 调用。
*   **`src/main/resources`:** 资源文件目录。
    *   `application.properties`: Spring Boot 配置文件，包含服务器端口、数据库连接信息、LangChain4J 模型 API Key 和模型名称等配置。
    *   `mapper`: Mybatis-Plus XML 映射文件，如 `AppointmentMapper.xml`。
    *   `*.txt`: LangChain4J 使用的提示模板文件，如 `xiaozhi-prompt-template.txt`。
    *   `knowledge`: (推测可能用于存放处理后的知识文件，但实际知识库在项目根目录的 `knowledge` 文件夹下)
*   **`src/test/java`:** 单元测试和集成测试代码。
*   **`pom.xml`:** Maven 项目配置文件，定义项目依赖和构建配置。

### 3.2 前端 (`xiaozhi-ui`) 结构

前端是一个基于 Vite 构建的 Vue.js 项目：

*   **`public`:** 静态资源目录。
*   **`src`:** 前端源代码目录。
    *   `main.js`: Vue 应用入口文件。
    *   `App.vue`: 根组件。
    *   `components`: 可复用的 Vue 组件。
        *   `ChatWindow.vue`: 核心的聊天窗口界面组件。
    *   `assets`: 存放图片、样式等资源。
*   **`index.html`:** HTML 入口文件。
*   **`package.json`:** Node.js 项目配置文件，定义前端依赖和脚本。
*   **`vite.config.js`:** Vite 配置文件。
*   **`README.md`:** 前端模块的说明文件。

## 4. 核心功能分析

### 4.1 AI 交互核心 (`XiaozhiAgent`)

项目的 AI 交互核心是 `XiaozhiAgent` 接口，通过 LangChain4J 的 `@AiService` 注解进行配置。这个注解极大地简化了与 LLM 的集成：

```java
@AiService(
        wiringMode = AiServiceWiringMode.EXPLICIT, // 显式指定要使用的 Bean
        // chatModel = "qwenChatModel", // 非流式模型（注释掉了）
        // 用于流式输出的模型
        streamingChatModel = "qwenStreamingChatModel", 
        // 聊天记忆提供者
        chatMemoryProvider = "chatMemoryProviderXiaozhi", 
        // 工具（函数调用）
        tools = "appointmentTools", 
        // 内容检索器（RAG），这里配置了 Pinecone，但代码中也有注释掉的本地实现
        contentRetriever = "contentRetrieverXiaozhiPincone" 
)
public interface XiaozhiAgent {
    // 流式输出方法
    @SystemMessage(fromResource = "xiaozhi-prompt-template.txt") // 指定系统提示模板
    Flux<String> chat(@MemoryId Long memoryId, @UserMessage String userMessage);
}
```

*   **模型集成:** 配置了 `qwenStreamingChatModel` Bean 作为流式聊天模型。该 Bean 的具体配置（如 API Key, 模型名称）在 `application.properties` 中定义，并通过 LangChain4J 的 Spring Boot Starter 自动配置或自定义配置类注入。
*   **流式输出:** 接口方法返回 `Flux<String>`，结合 Spring WebFlux，实现了聊天响应的流式输出，提升了用户体验。
*   **聊天记忆:** 通过 `chatMemoryProvider = "chatMemoryProviderXiaozhi"` 指定了聊天记忆的提供者 Bean。结合 `@MemoryId Long memoryId` 参数，可以为不同的用户或会话维护独立的聊天历史。`MongoChatMemoryStore` 提供了基于 MongoDB 的持久化记忆存储。
*   **工具调用 (Function Calling):** 通过 `tools = "appointmentTools"` 集成了 `AppointmentTools` Bean 中定义的方法。这使得 LLM 能够调用 Java 方法来执行特定任务，例如查询或创建预约。
*   **检索增强生成 (RAG):** 通过 `contentRetriever = "contentRetrieverXiaozhiPincone"` 集成了内容检索器。这允许 LLM 在回答问题前，先从指定的知识库（向量数据库 Pinecone 或本地文件）中检索相关信息，并将这些信息注入到提示中，从而让 LLM 能够基于特定领域的知识进行回答。知识库的来源是项目根目录下的 `knowledge` 文件夹中的文档。
*   **系统提示:** 使用 `@SystemMessage(fromResource = "xiaozhi-prompt-template.txt")` 从外部文件加载系统提示，用于设定 AI 的角色、行为和指令。

### 4.2 API 端点 (`XiaozhiController`)

`XiaozhiController` 提供了一个简单的 RESTful API 端点 `/xiaozhi/chat` (POST)，用于接收前端发送的聊天请求。请求体是一个 `ChatForm` 对象，包含 `memoryId` 和用户消息 `message`。控制器直接调用 `XiaozhiAgent` 的 `chat` 方法，并将返回的 `Flux<String>` 作为流式响应返回给前端。

### 4.3 知识库与 RAG 实现

项目包含一个 `knowledge` 目录，存放了 `.md`, `.pdf`, `.txt` 等格式的医疗相关文档（如医院信息、科室信息等）。这些文档是 RAG 的基础。

LangChain4J 提供了文档加载、切分、向量化和存储的工具。虽然具体的 RAG 配置代码（如 `EmbeddingStoreConfig.java` 和文档处理流程）没有在当前分析中完全展示，但从 `XiaozhiAgent` 的配置可以看出，项目旨在利用这些知识文件：

1.  **文档加载与切分:** 使用 LangChain4J 的 `DocumentLoader` 加载 `knowledge` 目录下的文件，并使用 `DocumentSplitter` 将长文档切分成小块。
2.  **向量化:** 使用配置的 Embedding Model（`application.properties` 中配置了阿里百炼的 `text-embedding-v3`）将文本块转换为向量。
3.  **向量存储:** 将文本块及其向量存储到向量数据库中。`XiaozhiAgent` 配置指向了 `contentRetrieverXiaozhiPincone`，表明可能使用了 Pinecone 云向量数据库。但也可能存在本地存储的配置（如 `EmbeddingStoreConfig` 中可能配置了内存或文件系统的向量存储）。
4.  **检索:** 当用户提问时，先将问题向量化，然后在向量数据库中查找最相似的文本块。
5.  **增强:** 将检索到的文本块内容整合到发送给 LLM 的提示中。
6.  **生成:** LLM 基于原始问题和检索到的上下文信息生成回答。

### 4.4 函数调用实现 (`AppointmentTools`)

`AppointmentTools` 类包含了带有 `@Tool` 注解的方法，这些方法可以被 LLM 调用。

```java
// 示例（基于 LangChain4J 常见用法推测）
public class AppointmentTools {

    @Autowired
    private AppointmentService appointmentService;

    @Tool("查询指定日期和科室的可用预约时段")
    public List<String> findAvailableSlots(String date, String department) {
        // 调用 AppointmentService 查询数据库
        return appointmentService.getAvailableSlots(date, department);
    }

    @Tool("为用户创建预约")
    public String createAppointment(String patientName, String date, String time, String department) {
        // 调用 AppointmentService 创建预约
        return appointmentService.createAppointment(patientName, date, time, department);
    }
}
```

当用户的提问涉及到查询或创建预约时，LLM 可以识别出意图，并决定调用这些工具方法。LangChain4J 负责处理 LLM 的函数调用请求，执行相应的 Java 方法，并将结果返回给 LLM，LLM 再根据结果生成最终的自然语言回复。

### 4.5 前端交互 (`ChatWindow.vue`)

前端的核心是 `ChatWindow.vue` 组件，它负责：

1.  **展示聊天界面:** 显示用户消息和 AI 回复。
2.  **接收用户输入:** 提供输入框供用户输入问题。
3.  **调用后端 API:** 当用户发送消息时，向后端的 `/xiaozhi/chat` 发送 POST 请求，包含用户消息和会话 ID (`memoryId`)。
4.  **处理流式响应:** 接收并逐步显示后端返回的流式数据。
5.  **管理会话:** 可能生成或管理 `memoryId` 以维持多轮对话的上下文。

## 5. 配置与依赖

### 5.1 关键配置 (`application.properties`)

*   `server.port`: 后端服务端口 (8080)。
*   `langchain4j.community.dashscope.*`: 配置阿里百炼平台的 API Key 和模型名称 (qwen-max, qwen-plus, text-embedding-v3)。API Key 通过环境变量 `${LANGCHAIN4J_KEY}` 获取。
*   `spring.data.mongodb.uri`: MongoDB 连接字符串，用于存储聊天记忆。
*   `spring.datasource.*`: MySQL 数据库连接信息，用于存储业务数据（如预约）。
*   `mybatis-plus.configuration.log-impl`: 开启 Mybatis-Plus 的 SQL 日志打印。
*   `logging.level.root`: 全局日志级别。

### 5.2 主要依赖 (`pom.xml`)

*   `spring-boot-starter-web`: Spring Boot Web 核心。
*   `spring-boot-starter-test`: 测试框架。
*   `knife4j-openapi3-jakarta-spring-boot-starter`: API 文档工具。
*   `langchain4j-open-ai-spring-boot-starter`: LangChain4J OpenAI 兼容接口的 Spring Boot Starter。
*   `langchain4j-community-dashscope-spring-boot-starter`: LangChain4J 阿里百炼 (DashScope) 的 Spring Boot Starter。
*   `langchain4j-spring-boot-starter`: LangChain4J 核心功能的 Spring Boot Starter (提供 @AiService 等)。
*   `spring-boot-starter-data-mongodb`: Spring Data MongoDB 支持。
*   `mysql-connector-j`: MySQL JDBC 驱动。
*   `mybatis-plus-spring-boot3-starter`: Mybatis-Plus 集成。
*   `langchain4j-document-parser-apache-pdfbox`: LangChain4J PDF 文档解析器。
*   `langchain4j-easy-rag`: (可能用于简化 RAG 配置，但核心 RAG 依赖于 core 和 embedding store)
*   `langchain4j-pinecone`: LangChain4J Pinecone 向量数据库集成。
*   `spring-boot-starter-webflux`, `langchain4j-reactor`: 支持响应式编程和流式输出。

## 6. 总结

LangChain4J-XiaozhiAI 是一个功能相对完善的 AI 助手演示项目，有效地展示了如何利用 LangChain4J 框架在 Java (Spring Boot) 环境下构建集成 LLM 的应用。它涵盖了 LangChain4J 的多项核心功能，包括：

*   **多模型支持:** 通过配置文件灵活接入不同的 LLM（阿里百炼 Qwen, DeepSeek 等）。
*   **AI 服务抽象:** 使用 `@AiService` 简化了与 LLM 的交互逻辑。
*   **流式输出:** 提供了流畅的实时聊天体验。
*   **持久化聊天记忆:** 利用 MongoDB 存储对话历史，实现上下文感知。
*   **工具调用:** 使 LLM 能够与外部系统（通过 Java 代码）交互，执行查询、创建预约等操作。
*   **检索增强生成 (RAG):** 通过集成向量数据库和本地知识文件，使 LLM 能够基于特定领域的知识回答问题。

该项目为学习和实践如何在 Java 生态中应用大模型技术提供了一个很好的起点和参考案例，特别是在医疗等需要结合特定知识和工具的领域。
