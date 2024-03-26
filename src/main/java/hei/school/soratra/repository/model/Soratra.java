package hei.school.soratra.repository.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

@Getter
@Setter
@Entity
public class Soratra {
    @Id
    private String id;
    private String originalFileUrl;
    private String transformedUrl;
}
