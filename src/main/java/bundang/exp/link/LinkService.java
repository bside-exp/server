package bundang.exp.link;

import bundang.exp.common.ExpException;
import bundang.exp.exp_offer.domain.ExpOffer;
import bundang.exp.exp_offer.repository.ExpOfferRepository;
import bundang.exp.exp_request.domain.ExpRequest;
import bundang.exp.exp_request.repository.ExpRequestRepository;
import bundang.exp.notice.CreateNoticeDto;
import bundang.exp.notice.NoticeService;
import bundang.exp.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LinkService {
    private final NoticeService noticeService;
    private final ExpRequestRepository expRequestRepository;
    private final ExpOfferRepository expOfferRepository;
    private final UserRepository userRepository;
    private final LinkRepository linkRepository;

    public List<Link> getLinkByOfferIdandRequester(Long offerId, Long userId) {
        ExpOffer offer = expOfferRepository.findById(offerId).get();
        List<Link> links = linkRepository.findByOffer(offer);
        return links.stream().filter(link -> link.getRequest().getUser().getId().equals(userId))
                .collect(Collectors.toList());
    }

    public List<Link> getLinkByRequestIdAndProvider(Long requestId, Long userId) {
        ExpRequest request = expRequestRepository.findById(requestId).get();
        List<Link> links = linkRepository.findByRequest(request);
        return links.stream().filter(link -> link.getOffer().getUser().getId().equals(userId))
                .collect(Collectors.toList());
    }

    @Transactional
    public void linkByRequest(LinkDto linkDto) throws ExpException {
        ExpRequest request = expRequestRepository.getOne(linkDto.getExpRequestId());
        ExpOffer offer = expOfferRepository.getOne(linkDto.getExpOfferId());

        Optional<Link> linkOptional = linkRepository.findByRequestAndOffer(request, offer);
        if (linkOptional.isPresent()) {
            confirmLinkByRequest(linkDto);
        } else {
            createLinkByRequest(linkDto);
        }
    }

    private void createLinkByRequest(LinkDto linkDto) throws ExpException {
        ExpRequest request = expRequestRepository.getOne(linkDto.getExpRequestId());
        ExpOffer offer = expOfferRepository.getOne(linkDto.getExpOfferId());

        Link link = Link.builder()
                .request(request)
                .offer(offer)
                .requester(new Boolean(true))
                .provider(new Boolean(false))
                .build();

        linkRepository.save(link);

        CreateNoticeDto createNoticeDto = CreateNoticeDto.builder()
                .title("경험 요청이 있습니다.")
                .link("/exp_request/" + request.getId())
                .build();
        noticeService.createNotice(offer.getUser().getId(), createNoticeDto);
    }

    private void confirmLinkByRequest(LinkDto confirmLinkDto) throws ExpException {
        ExpRequest request = expRequestRepository.getOne(confirmLinkDto.getExpRequestId());
        ExpOffer offer = expOfferRepository.getOne(confirmLinkDto.getExpOfferId());

        Link link = linkRepository.findByRequestAndOffer(request, offer)
                .orElseThrow(() -> new ExpException("잘못된 요청입니다."));

        if (link.getProvider()) {
            link.setRequester(true);
            CreateNoticeDto noticeForRequester = CreateNoticeDto.builder()
                    .title("거래가 성사되었습니다.")
                    .link("/exp_offer/" + offer.getId())
                    .build();
            CreateNoticeDto noticeForProvider = CreateNoticeDto.builder()
                    .title("거래가 성사되었습니다.")
                    .link("/exp_request/" + request.getId())
                    .build();

            noticeService.createNotice(request.getUser().getId(), noticeForRequester);
            noticeService.createNotice(offer.getUser().getId(), noticeForProvider);
        }
    }

    @Transactional
    public void linkByOffer(LinkDto linkDto) throws ExpException {
        ExpRequest request = expRequestRepository.getOne(linkDto.getExpRequestId());
        ExpOffer offer = expOfferRepository.getOne(linkDto.getExpOfferId());

        Optional<Link> linkOptional = linkRepository.findByRequestAndOffer(request, offer);
        if (linkOptional.isPresent()) {
            confirmLinkByOffer(linkDto);
        } else {
            createLinkByOffer(linkDto);
        }
    }

    private void createLinkByOffer(LinkDto linkDto) throws ExpException {
        ExpRequest request = expRequestRepository.getOne(linkDto.getExpRequestId());
        ExpOffer offer = expOfferRepository.getOne(linkDto.getExpOfferId());

        Link link = Link.builder()
                .request(request)
                .offer(offer)
                .requester(false)
                .provider(true)
                .build();

        linkRepository.save(link);

        CreateNoticeDto createNoticeDto = CreateNoticeDto.builder()
                .title("경험 제공이 있습니다.")
                .link("/exp_offer/" + offer.getId())
                .build();
        noticeService.createNotice(request.getUser().getId(), createNoticeDto);
    }

    private void confirmLinkByOffer(LinkDto confirmLinkDto) throws ExpException {
        ExpRequest request = expRequestRepository.getOne(confirmLinkDto.getExpRequestId());
        ExpOffer offer = expOfferRepository.getOne(confirmLinkDto.getExpOfferId());

        Link link = linkRepository.findByRequestAndOffer(request, offer)
                .orElseThrow(() -> new ExpException("잘못된 요청입니다."));

        if (link.getRequester()) {
            link.setProvider(true);
            CreateNoticeDto noticeForRequester = CreateNoticeDto.builder()
                    .title("거래가 성사되었습니다.")
                    .link("/exp_offer/" + offer.getId())
                    .build();
            CreateNoticeDto noticeForProvider = CreateNoticeDto.builder()
                    .title("거래가 성사되었습니다.")
                    .link("/exp_request/" + request.getId())
                    .build();

            noticeService.createNotice(request.getUser().getId(), noticeForRequester);
            noticeService.createNotice(offer.getUser().getId(), noticeForProvider);
        }
    }
}
