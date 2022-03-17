package crud.website.Repository;

import crud.website.domain.User;
import crud.website.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequiredArgsConstructor
@Repository
public class UserRepository {

    @PersistenceContext
    private final EntityManager em;

    public User findById(Long id) {
        return em.find(User.class, id);
    }

    public User save(UserDto userDto) {
        User user = userDto.toEntity();
        em.persist(user);

        return user;
    }
}
