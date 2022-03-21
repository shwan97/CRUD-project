package crud.website.service;

import crud.website.domain.Member;
import crud.website.dto.MemberDto;

public interface MemberService {

    void join(MemberDto memberDto);

    Member login(MemberDto memberDto);

    void change(Long memberId, String newNickname);

}
