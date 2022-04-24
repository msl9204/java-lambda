package chapter10.service;

import chapter10.model.UserNewWay;

public interface EmailProvider {
    String getEmail(UserNewWay user);
}
