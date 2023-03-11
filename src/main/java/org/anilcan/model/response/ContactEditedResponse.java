package org.anilcan.model.response;

import org.anilcan.model.domain.ContactRecord;

public record ContactEditedResponse(Long id, ContactRecord info) { }
