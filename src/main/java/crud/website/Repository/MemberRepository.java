package crud.website.Repository;

import crud.website.domain.Member;
import crud.website.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class MemberRepository {

    @PersistenceContext
    private final EntityManager em;

    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    public Member findByEmail(MemberDto memberDto) {
        List resultList = em.createQuery("select m from Member m where m.email= :email")
                .setParameter("email", memberDto.getEmail())
                .getResultList();

//        if (resultList.size() != 1)
//            throw new IllegalStateException("아이디와 일치하는 회원이 없습니다.");

        return (Member) resultList.get(0);
    }

    public boolean existsByEmail(MemberDto memberDto) {
        List resultList = em.createQuery("select m from Member m where m.email= :email")
                .setParameter("email", memberDto.getEmail())
                .getResultList();

        return resultList.size() != 0;
    }

    public boolean existsByNickname(MemberDto memberDto) {
        List resultList = em.createQuery("select m from Member m where m.nickname= :nickname")
                .setParameter("nickname", memberDto.getNickname())
                .getResultList();

        return resultList.size() != 0;
    }

    public Member save(MemberDto userDto) {
        Member member = userDto.toEntity();
        em.persist(member);

        return member;
    }

    public void delete(Member member) {
        member = em.contains(member) ? member : em.merge(member);
        em.remove(member);
    }
}
