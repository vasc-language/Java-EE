package Test;

import java.util.ArrayList;
import java.util.List;

public class TSP {
    static int n; // 城市数量
    static int[][] dist; // 邻接矩阵，城市间距离
    static boolean[] visited; // 访问标记
    static int bestCost = Integer.MAX_VALUE; // 全局最优路径长度
    static List<Integer> bestPath = new ArrayList<>(); // 全局最优路径

    public static void solveTSP(int[][] distanceMatrix) {
        n = distanceMatrix.length;
        dist = distanceMatrix;
        visited = new boolean[n];
        // 可预设起点为城市0
        visited[0] = true;
        List<Integer> path = new ArrayList<>();
        path.add(0);
        // 初始界：使用贪心近邻算法得到一个可行解长度bestCost
        bestCost = greedyInitialUpperBound();
        // 从城市0开始深度优先搜索
        dfs(0, 1, 0, path);
        // 输出bestCost和bestPath即可
    }

    // 递归DFS函数：cur为当前城市索引，count为已访问城市数，cost为当前路径长度
    static void dfs(int cur, int count, int cost, List<Integer> path) {
        if (count == n) {
            // 所有城市均已访问，回到起点
            cost += dist[cur][0];
            if (cost < bestCost) {
                bestCost = cost;
                bestPath = new ArrayList<>(path);
                bestPath.add(0);
            }
            return;
        }
        // 对每个未访问城市尝试深入
        for (int j = 1; j < n; j++) {
            if (!visited[j]) {
                int newCost = cost + dist[cur][j];
                // 剪枝：若当前成本已超过全局最优，无需继续
                if (newCost >= bestCost) continue;
                // 选择城市j作为下一个访问点
                visited[j] = true;
                path.add(j);
                dfs(j, count + 1, newCost, path);
                // 回溯：撤销选择
                visited[j] = false;
                path.remove(path.size() - 1);
            }
        }
    }

    // 简单的贪心初始上界（以城市0为起点）
    static int greedyInitialUpperBound() {
        boolean[] seen = new boolean[n];
        int u = 0, cost = 0;
        seen[u] = true;
        for (int k = 1; k < n; k++) {
            // 选择最近的未访问城市
            int next = -1, minDist = Integer.MAX_VALUE;
            for (int v = 0; v < n; v++) {
                if (!seen[v] && dist[u][v] < minDist) {
                    minDist = dist[u][v];
                    next = v;
                }
            }
            if (next == -1) break;
            cost += minDist;
            u = next;
            seen[u] = true;
        }
        // 回到起点
        cost += dist[u][0];
        return cost;
    }
}
