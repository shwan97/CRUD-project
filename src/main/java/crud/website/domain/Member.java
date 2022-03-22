package crud.website.domain;

import com.sun.istack.NotNull;
import crud.website.dto.MemberDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String nickname;

    @OneToMany(mappedBy = "member", orphanRemoval = true)
    private List<Post> postList = new ArrayList<>();

    @OneToMany(mappedBy = "member", orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @Builder
    public Member(Long id, String email, String password, String nickname) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public void update(MemberDto memberDto) {
        this.email = memberDto.getEmail();
        this.nickname = memberDto.getNickname();
        this.password = memberDto.getPassword();
    }

    public boolean isValidPassword(String password) {
        return this.password.equals(password);
    }
}
