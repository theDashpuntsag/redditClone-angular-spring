package com.sprint.tutorial.redditclone.repository;

import com.sprint.tutorial.redditclone.model.VertificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VertificationTokenRepository extends JpaRepository<VertificationToken, Long> {
    Optional<VertificationToken> findByToken(String token);
}
