package toyproject.genshin.teybatguide.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import toyproject.genshin.teybatguide.common.RequestConverter;
import toyproject.genshin.teybatguide.common.restDocs.AbstractRestDocsTests;
import toyproject.genshin.teybatguide.common.restDocs.Field;
import toyproject.genshin.teybatguide.common.restDocs.RestDocsUtil;
import toyproject.genshin.teybatguide.controller.dto.weapons.WeaponListRequest;
import toyproject.genshin.teybatguide.controller.dto.weapons.WeaponListResponse;
import toyproject.genshin.teybatguide.domain.Weapon;
import toyproject.genshin.teybatguide.domain.value.Stars;
import toyproject.genshin.teybatguide.domain.value.WeaponOptions;
import toyproject.genshin.teybatguide.domain.value.WeaponType;
import toyproject.genshin.teybatguide.service.WeaponService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.web.servlet.mvc.method.annotation.SseEmitter.event;
import static toyproject.genshin.teybatguide.common.data.Types.*;

@Slf4j
@AutoConfigureRestDocs
@WebMvcTest(WeaponController.class)
public class WeaponControllerTest extends AbstractRestDocsTests {

    @MockBean
    private WeaponService weaponService;

    @Test
    public void getWeaponListTest() throws Exception {
        //given
        Weapon weapon1 = buildWeapon("TestWeapon1", Stars.FIVE, WeaponType.BOWS, WeaponOptions.CRIT_DMG);
        Weapon weapon2 = buildWeapon("TestWeapon2", Stars.FOUR, WeaponType.BOWS, WeaponOptions.HP_PERCENTAGE);
        Weapon weapon3 = buildWeapon("TestWeapon3", Stars.FIVE, WeaponType.SWORDS, WeaponOptions.ENERGY_RECHARGE);

        List<Stars> stars = List.of(Stars.FIVE);
        List<WeaponType> bows = List.of(WeaponType.BOWS);

        WeaponListRequest request1 = new WeaponListRequest(stars, null, null);
        WeaponListRequest request2 = new WeaponListRequest(null, null, bows);

        Pageable pageable = PageRequest.of(0, 20);

        //when
        when(weaponService.getWeaponListResponse(
                eq(pageable), eq(request1)
        )).thenReturn(
                new PageImpl<>(createWeaponResponse(weapon1, weapon3))
        );

        when(weaponService.getWeaponListResponse(
                eq(pageable), eq(request2)
        )).thenReturn(
                new PageImpl<>(createWeaponResponse(weapon1, weapon2))
        );

        //then

        //Todo
        //  return값 만들기
        mockMvc.perform(get("/api/weapons")
                        .params(RequestConverter.convertRequestToMultiValueMap(request1))
                )
                .andDo(print())
                .andDo(restDocs.document(
                        RestDocsUtil.generateRequestParams(createRequestParams()),
                        RestDocsUtil.generateResponseFields(createResponseField())
                ))
                .andExpectAll(
                        status().isOk(),
                        content().string(containsString("TestWeapon1")),
                        content().string(containsString("TestWeapon3"))
                );

        mockMvc.perform(get("/api/weapons")
                        .params(RequestConverter.convertRequestToMultiValueMap(request2))
                )
                .andDo(print())
                .andDo(restDocs.document(
                        RestDocsUtil.generateRequestParams(createRequestParams()),
                        RestDocsUtil.generateResponseFields(createResponseField())
                ))
                .andExpectAll(
                        status().isOk(),
                        content().string(containsString("TestWeapon1")),
                        content().string(containsString("TestWeapon2"))
                );

    }

    private Weapon buildWeapon(
            String name,
            Stars stars,
            WeaponType weaponType,
            WeaponOptions weaponOptions
    ) {
        return Weapon.builder()
                .weaponName(name)
                .stars(stars)
                .weaponType(weaponType)
                .weaponOption(weaponOptions)
                .build();
    }

    private List<WeaponListResponse> createWeaponResponse(Weapon... weapons) {
        return Arrays.stream(weapons).map(WeaponListResponse::of).toList();
    }

    private List<Field> createRequestParams() {
        return List.of(
                new Field("stars", ARRAY, "5성/4성", "Enum stars", true),
                new Field("weaponOptions", ARRAY, "무기 주옵션", "Enum WeaponOptions", true),
                new Field("weaponTypes", ARRAY, "무기 종류", "Enum WeaponType", true)
        );
    }

    private List<Field> createResponseField() {
        return List.of(
                new Field("wrapper[].id", STRING, "무기 id", "pk", false),
                new Field("wrapper[].name", STRING, "무기 이름"),
                new Field("wrapper[].imageUrls", STRING, "무기 이미지 경로"),
                new Field("wrapper[].stars", STRING, "무기 별", "Enum Stars", false),

                new Field("page.currentPage", NUMBER, "현재 페이지"),
                new Field("page.totalPages", NUMBER, "총 페이지"),
                new Field("page.totalElements", NUMBER, "총 아이템 개수"),

                new Field("message", STRING, "메세지")
        );
    }

}
