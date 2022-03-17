package crud.website.domain;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@Builder
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
}
