package bundang.exp.exp_request;

import bundang.exp.category.domain.Duty;
import bundang.exp.category.domain.Industry;
import bundang.exp.category.domain.Type;
import bundang.exp.category.repository.DutyRepository;
import bundang.exp.category.repository.IndustryRepository;
import bundang.exp.category.repository.TypeRepository;
import bundang.exp.exp_request.domain.ExpRequest;
import bundang.exp.exp_request.domain.ExpRequestTag;
import bundang.exp.exp_request.dto.ExpRequestDto;
import bundang.exp.exp_request.repository.ExpRequestRepository;
import bundang.exp.exp_request.repository.ExpRequestTagRepository;
import bundang.exp.user.User;
import bundang.exp.user.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpRequestService {

    private final ExpRequestRepository expRequestRepository;
    private final ExpRequestTagRepository expRequestTagRepository;
    private final DutyRepository dutyRepository;
    private final IndustryRepository industryRepository;
    private final TypeRepository typeRepository;
    private final ModelMapper modelMapper;

    public ExpRequestDto create(UserPrincipal userPrincipal, ExpRequestDto expRequestDto) {

        Industry industry = industryRepository.findByName(expRequestDto.getIndustry()).orElseThrow(() -> new IllegalArgumentException("산업군을 찾을 수 없습니다."));
        Duty duty = dutyRepository.findByName(expRequestDto.getDuty()).orElseThrow(() -> new IllegalArgumentException("직군을 찾을 수 없습니다."));
        List<Type> types = typeRepository.findByNameIn(expRequestDto.getTypes());

        List<ExpRequestTag> tags = null;
        if (expRequestDto.getTags() != null) {
            tags = expRequestDto
                    .getTags()
                    .stream()
                    .map(s -> ExpRequestTag.builder().name(s).build()).collect(Collectors.toList());
        }

        User user = User.builder()
                .id(userPrincipal.getId())
                .build();

        ExpRequest exp = ExpRequest.builder()
                .title(expRequestDto.getTitle())
                .industry(industry)
                .duty(duty)
                .types(types)
                .user(user)
                .description(expRequestDto.getDescription())
                .build();

        for (ExpRequestTag name : tags) {
            ExpRequestTag expTag = ExpRequestTag.builder()
                    .name(name.getName())
                    .expRequest(exp)
                    .build();
            expRequestTagRepository.save(expTag);
        }

        expRequestRepository.save(exp);

        return expRequestDto;
    }

    @Transactional
    public ExpRequestDto update(UserPrincipal userPrincipal, ExpRequestDto expRequestDto, Long id) {

        ExpRequest expRequest = expRequestRepository.findById(id).orElseThrow(() -> new RuntimeException("게시물을 찾을 수 없습니다."));
        List<ExpRequestTag> tag = expRequestTagRepository.findByExpRequestId(id);

        Industry industry = industryRepository.findByName(expRequestDto.getIndustry()).orElseThrow(() -> new IllegalArgumentException("산업군을 찾을 수 없습니다."));
        Duty duty = dutyRepository.findByName(expRequestDto.getDuty()).orElseThrow(() -> new IllegalArgumentException("직군을 찾을 수 없습니다."));
        List<Type> types = typeRepository.findByNameIn(expRequestDto.getTypes());

        List<ExpRequestTag> tags = null;
        if (expRequestDto.getTags() != null) {
            tags = expRequestDto
                    .getTags()
                    .stream()
                    .map(s -> ExpRequestTag.builder().name(s).build()).collect(Collectors.toList());
        }

        User user = User.builder()
                .id(userPrincipal.getId())
                .build();

//        ExpRequest exp = ExpRequest.builder()
//                .title(expRequestDto.getTitle())
//                .industry(industry)
//                .duty(duty)
//                .types(types)
//                .user(user)
//                .description(expRequestDto.getDescription())
//                .build();

        for (ExpRequestTag name : tags) {
//            ExpRequestTag expTag = ExpRequestTag.builder()
//                    .name(name.getName())
//                    .expRequest(exp)
//                    .build();

            modelMapper.map(tags, tag);
            //expRequestTagRepository.save(expTag);
        }

        modelMapper.map(expRequestDto, expRequest);
        expRequestRepository.save(expRequest);

        return expRequestDto;


    }
}
