package crud.website.service;

import crud.website.domain.Post;
import crud.website.dto.PostDto;
import crud.website.dto.PostResponseDto;

import java.util.List;

public interface PostService {

    List<PostResponseDto> findAll();
    List<PostResponseDto> findListByPageId(Long pageId);
    PostDto findById(Long id);
    Long findLastPageNum();
}
