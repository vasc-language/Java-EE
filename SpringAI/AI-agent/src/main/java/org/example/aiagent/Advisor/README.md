# Spring AOP Advisor 示例

本项目实现了两个基于Spring AOP的自定义Advisor：

1. **权限校验Advisor** - 用于拦截特定方法，验证用户权限
2. **违禁词校验Advisor** - 用于检查文本内容是否包含敏感词汇

## 功能特点

- 基于Spring AOP和AspectJ实现
- 使用AspectJ表达式定义切入点
- 提供详细的日志记录
- 包含自定义注解支持
- 实现异常处理机制

## 项目结构

```
org.example.aiagent.Advisor
├── annotation            # 自定义注解
│   ├── PermissionCheck.java
│   └── SensitiveWordsCheck.java
├── config                # 配置类
│   └── AopConfig.java
├── exception             # 异常类
│   ├── PermissionDeniedException.java
│   └── SensitiveWordsException.java
├── util                  # 工具类
│   ├── SecurityContextUtil.java
│   └── SensitiveWordsUtil.java
├── example               # 使用示例
│   ├── ContentServiceExample.java
│   └── AopExampleController.java
├── PermissionCheckAdvisor.java    # 权限校验切面
└── SensitiveWordsCheckAdvisor.java # 敏感词校验切面
```

## 使用方法

### 1. 权限校验

为需要进行权限校验的方法添加`@PermissionCheck`注解：

```java
// 需要ADMIN权限
@PermissionCheck({"ADMIN"})
public void adminMethod() {
    // 方法实现
}

// 需要多个权限
@PermissionCheck({"READ", "WRITE"})
public void complexMethod() {
    // 方法实现
}

// 跳过权限校验
@PermissionCheck(skipCheck = true)
public void publicMethod() {
    // 方法实现
}
```

### 2. 敏感词校验

为需要进行敏感词检查的方法添加`@SensitiveWordsCheck`注解：

```java
// 检查所有String类型参数，发现敏感词抛出异常
@SensitiveWordsCheck(level = SensitiveWordsCheck.CheckLevel.ERROR)
public String processContent(String content, Long userId) {
    // 方法实现
}

// 只检查指定参数，发现敏感词只记录警告
@SensitiveWordsCheck(paramNames = {"content", "title"}, level = SensitiveWordsCheck.CheckLevel.WARN)
public String updateContent(String content, String title, Long id) {
    // 方法实现
}
```

## 异常处理

当权限校验或敏感词检查失败时，会抛出相应的异常：

- `PermissionDeniedException` - 权限不足时抛出
- `SensitiveWordsException` - 检测到敏感词时抛出

建议在Controller层捕获这些异常并返回适当的HTTP状态码。

## 扩展功能

### 自定义敏感词

```java
// 添加自定义敏感词
SensitiveWordsUtil.addSensitiveWord("自定义敏感词");

// 移除敏感词
SensitiveWordsUtil.removeSensitiveWord("政治");

// 重置为默认敏感词列表
SensitiveWordsUtil.resetSensitiveWords();
```

### 模拟用户权限（仅用于测试）

```java
// 添加权限
SecurityContextUtil.addPermissions("ADMIN");

// 移除权限
SecurityContextUtil.removePermission("WRITE");

// 重置为默认权限
SecurityContextUtil.resetPermissions();
```

## API测试示例

### 添加内容（需要ADMIN权限）

```
POST /api/aop-example/content?content=测试内容&userId=1
```

### 更新内容（需要WRITE权限）

```
PUT /api/aop-example/content/1?content=更新内容
```

### 查看内容（需要READ权限）

```
GET /api/aop-example/content/1
```

### 删除内容（需要ADMIN权限）

```
DELETE /api/aop-example/content/1
```

### 添加用户权限

```
POST /api/aop-example/permissions?permission=ADMIN
```

### 检查当前用户权限

```
GET /api/aop-example/permissions
``` 