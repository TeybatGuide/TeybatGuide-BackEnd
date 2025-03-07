package toyproject.genshin.teybatguide.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.test.context.support.WithMockUser;
import toyproject.genshin.teybatguide.character.controller.CharactersController;
import toyproject.genshin.teybatguide.common.restDocs.AbstractRestDocsTests;
import toyproject.genshin.teybatguide.common.RequestConverter;
import toyproject.genshin.teybatguide.common.restDocs.Field;
import toyproject.genshin.teybatguide.common.restDocs.RestDocsUtil;
import toyproject.genshin.teybatguide.character.controller.dto.request.CharacterListRequest;
import toyproject.genshin.teybatguide.character.controller.dto.response.CharacterListResponse;
import toyproject.genshin.teybatguide.character.entity.Characters;
import toyproject.genshin.teybatguide.base.value.Country;
import toyproject.genshin.teybatguide.character.entity.value.Element;
import toyproject.genshin.teybatguide.base.value.Stars;
import toyproject.genshin.teybatguide.character.service.CharactersService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static toyproject.genshin.teybatguide.common.data.Types.*;

@Slf4j
@AutoConfigureRestDocs
@WebMvcTest(CharactersController.class)
public class CharacterControllerTest extends AbstractRestDocsTests {

    @MockBean
    private CharactersService charactersService;

    @Test
    @WithMockUser(username = "user", roles = {"GUEST"})
    public void getCharacterListTest() throws Exception {
        //give
        List<Stars> stars = List.of(Stars.FIVE);
        List<Country> countries = List.of(Country.INAZUMA, Country.MONDSTADT);
        List<Element> elements = List.of(Element.ANEMO, Element.ELECTRO);

        CharacterListRequest request = new CharacterListRequest(stars, countries, elements, null);

        Characters testCharacter1 = createCharacters("test", Country.INAZUMA, Element.ANEMO);
        Characters testCharacter2 = createCharacters("test2", Country.MONDSTADT, Element.ELECTRO);

        Pageable pageable = PageRequest.of(0, 20);
        List<CharacterListResponse> characterResponse = createCharacterResponse(testCharacter1, testCharacter2);

        //when
        when(
                charactersService.findAndCreateCharacterList(any(CharacterListRequest.class), eq(pageable))
        ).thenReturn(new PageImpl<>(characterResponse, pageable, characterResponse.size()));

        //then
        this.mockMvc.perform(get("/api/characters")
                        .params(RequestConverter.convertRequestToMultiValueMap(request))
                )
                .andDo(print())
                .andDo(restDocs.document(
                        RestDocsUtil.generateRequestParams(createRequestParams()),
                        RestDocsUtil.generateResponseFields(createResponseField())
                ))
                .andExpectAll(
                        status().isOk(),
                        content().string(containsString("test2"))
                );
    }

    private Characters createCharacters(String name, Country country, Element element) {
        return Characters.builder()
                .characterName(name)
                .element(element)
                .country(country)
                .stars(Stars.FIVE)
                .build();
    }

    private List<CharacterListResponse> createCharacterResponse(Characters... characters) {
        return Arrays.stream(characters).map(CharacterListResponse::of).toList();
    }

    private List<Field> createRequestParams() {
        return Arrays.asList(
                new Field("stars", ARRAY, "5성/4성", "Enum stars", true),
                new Field("countries", ARRAY, "지역", "Enum Country", true),
                new Field("elements", ARRAY, "원소", "Enum Element", true),
                new Field("weaponTypes", ARRAY, "무기 종류", "Enum WeaponType", true)
        );
    }

    private List<Field> createResponseField() {
        return Arrays.asList(
                new Field("wrapper[].characterId", STRING, "캐릭터 id", "pk", false),
                new Field("wrapper[].characterName", STRING, "캐릭터 이름"),
                new Field("wrapper[].characterImage", STRING, "캐릭터 이미지 경로"),
                new Field("wrapper[].stars", STRING, "캐릭터의 별", "Enum Stars", false),

                new Field("page.currentPage", NUMBER, "현재 페이지"),
                new Field("page.totalPages", NUMBER, "총 페이지"),
                new Field("page.totalElements", NUMBER, "총 아이템 개수"),

                new Field("message", STRING, "메세지")
        );
    }
}
