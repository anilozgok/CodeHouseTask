package org.anilcan.model.response;

import org.anilcan.model.domain.ContactRecord;

import java.util.List;

public record ContactListResponse(List<ContactRecord> contacts) {
}
