package com.sprint.tutorial.redditclone.exeptions;

public class SpringRedditExeption extends RuntimeException{
    public SpringRedditExeption(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public SpringRedditExeption(String exMessage) {
        super(exMessage);
    }
}
