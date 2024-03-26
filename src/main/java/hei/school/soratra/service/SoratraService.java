package hei.school.soratra.service;

import hei.school.soratra.file.BucketComponent;
import hei.school.soratra.repository.SoratraRepository;
import hei.school.soratra.repository.model.Soratra;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;

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

        Soratra soratra = new Soratra(fileId, presignedUrl.toString(), null);
        soratraRepository.save(soratra);
    }
}
