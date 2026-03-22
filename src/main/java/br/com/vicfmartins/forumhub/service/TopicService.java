package br.com.vicfmartins.forumhub.service;

import br.com.vicfmartins.forumhub.domain.ForumUser;
import br.com.vicfmartins.forumhub.domain.Topic;
import br.com.vicfmartins.forumhub.dto.TopicCreateRequest;
import br.com.vicfmartins.forumhub.dto.TopicResponse;
import br.com.vicfmartins.forumhub.dto.TopicUpdateRequest;
import br.com.vicfmartins.forumhub.exception.ResourceNotFoundException;
import br.com.vicfmartins.forumhub.repository.ForumUserRepository;
import br.com.vicfmartins.forumhub.repository.TopicRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TopicService {

    private final TopicRepository topicRepository;
    private final ForumUserRepository userRepository;

    public TopicService(TopicRepository topicRepository, ForumUserRepository userRepository) {
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public TopicResponse create(TopicCreateRequest request, String userEmail) {
        ForumUser author = findUserByEmail(userEmail);

        Topic topic = new Topic(
                request.title(),
                request.message(),
                request.course(),
                author
        );

        return toResponse(topicRepository.save(topic));
    }

    @Transactional(readOnly = true)
    public Page<TopicResponse> list(Pageable pageable) {
        return topicRepository.findAll(pageable).map(this::toResponse);
    }

    @Transactional(readOnly = true)
    public TopicResponse findById(Long id) {
        return toResponse(findTopic(id));
    }

    @Transactional
    public TopicResponse update(Long id, TopicUpdateRequest request, String userEmail) {
        Topic topic = findTopic(id);
        validateOwnership(topic, userEmail);

        topic.update(request.title(), request.message(), request.course());
        return toResponse(topicRepository.save(topic));
    }

    @Transactional
    public void delete(Long id, String userEmail) {
        Topic topic = findTopic(id);
        validateOwnership(topic, userEmail);
        topicRepository.delete(topic);
    }

    private Topic findTopic(Long id) {
        return topicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Topico nao encontrado."));
    }

    private ForumUser findUserByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario autenticado nao encontrado."));
    }

    private void validateOwnership(Topic topic, String userEmail) {
        if (!topic.getAuthor().getEmail().equalsIgnoreCase(userEmail)) {
            throw new AccessDeniedException("Somente o autor pode alterar este topico.");
        }
    }

    private TopicResponse toResponse(Topic topic) {
        return new TopicResponse(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getCourse(),
                topic.getAuthor().getName(),
                topic.getAuthor().getEmail(),
                topic.getCreatedAt(),
                topic.getUpdatedAt()
        );
    }
}
