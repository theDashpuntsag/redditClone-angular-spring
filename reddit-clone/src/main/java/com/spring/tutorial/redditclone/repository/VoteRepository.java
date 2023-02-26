package com.spring.tutorial.redditclone.repository;

import com.spring.tutorial.redditclone.model.Post;
import com.spring.tutorial.redditclone.model.User;
import com.spring.tutorial.redditclone.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}
