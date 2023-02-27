package com.spring.tutorial.redditclone.services.custom;

import com.spring.tutorial.redditclone.exeptions.SpringRedditException;
import com.spring.tutorial.redditclone.model.NotificationEmail;
import lombok.AllArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;



/**
 *
 *
 * @SMTP-service
 *
 */
@Service
@AllArgsConstructor
@Slf4j
public class MailService {

    private final JavaMailSender mailSender;
    private final MailContentBuilder mailContentBuilder;

    public void sendEmail(NotificationEmail notificationEmail) {
        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom("springreddit@email.com");
            mimeMessageHelper.setTo(notificationEmail.getRecipient());
            mimeMessageHelper.setSubject(notificationEmail.getSubject());
            mimeMessageHelper.setText(mailContentBuilder.build(notificationEmail.getBody()));
        };
        try {
            mailSender.send(mimeMessagePreparator);
            log.info("Activation email sent to:" , notificationEmail.getRecipient());
        } catch (MailException ex) {
            ex.printStackTrace();
            throw new SpringRedditException("Exception occurred when sending mail to " + notificationEmail.getRecipient(), ex);
        }
    }
}
