import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created with IntelliJ IDEA.
 * Description: 图的深度优先遍历
 * User: 姚东名
 * Date: 2025-03-27
 * Time: 20:16
 */
public class Graph1 {
    private int V; // 顶点数
    private LinkedList<Integer>[] adj; // 邻接表

    public Graph1(int v) {
        V = v;
        this.adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    // 添加边
    public void addEdge(int v, int w) {
        // 二者均为顶点，相连就出现了边
        adj[v].add(w);
    }

    // 使用深度优先遍历图
    public void DFS(int v) {
        // 初始化这四个顶点都为 false，都未被访问过
        // [false, false, false, false]
        boolean[] visited = new boolean[V];
        DFSUtil(v, visited);
    }

    private void DFSUtil(int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(v + " ");
        ListIterator<Integer> iterator = adj[v].listIterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            if (!visited[i]) {
                DFSUtil(i, visited);
            }
        }
    }

    public static void main(String[] args) {
        Graph1 graph1 = new Graph1(4);
        graph1.addEdge(0,1);
        graph1.addEdge(0,2);
        graph1.addEdge(1, 2);
        graph1.addEdge(2,0);
        graph1.addEdge(2,3);
        graph1.addEdge(3,3);
        System.out.println("深度优先遍历(从顶点2开始):");//从顶点2开始读用
        graph1.DFS(2);//从顶点2开始读用
    }
}
