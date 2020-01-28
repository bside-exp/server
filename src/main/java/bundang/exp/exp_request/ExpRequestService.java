package bundang.exp.exp_request;

import bundang.exp.category.domain.Duty;
import bundang.exp.category.domain.ExpOfferType;
import bundang.exp.category.domain.Industry;
import bundang.exp.category.repository.DutyRepository;
import bundang.exp.category.repository.ExpOfferTypeRepository;
import bundang.exp.category.repository.IndustryRepository;
import bundang.exp.exp_request.domain.ExpRequest;
import bundang.exp.exp_request.domain.ExpRequestComment;
import bundang.exp.exp_request.domain.ExpRequestTag;
import bundang.exp.exp_request.dto.ExpRequestCommentDto;
import bundang.exp.exp_request.dto.ExpRequestDto;
import bundang.exp.exp_request.repository.ExpRequestCommentRepository;
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
    private final ExpRequestCommentRepository expRequestCommentRepository;
    private final DutyRepository dutyRepository;
    private final IndustryRepository industryRepository;
    private final ExpOfferTypeRepository expOfferTypeRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public ExpRequestDto create(UserPrincipal userPrincipal, ExpRequestDto expRequestDto) {

        Industry industry = industryRepository.findByName(expRequestDto.getIndustry()).orElseThrow(() -> new IllegalArgumentException("산업군을 찾을 수 없습니다."));
        Duty duty = dutyRepository.findByName(expRequestDto.getDuty()).orElseThrow(() -> new IllegalArgumentException("직군을 찾을 수 없습니다."));
        List<ExpOfferType> expOfferTypes = expOfferTypeRepository.findByNameIn(expRequestDto.getTypes());

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
                .expOfferTypes(expOfferTypes)
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

        if (userPrincipal == null) {
            throw new RuntimeException("유저정보가 존재하지 않습니다.");
        }

        ExpRequest expRequest = expRequestRepository.findById(id).orElseThrow(() -> new RuntimeException("게시물을 찾을 수 없습니다."));
        if (!expRequest.getUser().getId().equals(userPrincipal.getId())) {
            throw new RuntimeException("작성자와 현재 사용자가 다릅니다.");
        }

        Industry industry = industryRepository.findByName(expRequestDto.getIndustry()).orElseThrow(() -> new IllegalArgumentException("산업군을 찾을 수 없습니다."));
        Duty duty = dutyRepository.findByName(expRequestDto.getDuty()).orElseThrow(() -> new IllegalArgumentException("직군을 찾을 수 없습니다."));
        List<ExpOfferType> expOfferTypes = expOfferTypeRepository.findByNameIn(expRequestDto.getTypes());

        List<ExpRequestTag> tags = null;
        if (expRequestDto.getTags() != null) {
            tags = expRequestDto
                    .getTags()
                    .stream()
                    .map(s -> ExpRequestTag.builder().name(s).expRequest(expRequest).build()).collect(Collectors.toList());

            List<Long> deleteTargetExpRequestTagIds = expRequestTagRepository.findByExpRequestId(id).stream()
                    .map(x -> x.getId()).collect(Collectors.toList());

            expRequestTagRepository.deleteAllByIdIn(deleteTargetExpRequestTagIds);
        }

        expRequest.setTitle(expRequestDto.getTitle());
        expRequest.setIndustry(industry);
        expRequest.setDuty(duty);
        expRequest.setExpOfferTypes(expOfferTypes);
        expRequest.setDescription(expRequestDto.getDescription());
        expRequest.getTags().addAll(tags);

        return expRequestDto;
    }

    public ExpRequestCommentDto createComment(UserPrincipal userPrincipal, ExpRequestCommentDto expRequestCommentDto, Long id) {

        User user = User.builder()
                .id(userPrincipal.getId())
                .build();

        ExpRequest exp = expRequestRepository.findById(id).orElseThrow(() -> new RuntimeException("지정된 게시물을 찾을 수 없습니다."));

        ExpRequestComment comment = ExpRequestComment.builder()
                .user(user)
                .expRequest(exp)
                .contents(expRequestCommentDto.getContents())
                .build();

        expRequestCommentRepository.save(comment);

        return expRequestCommentDto;
    }
}
