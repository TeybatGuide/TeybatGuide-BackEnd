package toyproject.genshin.teybatguide.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import toyproject.genshin.teybatguide.common.RequestConverter;
import toyproject.genshin.teybatguide.controller.dto.characters.CharacterListRequest;
import toyproject.genshin.teybatguide.controller.dto.characters.CharacterListResponse;
import toyproject.genshin.teybatguide.domain.Characters;
import toyproject.genshin.teybatguide.domain.value.Country;
import toyproject.genshin.teybatguide.domain.value.Element;
import toyproject.genshin.teybatguide.domain.value.Stars;
import toyproject.genshin.teybatguide.service.CharactersService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@AutoConfigureRestDocs
@WebMvcTest(CharactersController.class)
public class CharacterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CharactersService charactersService;

//    @MockBean
//    private CharactersController charactersController;

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void getCharacterListTest() throws Exception {
        //give
        List<Stars> stars = List.of(Stars.FIVE);
        List<Country> countries = List.of(Country.INAZUMA, Country.MONDSTADT);
        List<Element> elements = List.of(Element.ANEMO, Element.ELECTRO);

        CharacterListRequest request = new CharacterListRequest(stars, countries, elements, new ArrayList<>());

        Characters testCharacter1 = createCharacters("test", Country.INAZUMA, Element.ANEMO);
        Characters testCharacter2 = createCharacters("test2", Country.MONDSTADT, Element.ELECTRO);

        Pageable pageable = PageRequest.of(0, 20);
        List<CharacterListResponse> characterResponse = createCharacterResponse(testCharacter1, testCharacter2);

        //when
        when(
                charactersService.findAndCreateCharacterList(any(CharacterListRequest.class), eq(pageable))
        ).thenReturn(new PageImpl<>(characterResponse, pageable, characterResponse.size()));
//        when(
//                charactersController.getCharacterList(pageable, eq(request))
//        ).thenReturn(PageResponseData.of(characterResponse, PageDto.of(pageable)));

        //then
        this.mockMvc.perform(get("/api/characters")
                .params(RequestConverter.convertRequestToMultiValueMap(request))
        ).andDo(print()).andExpectAll(
                status().isOk(),
                content().string(containsString("test"))
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


}
