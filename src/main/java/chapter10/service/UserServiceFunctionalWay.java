package chapter10.service;

import chapter10.model.UserNewWay;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class UserServiceFunctionalWay {
    private final Predicate<UserNewWay> validateUser;
    private final Consumer<UserNewWay> writeToDB;

    public UserServiceFunctionalWay(Predicate<UserNewWay> validateUser, Consumer<UserNewWay> writeToDB) {
        this.validateUser = validateUser;
        this.writeToDB = writeToDB;
    }

    public void createUser(UserNewWay user) {
        if (validateUser.test(user)) {
            writeToDB.accept(user);
        } else {
            System.out.println("Cannot create user");
        }
    }
}
