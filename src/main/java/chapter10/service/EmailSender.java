package chapter10.service;

import chapter10.model.UserNewWay;

public class EmailSender {
    private EmailProvider emailProvider;

    public EmailSender setEmailProvider(EmailProvider emailProvider) {
        this.emailProvider = emailProvider;
        return this;
    }

    public void sendEmail(UserNewWay user) {
        String email = emailProvider.getEmail(user);
        System.out.println("Sending " + email);
    }
}
