package toyproject.genshin.teybatguide.image;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ImageValue {

    @Value("${path.image}")
    private String imagePath;

}
