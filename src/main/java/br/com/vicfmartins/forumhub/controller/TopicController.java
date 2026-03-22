package br.com.vicfmartins.forumhub.controller;

import br.com.vicfmartins.forumhub.dto.TopicCreateRequest;
import br.com.vicfmartins.forumhub.dto.TopicResponse;
import br.com.vicfmartins.forumhub.dto.TopicUpdateRequest;
import br.com.vicfmartins.forumhub.service.TopicService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topicos")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TopicResponse create(@Valid @RequestBody TopicCreateRequest request, Authentication authentication) {
        return topicService.create(request, authentication.getName());
    }

    @GetMapping
    public Page<TopicResponse> list(
            @PageableDefault(size = 10, sort = "createdAt") Pageable pageable
    ) {
        return topicService.list(pageable);
    }

    @GetMapping("/{id}")
    public TopicResponse findById(@PathVariable Long id) {
        return topicService.findById(id);
    }

    @PutMapping("/{id}")
    public TopicResponse update(
            @PathVariable Long id,
            @Valid @RequestBody TopicUpdateRequest request,
            Authentication authentication
    ) {
        return topicService.update(id, request, authentication.getName());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id, Authentication authentication) {
        topicService.delete(id, authentication.getName());
    }
}
