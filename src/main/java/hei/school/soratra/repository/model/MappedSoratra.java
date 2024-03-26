package hei.school.soratra.repository.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MappedSoratra {
    private String originalUrl;
    private String transformedUrl;
}
