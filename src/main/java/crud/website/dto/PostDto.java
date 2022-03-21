package crud.website.dto;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class PostDto {

    private Long id;
    private String title;
    private String author;
    private String content;
    private String modifiedTime;
}
