package crud.website.Repository;

import crud.website.domain.Member;
import crud.website.dto.MemberDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
//@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("멤버가 저장된다.")
    public void saveMember() {
        //given
        MemberDto memberDto = MemberDto.builder()
                .email("dexrf@gmail.com")
                .password("1234")
                .nickname("hi")
                .build();
        Member saveMember = memberRepository.save(memberDto);

        //when
        Member findMember = memberRepository.findById(saveMember.getId());

        //then
        Assertions.assertThat(findMember).isEqualTo(saveMember);
        Assertions.assertThat(findMember.getEmail()).isEqualTo("dexrf@gmail.com");
    }

    @Test
    @DisplayName("멤버가 삭제된다.")
    public void deleteMember() {
        //given
        MemberDto memberDto = MemberDto.builder()
                .email("dexrf@gmail.com")
                .password("1234")
                .nickname("hi")
                .build();
        Member saveMember = memberRepository.save(memberDto);
        em.clear();

        //when
        memberRepository.delete(memberDto);
        Member findMember = memberRepository.findById(saveMember.getId());

        //then
        Assertions.assertThat(findMember).isEqualTo(null);
    }
}