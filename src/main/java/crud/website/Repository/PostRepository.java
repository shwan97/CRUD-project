package crud.website.Repository;

import crud.website.domain.Post;
import crud.website.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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

    public List<PostResponseDto> findAll() {
        List<PostResponseDto> resultList = em.createQuery("select new crud.website.dto.PostResponseDto(p.id, p.title, p.member.nickname, p.modifiedTime) from Post p inner join p.member m", PostResponseDto.class)
                .getResultList();

        return resultList;
    }

    public List<PostResponseDto> findListByPageId(Long pageId) {
        int startPosition = (int)(pageId - 1) * 10;
        List<PostResponseDto> resultList = em.createQuery("select new crud.website.dto.PostResponseDto(p.id, p.title, p.member.nickname, p.modifiedTime) from Post p inner join p.member m", PostResponseDto.class)
                .setFirstResult(startPosition)
                .setMaxResults(10)
                .getResultList();

        return resultList;
    }

    public Long findTotalCount() {
        return (Long)em.createQuery("select count(p) from Post p").getSingleResult();
    }
}
