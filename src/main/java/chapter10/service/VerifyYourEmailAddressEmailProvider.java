package chapter10.service;

import chapter10.model.UserNewWay;

public class VerifyYourEmailAddressEmailProvider implements EmailProvider {

    @Override
    public String getEmail(UserNewWay user) {
        return "'Verify Your Email Address' email for " + user.getName();
    }
}
