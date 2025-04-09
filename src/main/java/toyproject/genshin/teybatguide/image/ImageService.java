package toyproject.genshin.teybatguide.image;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.genshin.teybatguide.exception.TeybatException;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ImageService {

    private final ImageValue imageValue;

    public FileSystemResource openFile(String folderName, String imageUrl) {
        try {
            String path = String.join("/", imageValue.getImagePath(), folderName, imageUrl);
            log.info(path);
            FileSystemResource resource = new FileSystemResource(path);

            if (!resource.exists()) {
                throw new TeybatException("이미지 파일 없음");
            }

            return resource;
        } catch (Exception e) {
            throw new TeybatException("이미지 없음");
        }
    }
}
