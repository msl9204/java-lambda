package chapter10.service;

import chapter10.model.UserNewWay;

public abstract class AbstractUserService {
    protected abstract boolean validateUser(UserNewWay user);

    protected abstract void writeToDB(UserNewWay user);

    public void createUser(UserNewWay user) {
        if (validateUser(user)) {
            writeToDB(user);
        } else {
            System.out.println("Cannot create user");
        }
    }
}
