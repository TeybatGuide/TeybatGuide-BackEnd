package toyproject.genshin.teybatguide.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toyproject.genshin.teybatguide.controller.dto.ImageRequest;
import toyproject.genshin.teybatguide.service.ImageService;

@RestController
@RequestMapping("/api/image")
@RequiredArgsConstructor
public class ImageController {

    public final ImageService imageService;

    @GetMapping
    public ResponseEntity<Resource> getImage(@RequestBody ImageRequest request) {

        Resource resource = imageService.openFile(request.imageUrls());
        String contentType = "image/webp";
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_ENCODING, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
