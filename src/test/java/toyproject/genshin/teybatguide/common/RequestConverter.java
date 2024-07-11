package toyproject.genshin.teybatguide.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.Collection;

@Slf4j
public class RequestConverter {

    public static <T> MultiValueMap<String, String> convertRequestToMultiValueMap(T request) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        Arrays.stream(request.getClass().getDeclaredFields()).forEach(field -> {
            field.setAccessible(true);
            try {
                Object value = field.get(request);
                if (value != null) {
                    if (value instanceof Collection) {
                        for (Object item : (Collection<?>) value) {
                            map.add(field.getName(), item.toString());
                        }
                    } else {
                        map.add(field.getName(), value.toString());
                    }
                }
            } catch (IllegalAccessException e) {
                log.error(e.getMessage());
            }
        });

        return map;
    }

}
