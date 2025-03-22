/**
 * Created with IntelliJ IDEA.
 * Description: 网站用户活跃度分析：假设你在追踪一个拥有1000万用户的网站，需要记录每个用户在过去30天是否登录过。
 * User: 姚东名
 * Date: 2025-03-16
 * Time: 10:08
 */

public class UserActivityTracker {
    private Bitmap[] dailyActivity; // 30天的活跃记录
    // 创建3个位图，每个表示一天的用户活跃度
    public UserActivityTracker() {
        dailyActivity = new Bitmap[30];
        for (int i = 0; i < 30; i++) {
            dailyActivity[i] = new Bitmap(1_000_000); // 每一天的用户容量都得达到 1千万
        }
    }

    // 记录用户在某天登录
    public void recordLogin(int userId, int dayOffset) {
        dailyActivity[dayOffset].set(userId);
    }

    // 检查用户在30天内是否活跃（至少有一天是在线的）
    public boolean isActiveInLast30Days(int userId) {
        for (int i = 0; i < 30; i++) {
            if (dailyActivity[i].get(userId)) {
                return true;
            }
        }
        return false;
    }

    // 计算某天的用户活跃度
    public int countActiveUsersOnDay(int dayOffset) {
        int count = 0;
        for (int i = 0; i < 1_000_000; i++) {
            if (dailyActivity[dayOffset].get(i)) {
                count++;
            }
        }
        return count;
    }
}
