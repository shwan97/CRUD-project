package crud.website.domain;

import com.sun.istack.NotNull;
import crud.website.dto.CommentDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends BaseTimeEntity{
    @Id @GeneratedValue
    @Column(name = "COMMENT_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Member_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;

    @NotNull
    private String content;

    @Builder
    public Comment(Long id, Member member, Post post, String content) {
        this.id = id;
        this.member = member;
        this.post = post;
        this.content = content;
    }

    public void update(CommentDto commentDto) {
        this.content = commentDto.getContent();
    }
}
