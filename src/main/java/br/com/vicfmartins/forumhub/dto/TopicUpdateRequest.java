package br.com.vicfmartins.forumhub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TopicUpdateRequest(
        @NotBlank @Size(min = 5, max = 150) String title,
        @NotBlank @Size(min = 10, max = 4000) String message,
        @NotBlank @Size(min = 2, max = 120) String course
) {
}
