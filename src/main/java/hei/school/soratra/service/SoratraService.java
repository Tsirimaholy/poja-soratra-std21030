package hei.school.soratra.service;

import hei.school.soratra.file.BucketComponent;
import hei.school.soratra.repository.SoratraRepository;
import hei.school.soratra.repository.model.MappedSoratra;
import hei.school.soratra.repository.model.Soratra;
import jakarta.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SoratraService {
  private SoratraRepository soratraRepository;
  private final BucketComponent bucketComponent;
  private static final String IMAGE_BUCKET_DIRECTORY = "file/";

  @Transactional
  public void uploadFile(File soratraFile, String fileName, String fileId) throws IOException {
    String bucketKey = IMAGE_BUCKET_DIRECTORY + fileName;
    bucketComponent.upload(soratraFile, bucketKey);
    URL presignedUrl = bucketComponent.presign(bucketKey, Duration.ofDays(1));

    Soratra soratra = new Soratra(fileId, presignedUrl.toString(), null, bucketKey, null);
    soratraRepository.save(soratra);
  }

  public MappedSoratra getSOratra(Soratra soratra) throws IOException {
    String originalBucketKey = soratra.getOriginalBucketKey();

    File lower = bucketComponent.download(originalBucketKey);
    FileUtil.transformFileToUpperCase(lower);

    UUID uuid = UUID.randomUUID();
    String stringUUID = uuid.toString();
    String bucketKey = IMAGE_BUCKET_DIRECTORY + stringUUID;
    bucketComponent.upload(lower, bucketKey);

    URL originalUrl = bucketComponent.presign(soratra.getOriginalBucketKey(), Duration.ofDays(1));
    URL newUrl = bucketComponent.presign(bucketKey, Duration.ofDays(1));
    return MappedSoratra.builder()
        .transformedUrl(newUrl.toString())
        .originalUrl(originalUrl.toString())
        .build();
  }
}
