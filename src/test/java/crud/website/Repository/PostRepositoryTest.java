package crud.website.Repository;

import crud.website.domain.Post;
import crud.website.domain.User;
import crud.website.dto.UserDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("게시글이 저장된다.")
    public void savePost(){

        //given
        UserDto userDto = UserDto.builder()
                .email("dexrf@gmail.com")
                .password("1234")
                .nickname("hi")
                .build();
        User saveUser = userRepository.save(userDto);
        Post savePost = Post.builder().title("title").content("content").user(saveUser).build();
        postRepository.save(savePost);

        //when
        Post findPost = postRepository.findById(savePost.getId());

        //then
        Assertions.assertThat(savePost).isEqualTo(findPost);
    }

    @Test
    @DisplayName("게시글이 삭제된다.")
    public void deletePost(){

        //given
        UserDto userDto = UserDto.builder()
                .email("dexrf@gmail.com")
                .password("1234")
                .nickname("hi")
                .build();
        User saveUser = userRepository.save(userDto);
        Post savePost = Post.builder().title("title").content("content").user(saveUser).build();
        postRepository.save(savePost);

        //when
        postRepository.delete(savePost.getId());

        //then
        Post findPost = postRepository.findById(savePost.getId());
        Assertions.assertThat(findPost).isEqualTo(null);
    }

}