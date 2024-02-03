package toyproject.genshin.teybatguide.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.genshin.teybatguide.exception.TeybatException;

@Slf4j
@Service
@Transactional(readOnly = true)
public class ImageService {

    public FileSystemResource openFile(String imageUrl) {
        try {
            String path = "/Users/ahnnayeong/img" + imageUrl;
            System.out.println(path);
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
