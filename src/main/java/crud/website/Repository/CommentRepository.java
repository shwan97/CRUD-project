package crud.website.Repository;

import crud.website.domain.Comment;
import crud.website.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

    @PersistenceContext
    private final EntityManager em;

    public Comment findById(Long id) {
        return em.find(Comment.class, id);
    }

    public Comment save(Comment entity) {
        em.persist(entity);
        return entity;
    }
}
