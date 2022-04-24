package chapter8;

import chapter8.model.User;
import chapter8.service.EmailService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ParallelStreamPractice {

    public static void main(String[] args) {

        User user1 = new User()
                .setId(101)
                .setName("Alice")
                .setVerified(true)
                .setEmailAddress("alice@fastcampus.co.kr");

        User user2 = new User()
                .setId(102)
                .setName("Bob")
                .setVerified(false)
                .setEmailAddress("bob@fastcampus.co.kr");

        User user3 = new User()
                .setId(103)
                .setName("Charlie")
                .setVerified(false)
                .setEmailAddress("charlie@fastcampus.co.kr");

        User user4 = new User()
                .setId(104)
                .setName("David")
                .setVerified(true)
                .setEmailAddress("david@fastcampus.co.kr");

        User user5 = new User()
                .setId(105)
                .setName("Eve")
                .setVerified(false)
                .setEmailAddress("eve@fastcampus.co.kr");

        User user6 = new User()
                .setId(106)
                .setName("Frank")
                .setVerified(false)
                .setEmailAddress("frank@fastcampus.co.kr");

        List<User> users = Arrays.asList(user1, user2, user3, user4, user5, user6);

        long startTime = System.currentTimeMillis();
        EmailService emailService = new EmailService();

        users.stream()
                .filter(user -> !user.isVerified())
                .forEach(emailService::sendVerifyYourEmailEmail);
        long endTime = System.currentTimeMillis();

        System.out.println("Sequential: " + (endTime - startTime) + "ms");

        startTime = System.currentTimeMillis();

        users.stream().parallel()
                .filter(user -> !user.isVerified())
                .forEach(user -> {
                    emailService.sendVerifyYourEmailEmail(user);
                });
        endTime = System.currentTimeMillis();

        System.out.println("Sequential: " + (endTime - startTime) + "ms");

        List<User> processedUser = users.parallelStream()
                .map(user -> {
                    System.out.println("Capitalize user name for user " + user.getId());
                    user.setName(user.getName().toUpperCase());
                    return user;
                })
                .map(user -> {
                    System.out.println("Set 'isVerified' to true for user " + user.getId());
                    user.setVerified(true);
                    return user;
                })
                .collect(Collectors.toList());

        System.out.println(processedUser);
    }
}
