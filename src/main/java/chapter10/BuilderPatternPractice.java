package chapter10;

import chapter10.model.User;
import chapter10.model.UserNewWay;

public class BuilderPatternPractice {

    public static void main(String[] args) {

        User user = User.builder(1, "Alice")
                .withEmailAddress("alice@fastcampus.co.kr")
                .withVerify(true)
                .build();

        System.out.println(user);

        UserNewWay user2 = UserNewWay.builder(2, "John")
                .with(builder -> {
                    builder.emailAddress = "john@fastcampus.co.kr";
                    builder.isVerified = true;
                }).build();
        System.out.println(user2);
    }
}
