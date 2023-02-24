package com.sprint.tutorial.redditclone.repository;

import com.sprint.tutorial.redditclone.model.Post;
import com.sprint.tutorial.redditclone.model.User;
import com.sprint.tutorial.redditclone.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}
