package com.spring.tutorial.redditclone.exeptions;

public class SpringRedditException extends RuntimeException{
    public SpringRedditException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public SpringRedditException(String exMessage) {
        super(exMessage);
    }
}
