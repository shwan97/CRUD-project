package crud.website.Repository;

import crud.website.domain.Member;
import crud.website.domain.Post;
import crud.website.dto.MemberDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MemberRepository userRepository;

    @Test
    @DisplayName("게시글이 저장된다.")
    public void savePost() {

        //given
        MemberDto userDto = MemberDto.builder()
                .email("dexrf@gmail.com")
                .password("1234")
                .nickname("hi")
                .build();
        Member saveMember = userRepository.save(userDto);
        Post savePost = Post.builder().title("title").content("content").member(saveMember).build();
        postRepository.save(savePost);

        //when
        Post findPost = postRepository.findById(savePost.getId());

        //then
        Assertions.assertThat(savePost).isEqualTo(findPost);
    }

    @Test
    @DisplayName("게시글이 삭제된다.")
    public void deletePost() {

        //given
        MemberDto memberDto = MemberDto.builder()
                .email("dexrf@gmail.com")
                .password("1234")
                .nickname("hi")
                .build();
        Member saveMember = userRepository.save(memberDto);
        Post savePost = Post.builder().title("title").content("content").member(saveMember).build();
        postRepository.save(savePost);

        //when
        postRepository.delete(savePost.getId());

        //then
        Post findPost = postRepository.findById(savePost.getId());
        Assertions.assertThat(findPost).isEqualTo(null);
    }

    @Test
    @DisplayName("모든 게시글을 조회한다.")
    public void findAll() {

        final int testDataNumber = 9;
        final int count = 10;
        //given
        MemberDto memberDto = MemberDto.builder()
                .email("dexrf@gmail.com")
                .password("1234")
                .nickname("hi")
                .build();
        Member saveMember = userRepository.save(memberDto);

        //when
        for (int idx = 0; idx < count; idx++) {
            Post savePost = Post.builder().title("title" + idx).content("content" + idx).member(saveMember).build();
            postRepository.save(savePost);
        }

        //then
        Assertions.assertThat(postRepository.findAll().size()).isEqualTo(count + testDataNumber);
    }

    @Test
    @DisplayName("게시글 개수를 확인한다.")
    public void checkTotalCount() {
        System.out.println("totalCount = " + postRepository.findTotalCount());
    }
}