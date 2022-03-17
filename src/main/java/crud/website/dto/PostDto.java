package crud.website.dto;

import crud.website.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class PostDto {

    private Long id;
    private String title;
    private String content;
}
