package org.anilcan.model.dto.response;

import org.anilcan.utility.ExceptionMessage;

import java.time.LocalDateTime;

public record ErrorResponseModel(ExceptionMessage exception, LocalDateTime occurredAt) {
}
