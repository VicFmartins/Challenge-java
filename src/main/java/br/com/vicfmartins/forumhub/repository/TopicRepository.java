package br.com.vicfmartins.forumhub.repository;

import br.com.vicfmartins.forumhub.domain.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    @Override
    @EntityGraph(attributePaths = "author")
    Page<Topic> findAll(Pageable pageable);

    @Override
    @EntityGraph(attributePaths = "author")
    Optional<Topic> findById(Long id);
}
