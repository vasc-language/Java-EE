import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2024-12-29
 * Time: 16:46
 */
class Point {
/*    public Point(double x, double y) {
        // 直接表示
    }
    public Point(Double r, double a) {
        // 用极坐标表示
    }*/
}
class PointFactory {
    public static Point makePointByXY(double x, double y) {
        Point p = new Point();
        // 通过 x 和 y 给 p 进行属性设置
        return p;
    }
    public static Point makePointByRA(double r, double a) {
        Point p = new Point();
        // 通过 r 和 a 给 p 进行属性设置
        return p;
    }
}
public class Demo2 {
    public static void main(String[] args) {
        // final TimeUnit hours = TimeUnit.HOURS;
        Point point = PointFactory.makePointByXY(1, 2);
    }
}
