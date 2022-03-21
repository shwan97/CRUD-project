package crud.website.service;

import crud.website.Repository.PostRepository;
import crud.website.domain.Post;
import crud.website.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;

    @Transactional
    public List<PostResponseDto> findAll() {
        return postRepository.findAll();
    }
}
