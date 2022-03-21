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
                .email("gradish@gmail.com")
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
                .email("gradish@gmail.com")
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

    @Test
    @DisplayName("이메일로 회원을 조회한다.")
    public void findMemberNyEmail() {
        final String email = "gradish@gmail.com";
        //given
        MemberDto memberDto = MemberDto.builder()
                .email(email)
                .password("1234")
                .nickname("hi")
                .build();
        Member saveMember = memberRepository.save(memberDto);

        //when
        Member findMember = memberRepository.findByEmail(memberDto);

        //then
        Assertions.assertThat(saveMember).isEqualTo(findMember);
        Assertions.assertThat(saveMember.getId()).isEqualTo(findMember.getId());
        Assertions.assertThat(saveMember.getNickname()).isEqualTo(findMember.getNickname());
    }

    @Test
    @DisplayName("이메일 중복조회")
    public void ExistsByEmail() {
        //given
        final String existsInDataBase = "solaris@gmail.com";
        final String notExistsinDataBase = "MacOs@gmail.com";
        MemberDto notExistsDto = MemberDto.builder()
                .build();
        MemberDto existDto = MemberDto.builder()
                .email(existsInDataBase)
                .password("1234")
                .nickname("hi")
                .build();
        memberRepository.save(existDto);

        //when
        boolean exists = memberRepository.existsByEmail(existDto);
        boolean notExists = memberRepository.existsByEmail(notExistsDto);

        //then
        Assertions.assertThat(exists).isEqualTo(true);
        Assertions.assertThat(notExists).isEqualTo(false);
    }

    @Test
    @DisplayName("닉네임 중복조회")
    public void ExistsByNickname() {
        //given
        final String existsInDataBase = "existNickname";
        final String notExistsinDataBase = "NotExistNickname";
        MemberDto notExistsDto = MemberDto.builder()
                .build();
        MemberDto existDto = MemberDto.builder()
                .email("solaris@gmail.com")
                .password("1234")
                .nickname(existsInDataBase)
                .build();
        memberRepository.save(existDto);

        //when
        boolean exists = memberRepository.existsByNickname(existDto);
        boolean notExists = memberRepository.existsByNickname(notExistsDto);

        //then
        Assertions.assertThat(exists).isEqualTo(true);
        Assertions.assertThat(notExists).isEqualTo(false);
    }
}