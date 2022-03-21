package crud.website.controller;

import crud.website.domain.Member;
import crud.website.dto.MemberDto;
import crud.website.service.MemberService;
import crud.website.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PostService postService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(MemberDto memberDto, HttpSession session) {
        Member loginMember = memberService.login(memberDto);// exception 터질 수 있음
        session.setAttribute("sessionUser", loginMember);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("sessionUser");
        return "redirect:/";
    }

    @GetMapping("/mypage")
    public String mypage(HttpSession session, Model model) {
        Member member = (Member) session.getAttribute("sessionUser");
        if (member == null) {
            throw new IllegalStateException("로그인되어 있지 않은 상태입니다.");
        }
        model.addAttribute("email", member.getEmail());
        model.addAttribute("nickname", member.getNickname());
        return "mypage";
    }

    @PatchMapping("/mypage")
    public String changeMemberInfo(HttpSession session, @RequestBody String nickname) {
        Member member = (Member) session.getAttribute("sessionUser");
        System.out.println("nickname = " + nickname);
        if (member == null) {
            throw new IllegalStateException("로그인되어 있지 않은 상태입니다.");
        }
        memberService.change(member.getId(), nickname);
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String create(MemberDto memberDto) {
        System.out.println(memberDto);
        memberService.join(memberDto); // exception 터질 수 있음

        return "redirect:/";
    }
}
