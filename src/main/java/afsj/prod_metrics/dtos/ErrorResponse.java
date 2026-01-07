package afsj.prod_metrics.dtos;

import java.time.LocalDateTime;

public record ErrorResponse(
        int status,
        String message,
        String path,
        LocalDateTime timestamp
) {
}
