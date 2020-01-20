package bundang.exp.ExpRequest;

import bundang.exp.ExpRequest.damian.ExpRequest;
import bundang.exp.ExpRequest.damian.ExpRequestTag;
import bundang.exp.ExpRequest.dto.ExpRequestDto;
import bundang.exp.ExpRequest.repository.ExpRequestRepository;
import bundang.exp.ExpRequest.repository.ExpRequestTagRepository;
import bundang.exp.category.domain.Duty;
import bundang.exp.category.domain.Industry;
import bundang.exp.category.domain.Type;
import bundang.exp.category.repository.DutyRepository;
import bundang.exp.category.repository.IndustryRepository;
import bundang.exp.category.repository.TypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ExpRequestService {

    private final ExpRequestRepository expRequestRepository;
    private final ExpRequestTagRepository expRequestTagRepository;
    private final DutyRepository dutyRepository;
    private final IndustryRepository industryRepository;
    private final TypeRepository typeRepository;


    @Transactional
    public ExpRequestDto create(ExpRequestDto expRequestDto) {

        Industry industry = industryRepository.findByName(expRequestDto.getIndustry()).orElseThrow(() -> new IllegalArgumentException("산업군을 찾을 수 없습니다."));
        Duty duty = dutyRepository.findByName(expRequestDto.getDuty()).orElseThrow(() -> new IllegalArgumentException("직군을 찾을 수 없습니다."));
        List<Type> types = typeRepository.findByNameIn(Arrays.asList(expRequestDto.getTypes()));


        ExpRequest exp = ExpRequest.builder()
                .industry(industry)
                .duty(duty)
                .types(types)
                .description(expRequestDto.getDescription())
                .tags(Arrays.stream(expRequestDto.getTags()).map(s -> ExpRequestTag.builder().name(s).build()).collect(Collectors.toList()))
                .build();

//        exp.addTag(Arrays.stream(expRequestDto.getTags()).map(s -> ExpRequestTag.builder().name(s).build()).collect(Collectors.toList()));
        expRequestRepository.save(exp);
//        expRequestTagRepository.save(tags);
        return expRequestDto;
    }





}
