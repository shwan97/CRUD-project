package crud.website.dto;

import crud.website.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDto {
    private String email;
    private String password;
    private String nickname;

    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .build();
    }
}
