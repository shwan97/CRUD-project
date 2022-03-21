package crud.website.controller;

import crud.website.dto.PostResponseDto;
import crud.website.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostService postService;

    @GetMapping("/")
    public String home(Model model) {
        List<PostResponseDto> posts = postService.findAll();
        model.addAttribute("posts", posts);
        return "index";
    }
}
