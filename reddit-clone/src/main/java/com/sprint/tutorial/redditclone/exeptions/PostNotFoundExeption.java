package com.sprint.tutorial.redditclone.exeptions;

public class PostNotFoundExeption extends RuntimeException{
    public PostNotFoundExeption (String message) {
        super(message);
    }
}
