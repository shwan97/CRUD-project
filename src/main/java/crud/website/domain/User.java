package crud.website.domain;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@RequiredArgsConstructor
@Builder
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
}
