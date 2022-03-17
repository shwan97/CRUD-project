package crud.website.Repository;

import crud.website.domain.Comment;
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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class CommentRepositoryTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("댓글이 저장된다.")
    public void commentSave() {

        //given
        UserDto userDto = UserDto.builder()
                .email("dexrf@gmail.com")
                .password("1234")
                .nickname("hi")
                .build();
        User saveUser = userRepository.save(userDto);
        Post savePost = Post.builder().title("title").content("content").user(saveUser).build();
        postRepository.save(savePost);
        Comment comment = Comment.builder().content("댓글이야").post(savePost).user(saveUser).build();
        commentRepository.save(comment);

        //when
        Comment findComment = commentRepository.findById(comment.getId());

        //then
        Assertions.assertThat(findComment).isEqualTo(comment);
        Assertions.assertThat(findComment.getContent()).isEqualTo(comment.getContent());
    }

    @Test
    @DisplayName("댓글이 삭제된다.")
    public void deleteComment() {

        //given
        UserDto userDto = UserDto.builder()
                .email("dexrf@gmail.com")
                .password("1234")
                .nickname("hi")
                .build();
        User saveUser = userRepository.save(userDto);
        Post savePost = Post.builder().title("title").content("content").user(saveUser).build();
        postRepository.save(savePost);
        Comment comment = Comment.builder().content("댓글이야").post(savePost).user(saveUser).build();
        commentRepository.save(comment);

        //when
        commentRepository.delete(comment.getId());

        //then
        Comment findComment = em.find(Comment.class, comment.getId());
        Assertions.assertThat(findComment).isEqualTo(null);
    }

}