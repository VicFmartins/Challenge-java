package br.com.vicfmartins.forumhub.dto;

import java.time.LocalDateTime;

public record TopicResponse(
        Long id,
        String title,
        String message,
        String course,
        String authorName,
        String authorEmail,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
