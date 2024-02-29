package org.backend.medium.service;

import org.backend.medium.entity.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TagServices {
    List<Tag> addTags(List<String> tags);
    ResponseEntity<List<String>> popularTags();
}