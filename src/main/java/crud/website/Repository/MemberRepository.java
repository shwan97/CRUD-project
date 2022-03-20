package crud.website.Repository;

import crud.website.domain.Member;
import crud.website.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequiredArgsConstructor
@Repository
public class MemberRepository {

    @PersistenceContext
    private final EntityManager em;

    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    public Member save(MemberDto userDto) {
        Member member = userDto.toEntity();
        em.persist(member);

        return member;
    }

    public void delete(MemberDto userDto) {
        String email = userDto.getEmail();
        int number = em.createQuery("delete from Member m where m.email= :email")
                .setParameter("email", email)
                .executeUpdate();
    }
}
