package crud.website.Repository;

import crud.website.domain.Post;
import crud.website.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    @PersistenceContext
    private final EntityManager em;

    public Post findById(Long id) {
        return em.find(Post.class, id);
    }

    public Post save(Post entity) {
        em.persist(entity);
        return entity;
    }

    public void delete(Long id) {
        Post post = em.find(Post.class, id);
        em.remove(post);
    }
}
