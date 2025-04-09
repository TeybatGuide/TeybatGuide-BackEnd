package toyproject.genshin.teybatguide.image;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {

    public final ImageService imageService;

    @PostMapping("/{folder_name}/{image_url}")
    public ResponseEntity<Resource> getImage(
        @PathVariable("folder_name") String folderName,
        @PathVariable("image_url") String imageUrl
    ) {
        Resource resource = imageService.openFile(folderName, imageUrl);
        String contentType = "image/webp";
        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .header(HttpHeaders.CONTENT_ENCODING, "attachment; filename=\"" + resource.getFilename() + "\"")
            .body(resource);
    }

}
