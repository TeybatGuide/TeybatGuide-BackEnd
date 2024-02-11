package toyproject.genshin.teybatguide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.genshin.teybatguide.controller.dto.ResourceSaveRequest;
import toyproject.genshin.teybatguide.domain.Resources;
import toyproject.genshin.teybatguide.repository.ResourcesRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ResourcesService {

    private final ResourcesRepository resourcesRepository;

    @Transactional
    public String saveResources(ResourceSaveRequest request) {
        resourcesRepository.save(Resources.of(request));
        return "good";
    }

}
