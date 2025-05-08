package org.example.springblogdemo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-06
 * Time: 8:16
 */

public class DateUtils {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println("date: "+ date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String format = dateFormat.format(date);
        System.out.println("format: "+format);

    }
}
