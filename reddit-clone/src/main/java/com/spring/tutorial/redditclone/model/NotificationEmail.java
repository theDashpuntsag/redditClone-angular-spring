package com.spring.tutorial.redditclone.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationEmail {
    private String subject;
    private String recipient;
    private String body;

    @Override
    public String toString() {
        return "NotificationEmail{" +
                "subject='" + subject + '\'' +
                ", recipient='" + recipient + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
