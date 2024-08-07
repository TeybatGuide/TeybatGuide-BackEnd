package toyproject.genshin.teybatguide.controller.dto.resource;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import toyproject.genshin.teybatguide.domain.Domain;

public record DomainDto(String id, String name, String countryName) {

    @Contract("_ -> new")
    public static @NotNull DomainDto of(@NotNull Domain domain) {
        return new DomainDto(domain.getId(), domain.getDomainName(), domain.getCountry().getCountryName());
    }

}
