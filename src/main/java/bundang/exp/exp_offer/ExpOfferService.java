package bundang.exp.exp_offer;

import bundang.exp.category.domain.Duty;
import bundang.exp.category.domain.ExpOfferType;
import bundang.exp.category.domain.Industry;
import bundang.exp.category.repository.DutyRepository;
import bundang.exp.category.repository.ExpOfferTypeRepository;
import bundang.exp.category.repository.IndustryRepository;
import bundang.exp.exp_offer.domain.ExpOffer;
import bundang.exp.exp_offer.domain.ExpOfferTag;
import bundang.exp.exp_offer.dto.ExpOfferDto;
import bundang.exp.exp_offer.repository.ExpOfferCommentRepository;
import bundang.exp.exp_offer.repository.ExpOfferRepository;
import bundang.exp.exp_offer.repository.ExpOfferTagRepository;
import bundang.exp.user.User;
import bundang.exp.user.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

public class ExpOfferService {

    @Autowired
    private ExpOfferRepository expOfferRepository;
    @Autowired
    private ExpOfferCommentRepository expOfferCommentRepository;
    @Autowired
    private IndustryRepository industryRepository;
    @Autowired
    private DutyRepository dutyRepository;
    @Autowired
    private ExpOfferTypeRepository expOfferTypeRepository;
    @Autowired
    private ExpOfferTagRepository expOfferTagRepository;

    @Transactional
    public ExpOffer create(UserPrincipal userPrincipal, ExpOfferDto expOfferDto) {

        Industry industry = industryRepository.findByName(expOfferDto.getIndustry()).orElseThrow(() -> new IllegalArgumentException("산업군을 찾을 수 없습니다."));
        Duty duty = dutyRepository.findByName(expOfferDto.getDuty()).orElseThrow(() -> new IllegalArgumentException("직군을 찾을 수 없습니다."));
        List<ExpOfferType> expOfferTypes = expOfferTypeRepository.findByNameIn(expOfferDto.getExpOfferTypes());

        List<ExpOfferTag> tags = null;
        if (expOfferDto.getTags() != null) {
            tags = expOfferDto.getTags().stream()
                    .map(tag -> ExpOfferTag.builder().tag(tag).build())
                    .collect(Collectors.toList());
        }

        User user = User.create(userPrincipal);

        ExpOffer expOffer = ExpOffer.builder()
                .title(expOfferDto.getTitle())
                .description(expOfferDto.getDescription())
                .user(user)
                .duty(duty)
                .email(expOfferDto.getEmail())
                .firmName(expOfferDto.getFirmName())
                .contact(expOfferDto.getContact())
                .expDuration(expOfferDto.getExpDuration())
                .industry(industry)
                .expOfferTypes(expOfferTypes)
                .expOfferTags(tags)
                .build();

        for (ExpOfferTag tag : tags) {
            tag.setExpOffer(expOffer);
            expOfferTagRepository.save(tag);
        }

        expOfferRepository.save(expOffer);

        return expOffer;
    }
}
