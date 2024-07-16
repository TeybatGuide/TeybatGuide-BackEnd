package toyproject.genshin.teybatguide.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import toyproject.genshin.teybatguide.common.restDocs.AbstractRestDocsTests;
import toyproject.genshin.teybatguide.service.WeaponService;

@Slf4j
@AutoConfigureRestDocs
@WebMvcTest(WeaponController.class)
public class WeaponControllerTest extends AbstractRestDocsTests {

    @MockBean
    private WeaponService weaponService;

    @Test
    public void getWeaponListTest() {
        //given

        //when

        //then

    }

}
