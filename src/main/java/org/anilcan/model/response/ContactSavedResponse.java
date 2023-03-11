package org.anilcan.model.response;

import org.anilcan.model.domain.ContactRecord;

public record ContactSavedResponse(Long id, ContactRecord info){ }
