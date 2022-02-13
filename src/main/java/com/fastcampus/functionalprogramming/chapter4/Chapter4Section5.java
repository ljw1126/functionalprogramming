package com.fastcampus.functionalprogramming.chapter4;

import java.util.Comparator;
import java.util.List;
import com.fastcampus.functionalprogramming.chapter4.model.User;
import java.util.ArrayList;
import java.util.Collections;

/**
 *  Comparator 인터페이스
 *  음수면 o1 < o2
 *  0이면 o1 = o2
 *  양수이면 o1 > o2
 */
public class Chapter4Section5 {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User(3, "alice"));
        users.add(new User(1, "charlie"));
        users.add(new User(5, "bob"));
        //System.out.println(users);

        // 숫자 오름차순 정렬
        Comparator<User> idComparator
                    = (u1, u2) -> u1.getId() - u2.getId();
        Collections.sort(users, idComparator);
        System.out.println(users);

        // 이름 사전 순 정렬
        Collections.sort(users, (u1,u2)->u1.getName().compareTo(u2.getName()));
        System.out.println(users);
    }
}
