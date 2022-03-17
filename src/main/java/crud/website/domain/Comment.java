package crud.website.domain;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@Builder
public class Comment extends BaseTimeEntity{
    @Id @GeneratedValue
    @Column(name = "COMMENT_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;

    @NotNull
    private String content;
}
