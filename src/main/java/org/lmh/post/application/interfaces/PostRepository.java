package org.lmh.post.application.interfaces;

import org.lmh.post.domain.Post;

import java.util.Optional;

public interface PostRepository {

    Post save(Post post);
    Optional<Post> findById(Long id);
}
