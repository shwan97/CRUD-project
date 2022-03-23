package crud.website.service;

import crud.website.domain.Post;
import crud.website.dto.PostDto;
import crud.website.dto.PostResponseDto;

import java.util.List;

public interface PostService {

    public List<PostResponseDto> findAll();

    public List<PostResponseDto> findListByPageId(Long pageId);

    public PostDto findById(Long id);

    public Long findLastPageNum();
}
