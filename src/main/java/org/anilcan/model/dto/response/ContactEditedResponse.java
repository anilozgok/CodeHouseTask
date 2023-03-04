package org.anilcan.model.dto.response;

import org.anilcan.model.domain.ContactRecord;

public record ContactEditedResponse(Long id, ContactRecord info) { }
