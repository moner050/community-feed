package org.lmh.post.repository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.lmh.post.application.interfaces.PostRepository;
import org.lmh.post.domain.Post;
import org.lmh.post.repository.entity.post.PostEntity;
import org.lmh.post.repository.jpa.JpaPostRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    private final JpaPostRepository jpaPostRepository;

    @Override
    @Transactional
    public Post save(Post post) {
        PostEntity postEntity = new PostEntity(post);

        // 아이디 값이 있으면 UPDATE
        if(post.getId() != null) {
            jpaPostRepository.updatePostEntity(postEntity);
            return postEntity.toPost();
        }

        postEntity = jpaPostRepository.save(postEntity);
        return postEntity.toPost();
    }

    @Override
    public Post findById(Long id) {
        PostEntity postEntity = jpaPostRepository.findById(id).orElseThrow();
        return postEntity.toPost();
    }
}
