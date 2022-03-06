package com.fastcampus.functionalprogramming.chapter8;

import com.fastcampus.functionalprogramming.chapter7.model.User;
import com.fastcampus.functionalprogramming.chapter8.service.EmailService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 8.9 For Each
 * - 매개변수가 Consumer 이기 때문에 return 이 없음
 * - Sequential (순차) Stream
 */
public class Chapter8Section9 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3,5,2,1);
        numbers.stream().forEach(
                number-> System.out.println("The number is " + number)
        );
        // 동일한 결과
        numbers.forEach(number-> System.out.println("The number is " + number));

        User user1 = new User()
                .setId(101)
                .setName("Aaul")
                .setVerified(true)
                .setEmailAddress("alice@fastcampus.co.kr")
                .setFriendUserIds(Arrays.asList(201,202,203,204,205,206));

        User user2 = new User()
                .setId(102)
                .setName("David")
                .setVerified(false)
                .setEmailAddress("bob@fastcampus.co.kr")
                .setFriendUserIds(Arrays.asList(204,205,206));

        User user3 = new User()
                .setId(103)
                .setName("John")
                .setVerified(false)
                .setEmailAddress("charlie@fastcampus.co.kr")
                .setFriendUserIds(Arrays.asList(204,205,207));

        List<User> users = Arrays.asList(user1,user2,user3);
        EmailService emailService = new EmailService();
        //verified false인 유저에게만 이메일 (이메일 검증을 해주세요!)
        users.stream().filter(user -> !user.isVerified())
                .forEach(emailService::sendVerifyYourEmailEmail);
                //.forEach(user -> emailService.sendVerifyYourEmailEmail(user));
        //지난 예제도 이걸 적용해서 한줄로 만들 수 있다함! 해봄 !

        /**
         * for문에 index가 필요한 경우
         * -> stream에 IntStream 사용하면 쉽게 할 수 있다네
         */

        IntStream.range(0,users.size()).forEach(i-> {
            User user = users.get(i); //i라는 인덱스 가져옴
            System.out.println("Name :"+user.getName()+"/at index : " + i);
        });
    }
}
