package mk.finki.ukim.wp.libraryapi.web.dto;

import java.time.LocalDateTime;

public record ApiError(
        String message,
        int status,
        LocalDateTime timestamp
) {
    public ApiError(String message, int status) {
        this(message, status, LocalDateTime.now());
    }
}