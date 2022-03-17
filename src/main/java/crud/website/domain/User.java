package crud.website.domain;

import com.sun.istack.NotNull;
import crud.website.dto.UserDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id @GeneratedValue
    @Column(name = "USER_ID")
    private Long id;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String nickname;

    @Builder
    public User(Long id, String email, String password, String nickname) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public void update(UserDto userDto) {
        this.nickname = userDto.getNickname();
    }
}
