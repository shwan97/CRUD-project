package crud.website.controller;

import crud.website.domain.Member;
import crud.website.dto.PostDto;
import crud.website.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/post/view/{id}")
    public String detailPage(@PathVariable Long id, HttpSession session, Model model) {
        PostDto postDto = postService.findById(id);
        Member loginMember = (Member) session.getAttribute("sessionUser");
        if (loginMember == null || !loginMember.getNickname().equals(postDto.getAuthor())) {
            model.addAttribute("post", postDto);
        } else {
            model.addAttribute("post", postDto);
            model.addAttribute("isOwner", true);
        }
        return "postdetail";
    }
}
