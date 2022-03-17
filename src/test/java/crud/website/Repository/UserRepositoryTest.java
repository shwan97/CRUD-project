package crud.website.Repository;

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

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
//@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("유저가 저장된다.")
    public void saveUser() {
        //given
        UserDto userDto = UserDto.builder()
                .email("dexrf@gmail.com")
                .password("1234")
                .nickname("hi")
                .build();
        User saveUser = userRepository.save(userDto);

        //when
        User findUser = userRepository.findById(saveUser.getId());

        //then
        Assertions.assertThat(findUser).isEqualTo(saveUser);
        Assertions.assertThat(findUser.getEmail()).isEqualTo("dexrf@gmail.com");
    }

    @Test
    @DisplayName("유저가 삭제된다.")
    public void deleteUser() {
        //given
        UserDto userDto = UserDto.builder()
                .email("dexrf@gmail.com")
                .password("1234")
                .nickname("hi")
                .build();
        User saveUser = userRepository.save(userDto);
        em.clear();

        //when
        userRepository.delete(userDto);
        User findUser = userRepository.findById(saveUser.getId());

        //then
        Assertions.assertThat(findUser).isEqualTo(null);
    }
}