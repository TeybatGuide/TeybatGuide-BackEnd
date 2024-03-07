package toyproject.genshin.teybatguide.controller.dto.resource;

import lombok.Builder;
import toyproject.genshin.teybatguide.domain.Domain;

@Builder
public record DomainDto(String id, String name) {

    public static DomainDto of(Domain domain) {
        return DomainDto.builder()
                .id(domain.getId())
                .name(domain.getDomainName())
                .build();
    }

}
