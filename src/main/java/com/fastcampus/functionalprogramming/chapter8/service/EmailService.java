package com.fastcampus.functionalprogramming.chapter8.service;


import com.fastcampus.functionalprogramming.chapter7.model.User;

public class EmailService {
    // 8.8
    public void sendPlayWithFriendsEmail(User user){
        user.getEmailAddress().ifPresent(
                email -> System.out.println("Sending `Play With Friends` email to " + email)
        );
    }
    // 8.8
    public void sendMakeMoreFriendsEmail(User user){
        user.getEmailAddress().ifPresent(
                email -> System.out.println("Sending `Make More Friends` email to " + email)
        );
    }
    // 8.9
    public void sendVerifyYourEmailEmail(User user){
        user.getEmailAddress().ifPresent(
                email -> System.out.println("Sending `Verify Your Email` email to " + email)
        );
    }
}
