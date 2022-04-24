package chapter10;

import chapter10.model.UserNewWay;
import chapter10.service.InternalUserService;
import chapter10.service.UserService;
import chapter10.service.UserServiceFunctionalWay;

import java.util.Arrays;

public class TemplateMethodPatternPractice {

    public static void main(String[] args) {

        UserNewWay alice = UserNewWay.builder(1, "Alice")
                .with(builder -> {
                    builder.emailAddress = "alice@fastcampus.co.kr";
                    builder.isVerified = false;
                    builder.friendUserIds = Arrays.asList(201, 202, 203, 204, 211, 212, 213, 214);
                }).build();

        UserService userService = new UserService();
        InternalUserService internalUserService = new InternalUserService();

//        userService.createUser(alice);
//        internalUserService.createUser(alice);

        UserServiceFunctionalWay userServiceFunctionalWay = new UserServiceFunctionalWay(
                user -> {
                    System.out.println("Validating user " + user.getName());
                    return user.getName() != null && user.getEmailAddress().isPresent();
                },
                user -> {
                    System.out.println("Writing user " + user.getName() + " to DB");
                }
        );
        userServiceFunctionalWay.createUser(alice);
    }
}
