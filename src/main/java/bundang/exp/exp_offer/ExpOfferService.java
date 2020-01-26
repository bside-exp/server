package bundang.exp.exp_offer;

import bundang.exp.category.domain.Duty;
import bundang.exp.category.domain.ExpOfferType;
import bundang.exp.category.domain.Industry;
import bundang.exp.category.repository.DutyRepository;
import bundang.exp.category.repository.ExpOfferTypeRepository;
import bundang.exp.category.repository.IndustryRepository;
import bundang.exp.exp_offer.domain.ExpOffer;
import bundang.exp.exp_offer.domain.ExpOfferComment;
import bundang.exp.exp_offer.domain.ExpOfferTag;
import bundang.exp.exp_offer.dto.ExpOfferCommentDto;
import bundang.exp.exp_offer.dto.ExpOfferDto;
import bundang.exp.exp_offer.repository.ExpOfferCommentRepository;
import bundang.exp.exp_offer.repository.ExpOfferRepository;
import bundang.exp.exp_offer.repository.ExpOfferTagRepository;
import bundang.exp.user.User;
import bundang.exp.user.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
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

    @Transactional
    public ExpOffer update(UserPrincipal userPrincipal, ExpOfferDto expOfferDto, Long expOfferId) {

        ExpOffer targetOffer = expOfferRepository.findById(expOfferId).orElseThrow(() -> new RuntimeException("게시물을 찾을 수 없습니다."));

        if (userPrincipal == null || !userPrincipal.getId().equals(targetOffer.getId())) {
            throw new RuntimeException("사용자 정보가 다릅니다.");
        }

        Industry industry = industryRepository.findByName(expOfferDto.getIndustry()).orElseThrow(() -> new IllegalArgumentException("산업군을 찾을 수 없습니다."));
        Duty duty = dutyRepository.findByName(expOfferDto.getDuty()).orElseThrow(() -> new IllegalArgumentException("직군을 찾을 수 없습니다."));
        List<ExpOfferType> expOfferTypes = expOfferTypeRepository.findByNameIn(expOfferDto.getExpOfferTypes());

        if (expOfferTypes.size() == 0) {
            throw new RuntimeException("경험 제공형태가 잘못됐습니다.");
        }

        List<ExpOfferTag> newTags = null;
        if (expOfferDto.getTags() != null) {
            newTags = expOfferDto.getTags().stream()
                    .map(tag -> ExpOfferTag.builder().expOffer(targetOffer).tag(tag).build()).collect(Collectors.toList());

            List<Long> deleteTargetExpOfferTagIds = expOfferTagRepository.findAllByExpOfferId(expOfferId).stream()
                    .map(tag -> tag.getId()).collect(Collectors.toList());

            expOfferTagRepository.deleteAllByIdIn(deleteTargetExpOfferTagIds);
        }

        targetOffer.setTitle(expOfferDto.getTitle());
        targetOffer.setIndustry(industry);
        targetOffer.setDuty(duty);
        targetOffer.setExpOfferTypes(expOfferTypes);
        targetOffer.setDescription(expOfferDto.getDescription());
        targetOffer.setContact(expOfferDto.getContact());
        targetOffer.setEmail(expOfferDto.getEmail());
        targetOffer.setFirmName(expOfferDto.getFirmName());
        targetOffer.setExpDuration(expOfferDto.getExpDuration());
        targetOffer.setExpOfferTags(newTags);

        return targetOffer;
    }

    public ExpOffer deleteOffer(UserPrincipal userPrincipal, Long offerId) {
        ExpOffer expOffer = expOfferRepository.findById(offerId).orElseThrow(() -> new RuntimeException("게시물이 없습니다."));
        if (userPrincipal == null || !expOffer.getUser().getId().equals(userPrincipal.getId())) {
            throw new RuntimeException("사용자 정보가 다릅니다.");
        }

        expOfferRepository.delete(expOffer);

        return expOffer;
    }

    public ExpOfferComment createComment(UserPrincipal userPrincipal, ExpOfferCommentDto expOfferCommentDto, Long expOfferId) {

        User user = User.create(userPrincipal);

        ExpOffer expOffer = expOfferRepository.findById(expOfferId).orElseThrow(() -> new RuntimeException("게시물을 찾을 수 없습니다."));
        ExpOfferComment comment = ExpOfferComment.builder().user(user)
                .expOffer(expOffer).comment(expOfferCommentDto.getComment()).build();

        expOfferCommentRepository.save(comment);

        return comment;
    }

    public ExpOfferComment updateComment(UserPrincipal userPrincipal, ExpOfferCommentDto expOfferCommentDto, Long commentId) {
        ExpOfferComment targetComment = expOfferCommentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("해당 댓글을 찾을 수 없습니다."));
        if (userPrincipal == null || !targetComment.getUser().getId().equals(userPrincipal.getId())) {
            throw new RuntimeException("사용자 정보가 다릅니다.");
        }

        targetComment.setComment(expOfferCommentDto.getComment());

        return targetComment;
    }

    public ExpOfferComment deleteComment(UserPrincipal userPrincipal, Long commentId) {
        ExpOfferComment targetComment = expOfferCommentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("해당 댓글을 찾을 수 없습니다."));
        if (userPrincipal == null || !targetComment.getUser().getId().equals(userPrincipal.getId())) {
            throw new RuntimeException("사용자 정보가 다릅니다.");
        }

        expOfferCommentRepository.delete(targetComment);

        return targetComment;
    }
}
