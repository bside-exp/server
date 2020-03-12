package bundang.exp.link;

import bundang.exp.common.ExpException;
import bundang.exp.exp_offer.domain.ExpOffer;
import bundang.exp.exp_offer.repository.ExpOfferRepository;
import bundang.exp.exp_request.domain.ExpRequest;
import bundang.exp.exp_request.repository.ExpRequestRepository;
import bundang.exp.notice.CreateNoticeDto;
import bundang.exp.notice.NoticeService;
import bundang.exp.user.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class LinkService {
    private NoticeService noticeService;
    private ExpRequestRepository expRequestRepository;
    private ExpOfferRepository expOfferRepository;
    private UserRepository userRepository;
    private LinkRepository linkRepository;

    @Transactional
    public void createLinkByRequest(CreateLinkDto createLinkDto) throws ExpException {
        ExpRequest request = expRequestRepository.getOne(createLinkDto.getExpRequestId());
        ExpOffer offer = expOfferRepository.getOne(createLinkDto.getExpOfferId());

        Link link = Link.builder()
                .request(request)
                .offer(offer)
                .requester(true)
                .provider(false)
                .build();

        linkRepository.save(link);

        CreateNoticeDto createNoticeDto = CreateNoticeDto.builder()
                .title("경험 요청이 있습니다.")
                .link("/exp_request/" + request.getId())
                .build();
        noticeService.createNotice(offer.getUser().getId(), createNoticeDto);
    }

    @Transactional
    public void createLinkByOffer(CreateLinkDto createLinkDto) throws ExpException {
        ExpRequest request = expRequestRepository.getOne(createLinkDto.getExpRequestId());
        ExpOffer offer = expOfferRepository.getOne(createLinkDto.getExpOfferId());

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

    @Transactional
    public void confirmLinkByRequest(ConfirmLinkDto confirmLinkDto) throws ExpException {
        ExpRequest request = expRequestRepository.getOne(confirmLinkDto.getRequestId());
        ExpOffer offer = expOfferRepository.getOne(confirmLinkDto.getOfferId());

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
    public void confirmLinkByOffer(ConfirmLinkDto confirmLinkDto) throws ExpException {
        ExpRequest request = expRequestRepository.getOne(confirmLinkDto.getRequestId());
        ExpOffer offer = expOfferRepository.getOne(confirmLinkDto.getOfferId());

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
