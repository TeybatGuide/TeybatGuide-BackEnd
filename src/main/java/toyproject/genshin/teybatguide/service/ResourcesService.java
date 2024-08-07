package toyproject.genshin.teybatguide.service;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.genshin.teybatguide.base.dto.PageDto;
import toyproject.genshin.teybatguide.base.PageResponseData;
import toyproject.genshin.teybatguide.controller.dto.banner.response.ResourcesResponse;
import toyproject.genshin.teybatguide.controller.dto.resource.ResourceListRequest;
import toyproject.genshin.teybatguide.controller.dto.resource.ResourceListResponse;
import toyproject.genshin.teybatguide.controller.dto.resource.ResourceSaveRequest;
import toyproject.genshin.teybatguide.domain.Domain;
import toyproject.genshin.teybatguide.domain.Resources;
import toyproject.genshin.teybatguide.domain.value.DayOfWeek;
import toyproject.genshin.teybatguide.domain.value.Materials;
import toyproject.genshin.teybatguide.exception.TeybatBadRequestException;
import toyproject.genshin.teybatguide.exception.TeybatDataAccessException;
import toyproject.genshin.teybatguide.repository.DomainRepository;
import toyproject.genshin.teybatguide.repository.ResourcesRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ResourcesService {

    private final ResourcesRepository resourcesRepository;
    private final DomainRepository domainRepository;

    public Page<ResourceListResponse> searchResourceList(ResourceListRequest request, Pageable pageable) {
        return resourcesRepository.findByCountryAndDayOfWeekAndMaterial(request, pageable)
                .map(ResourceListResponse::of);
    }

    public PageResponseData<ResourcesResponse> searchResourcesForMaterials(Materials materials, Pageable pageable) {
        Page<Resources> resourcesPage = resourcesRepository.findByDayOfWeekAndMaterialForMain(DayOfWeek.of(getDayOfWeek()), materials, pageable);
        List<ResourceListResponse> resourcesList = resourcesPage.stream().map(ResourceListResponse::of).toList();

        return PageResponseData.of(ResourcesResponse.of(materials, resourcesList), PageDto.of(resourcesPage));
    }

    @Transactional
    public ResourceListResponse saveResources(@NotNull ResourceSaveRequest request) {
        Domain domain = domainRepository.findById(request.domainId())
                .orElseThrow(() -> new TeybatBadRequestException("비경이 존재하지 않습니다."));

        Resources resources = Resources.of(request, domain);
        resources.setResourcesImage(createImagePath(resources));
        resourcesRepository.save(resources);

        Resources resource = resourcesRepository.findById(resources.getId())
                .orElseThrow(() -> new TeybatDataAccessException("resource 저장에 실패하였습니다."));
        return ResourceListResponse.of(resource);
    }

    private java.time.DayOfWeek getDayOfWeek() {
        LocalDateTime now = LocalDateTime.now();

        if (now.getHour() < 5) {
            now = now.minusDays(1);
        }

        return now.getDayOfWeek();
    }

    private @NotNull String createImagePath(@NotNull Resources resources) {
        return "/" + resources.getId().replace("_", "/") + ".webp";
    }

}
