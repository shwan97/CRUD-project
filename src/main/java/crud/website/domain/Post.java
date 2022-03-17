package crud.website.domain;

import com.sun.istack.NotNull;
import crud.website.dto.PostRequestSaveDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Post extends BaseTimeEntity{

    @Id @GeneratedValue
    @Column(name = "POST_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @NotNull
    private String title;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String content;

    @Builder
    public Post(Long id, User user, String title, String content) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.content = content;
    }

    public void update(PostRequestSaveDto postDto) {
        this.title = postDto.getTitle();
        this.content = postDto.getContent();
    }
}
