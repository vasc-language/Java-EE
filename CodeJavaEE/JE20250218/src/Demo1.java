/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-02-18
 * Time: 22:05
 */

import java.io.File;
import java.io.IOException;

/**
 * 解释一下为什么不能直接操作硬盘，而是通过文件的形式间接操作
 * 1. 太乱了，找不到东西: 你想找一个特定的文件（比如你写的作业），但在乱糟糟的仓库里，你根本不知道它被塞在哪里了，
 *  要找到它简直像大海捞针！
 * 2. 容易搞坏东西: 你在乱翻乱找的过程中，可能会不小心碰到其他重要的东西，把它们弄坏或者弄丢了，就像不小心删除了
 *  重要的电脑程序一样！
 * 3. 太复杂，普通人搞不定: 要管理这么乱的仓库，你需要非常专业的人员，了解仓库的每一个角落、每一种物品的存放规则，
 *  普通人根本不知道怎么下手。
 *
 * 为了解决这个问题，电脑引入了“文件”和“文件夹”的概念，这就想给仓库创建了一套完整的管理系统：
 * 1. 建立货架（文件夹）：仓库不再是乱糟糟的一片，而是建立了很多货架（文件夹），可以把东西分类好，有“文档”货架，
 *  “图片”货架，“视频”货架。
 * 2. 建立标签（文件名）：每个东西都贴上标签（文件名），告诉你这是什么东西，比如"我的作业.docx"，“风景照片.jpg”
 *  "搞笑视频.mp4"。
 * 3. 建立目录（文件系统）：仓库操作员（文件系统）还创建一个详细的目录，记录每个东西放在哪个货架的哪个位置。
 *
 * 不允许你直接操作硬盘，而是间接通过文件的形式来操作，就好比：
 * 不让你直接进入杂乱无章的仓库里乱翻，而是通过仓库的目录管理系统，有条理，清晰的找到你想要的东西。
 */
public class Demo1 {
    public static void main(String[] args) {
        System.out.println("欲与天公试比高");
    }
    public static void main1(String[] args) throws IOException {
        // File file = new File("D:/Java/JavaEE/test20250220.txt");
        File file = new File("./test20250220.txt");
        // getParent() 获取父目录路径（如果没有父目录，就会返回 null）
        System.out.println(file.getParent());
        // getName() 获取文件名或目录名（返回路径的在最后一部分）
        System.out.println(file.getName());
        // getPath() 获取创建 File 对象时使用的路径字符串（原样返回构造方法中传入的路径）
        System.out.println(file.getPath());
        // getAbsolutePath() 获取绝对路径（如果是相对路径，会相对于当前工作目录进行解析，可能会进行部分规范化）
        System.out.println(file.getAbsolutePath());
        // getCanonicalPath() 获取规范化的绝对路径，解析符号链接，移除冗余成分
        // （最权威的路径表示，会进行完全规范化，可能会抛出 IOException 异常，性能可能低于 getAbsolutePath，因为它需要访问文件系统）
        System.out.println(file.getCanonicalPath());
    }
}

/**
 *什么是文本文件（像日记本）
 * 1. 主要内容是文字（字符）
 * 2. 人可以直接理解（用文本编辑器打开）
 * 3. 记录的是文字信息（日记、文章、代码指令）
 * 例子：.txt, .html, .js, .c 等
 *
 *什么是二进制文件（像照片、音乐、电影）
 * 1. 内容不仅仅是文字，还可以是图像、声音、程序等各种数据
 * 2. 人不能直接阅读理解（用记事本打开时乱码）
 * 3. 需要专门的程序（看图软件、播放器、程序加载器）来解析和使用
 * 4. 记录的是各种类型的数据（图像、音乐、代码指令）
 * 例子：.jsp, .png, .mp3, mp4, .exe, .zip 等
 *
 * 二者之间的关联关系
 * 1. 底层都是二进制：电脑上的东西，最终都是用 0 和 1 （二进制）来表示
 * 2. 解释方式不同：文本文件和二进制文件真正在于如何去解释和使用这堆 0 1 二进制数据
 *  1）文本文件：我们把二进制数据按照字符编码（例如 UFT-8）来解析，理解成文字来展示给我们看
 *  2）二进制文件：我们把文件格式的规则（例如 JPG 图像格式、MP3 音频格式）来解释，让专门的程序
 *    （看图软件、播放器）去处理这些数据，呈现出图像，声音等结果给我们
 *
 * 如何区分
 * 用记事本打开
 * 1. 如果打开看到的是你能读懂的文字，那很可能是文本文件
 * 2. 如果打开后，看到的是乱码，读不懂，很可能二进制文件
 */
