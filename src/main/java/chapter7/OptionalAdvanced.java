package chapter7;

import chapter7.model.User;

import java.util.Optional;

public class OptionalAdvanced {

    public static void main(String[] args) {
        Optional<User> maybeUser = Optional.ofNullable(maybeGetUser(true));
        maybeUser.ifPresent(user -> System.out.println(user));

        Optional<Integer> maybeId = Optional.ofNullable(maybeGetUser(true))
                .map(user -> user.getId());
        maybeId.ifPresent(System.out::println);

        String username = Optional.ofNullable(maybeGetUser(true))
                .map(User::getName)
                .map(name -> "The name is " + name)
                .orElse("Name is Empty");
        System.out.println(username);

        Optional<String> maybeEmail = Optional.ofNullable(maybeGetUser(true))
                .flatMap(User::getEmailAddress);
        maybeEmail.ifPresent(System.out::println);

    }

    public static User maybeGetUser(boolean returnUser) {
        if (returnUser) {
            return new User()
                    .setId(1001)
                    .setName("Alice")
                    .setEmailAddress("alice@fastcampus@co.kr")
                    .setVerified(false);
        }
        return null;
    }
}
