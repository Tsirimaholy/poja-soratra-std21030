package hei.school.soratra.repository.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Soratra {
    @Id
    private String id;
    private String originalFileUrl;
    private String transformedUrl;
    private String originalBucketKey;
    private String transformedBucketKey;
}
