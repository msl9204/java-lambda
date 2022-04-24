package chapter10.service;

import chapter10.model.UserNewWay;

public class MakeMoreFriendsEmailProvider implements EmailProvider {

    @Override
    public String getEmail(UserNewWay user) {
        return "'Make More Friends' email for " + user.getName();
    }
}
