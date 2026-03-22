package br.com.vicfmartins.forumhub.repository;

import br.com.vicfmartins.forumhub.domain.ForumUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ForumUserRepository extends JpaRepository<ForumUser, Long> {

    Optional<ForumUser> findByEmailIgnoreCase(String email);

    boolean existsByEmailIgnoreCase(String email);
}
