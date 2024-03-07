package toyproject.genshin.teybatguide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.genshin.teybatguide.controller.dto.resource.ResourceListRequest;
import toyproject.genshin.teybatguide.controller.dto.resource.ResourceListResponse;
import toyproject.genshin.teybatguide.controller.dto.resource.ResourceSaveRequest;
import toyproject.genshin.teybatguide.domain.Domain;
import toyproject.genshin.teybatguide.domain.Resources;
import toyproject.genshin.teybatguide.exception.TeybatException;
import toyproject.genshin.teybatguide.repository.DomainRepository;
import toyproject.genshin.teybatguide.repository.ResourcesRepository;

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


    @Transactional
    public String saveResources(ResourceSaveRequest request) {
        Domain domain = domainRepository.findById(request.domainId())
                .orElseThrow(() -> new TeybatException("domainId가 존재하지 않습니다."));

        Resources resources = Resources.of(request, domain);
        String path = "/" + resources.getId().replace("_", "/") + ".webp";
        resources.setResourcesImage(path);
        resourcesRepository.save(resources);
        return "good";
    }

}
