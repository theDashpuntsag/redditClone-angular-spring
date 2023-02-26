package com.spring.tutorial.redditclone.exeptions;

public class SubredditNotFoundExeption extends RuntimeException{
    public SubredditNotFoundExeption(String message) {
        super(message);
    }
}
