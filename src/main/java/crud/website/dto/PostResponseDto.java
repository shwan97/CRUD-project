package crud.website.dto;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class PostResponseDto {
    private Long id;
    private String title;
    private String author;
    private LocalDateTime modifiedTime;
}
