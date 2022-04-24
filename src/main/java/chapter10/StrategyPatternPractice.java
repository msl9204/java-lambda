package chapter10;

import chapter10.model.UserNewWay;
import chapter10.service.EmailProvider;
import chapter10.service.EmailSender;
import chapter10.service.MakeMoreFriendsEmailProvider;
import chapter10.service.VerifyYourEmailAddressEmailProvider;
import chapter6.model.User;

import java.util.Arrays;
import java.util.List;

public class StrategyPatternPractice {

    public static void main(String[] args) {

        UserNewWay user1 = UserNewWay.builder(1, "Alice")
                .with(builder -> {
                    builder.emailAddress = "alice@fastcampus.co.kr";
                    builder.isVerified = false;
                    builder.friendUserIds = Arrays.asList(201, 202, 203, 204, 211, 212, 213, 214);
                }).build();

        UserNewWay user2 = UserNewWay.builder(2, "Bob")
                .with(builder -> {
                    builder.emailAddress = "bob@fastcampus.co.kr";
                    builder.isVerified = true;
                    builder.friendUserIds = Arrays.asList(212, 213, 214);
                }).build();

        UserNewWay user3 = UserNewWay.builder(3, "Charlie")
                .with(builder -> {
                    builder.emailAddress = "charlie@fastcampus.co.kr";
                    builder.isVerified = true;
                    builder.friendUserIds = Arrays.asList(201, 202, 203, 204, 211, 212);
                }).build();

        List<UserNewWay> users = Arrays.asList(user1, user2, user3);

        EmailSender emailSender = new EmailSender();
        EmailProvider verifyYourEmailAddressEmailProvider = new VerifyYourEmailAddressEmailProvider();
        EmailProvider makeMoreFriendsEmailProvider = new MakeMoreFriendsEmailProvider();

        emailSender.setEmailProvider(verifyYourEmailAddressEmailProvider);
        users.stream()
                .filter(user -> !user.isVerified())
                .forEach(emailSender::sendEmail);

        emailSender.setEmailProvider(makeMoreFriendsEmailProvider);
        users.stream()
                .filter(UserNewWay::isVerified)
                .filter(user -> user.getFriendUserIds().size() < 5)
                .forEach(emailSender::sendEmail);

        emailSender.setEmailProvider(user -> "'Play With Friends' email for " + user.getName());
        users.stream()
                .filter(UserNewWay::isVerified)
                .filter(user -> user.getFriendUserIds().size() > 5)
                .forEach(emailSender::sendEmail);


    }
}
