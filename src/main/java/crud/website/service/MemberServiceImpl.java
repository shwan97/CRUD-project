package crud.website.service;

import crud.website.Repository.MemberRepository;
import crud.website.domain.Member;
import crud.website.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Transactional
    public void join(MemberDto memberDto){
        // 1. 비밀번호 암호화 필요
        // 2. 중복 회원 찾는 로직 필요
        if(memberRepository.existsByEmail(memberDto))
            throw new IllegalStateException("동일한 이메일을 가진 유저가 이미 존재합니다.");
        else if (memberRepository.existsByNickname(memberDto)) {
            throw new IllegalStateException("동일한 닉네임을 가진 유저가 이미 존재합니다.");
        }
        memberRepository.save(memberDto);
    }

    @Transactional
    public Member login(MemberDto memberDto) {
        Member findMember = memberRepository.findByEmail(memberDto);
        if (findMember == null) {
            throw new IllegalStateException("해당하는 아이디를 가진 회원이 없습니다.");
        } else if (!findMember.isValidPassword(memberDto.getPassword())) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        } else {
            return findMember;
        }
    }

    @Transactional
    public void update(Long memberId, String newNickname) {
        Member member = memberRepository.findById(memberId);
        member.updateNickname(newNickname);
    }
}
