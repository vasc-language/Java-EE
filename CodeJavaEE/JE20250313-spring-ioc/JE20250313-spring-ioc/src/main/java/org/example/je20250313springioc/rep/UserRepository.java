package org.example.je20250313springioc.rep;

import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-03-20
 * Time: 21:24
 */
@Repository
public class UserRepository {
    public void print() {
        System.out.println("do repository");
    }
}
