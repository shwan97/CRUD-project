package crud.website.controller;

import crud.website.Pagination;
import crud.website.dto.PostResponseDto;
import crud.website.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostService postService;

    @GetMapping("/")
    public String home(Model model, @RequestParam(value="pageId", required = false) Long pageId) {
        pageId = (pageId == null) ? 1 : pageId;
        Long lastPageNum = postService.findLastPageNum();
        if(pageId > lastPageNum) throw new IllegalStateException("해당하는 페이지가 없습니다.");

        Pagination pagination = new Pagination(pageId, postService.findLastPageNum());
        List<PostResponseDto> posts = postService.findListByPageId(pageId);
        model.addAttribute("posts", posts);
        model.addAttribute("pagination", pagination);
        return "index";
    }
}
