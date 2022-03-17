package crud.website.domain;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseTimeEntity {

    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;
}
