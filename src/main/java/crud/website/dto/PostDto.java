package crud.website.dto;

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
