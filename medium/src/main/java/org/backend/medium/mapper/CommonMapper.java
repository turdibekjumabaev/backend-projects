package org.backend.medium.mapper;

import org.backend.medium.entity.Tag;
import org.backend.medium.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public interface CommonMapper<D,E> {

    D toDto(E e);
    E toEntity(D d);
    default List<String> mapTags(List<Tag> tags) {
        return tags.stream()
                .map(Tag::getTag)
                .collect(Collectors.toList());
    }

    default Integer mapLikes(List<User> likes) {
        return likes.size();
    }
}