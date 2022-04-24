package chapter10.service;

import chapter10.model.UserNewWay;

public class UserService extends AbstractUserService {

    @Override
    protected boolean validateUser(UserNewWay user) {
        System.out.println("Validating user " + user.getName());
        return user.getName() != null && user.getEmailAddress().isPresent();
    }

    @Override
    protected void writeToDB(UserNewWay user) {
        System.out.println("Writing user " + user.getName() + " to DB");
    }
}
