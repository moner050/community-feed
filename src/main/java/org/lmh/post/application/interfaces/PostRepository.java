package org.lmh.post.application.interfaces;

import org.lmh.post.domain.Post;

public interface PostRepository {

    Post save(Post post);
    Post findById(Long id);
}
