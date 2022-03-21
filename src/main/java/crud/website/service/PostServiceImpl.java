package crud.website.service;

import crud.website.Repository.PostRepository;
import crud.website.domain.Post;
import crud.website.dto.PostDto;
import crud.website.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);

    @Transactional
    public List<PostResponseDto> findAll() {
        return postRepository.findAll();
    }

    @Transactional
    public PostDto findById(Long id) {
        Post post = postRepository.findById(id);
        PostDto postDto = PostDto.builder()
                .id(id)
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getMember().getNickname())
                .modifiedTime(post.getModifiedTime().format(formatter))
                .build();
        return postDto;
    }
}
