package bundang.exp.controller;

import bundang.exp.exp_offer.ExpOfferService;
import bundang.exp.exp_offer.domain.ExpOffer;
import bundang.exp.exp_offer.domain.ExpOfferComment;
import bundang.exp.exp_offer.dto.ExpOfferCommentDto;
import bundang.exp.exp_offer.dto.ExpOfferDto;
import bundang.exp.exp_offer.repository.ExpOfferCommentRepository;
import bundang.exp.exp_offer.repository.ExpOfferRepository;
import bundang.exp.user.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exp-offer")
@Slf4j
public class ExpOfferController {

    private final ExpOfferService expOfferService;
    private final ExpOfferRepository expOfferRepository;
    private final ExpOfferCommentRepository expOfferCommentRepository;

    @GetMapping
    public ResponseEntity<Page<ExpOffer>> list(@RequestParam Integer page, @RequestParam(required = false) Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size != null ? size : 5, Sort.Direction.DESC, "id");
        return ResponseEntity.ok(expOfferRepository.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpOffer> details(@PathVariable Long id) {
        ExpOffer expOffer = expOfferRepository.findById(id).orElseThrow(() -> new RuntimeException("게시물이 없습니다."));
        return ResponseEntity.ok(expOffer);
    }

    @PostMapping
    public ResponseEntity<ExpOffer> create(Authentication authentication, @RequestBody @Valid ExpOfferDto expOfferDto) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return ResponseEntity.ok(expOfferService.create(userPrincipal, expOfferDto));
    }

    @PutMapping("/{offerId}")
    public ResponseEntity<ExpOffer> update(Authentication authentication, @RequestBody ExpOfferDto expOfferDto, @PathVariable Long offerId) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return ResponseEntity.ok(expOfferService.update(userPrincipal, expOfferDto, offerId));
    }

    @DeleteMapping("/{offerId}")
    public ResponseEntity<ExpOffer> delete(Authentication authentication, @PathVariable Long offerId) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return ResponseEntity.ok(expOfferService.deleteOffer(userPrincipal, offerId));
    }

    @GetMapping("/{offerId}/comment/{pNo}")
    public ResponseEntity<Page<ExpOfferComment>> listComment(@PathVariable Long offerId, @PathVariable Integer pNo) {
        Pageable pageable = PageRequest.of(pNo - 1, 5, Sort.Direction.DESC, "id");
        return ResponseEntity.ok(expOfferCommentRepository.findAllByExpOfferId(offerId, pageable));
    }

    @PostMapping("/{offerId}/comment")
    public ResponseEntity<ExpOfferComment> createComment(Authentication authentication,
                                                         @RequestBody @Valid ExpOfferCommentDto expOfferCommentDto,
                                                         @PathVariable Long offerId) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return ResponseEntity.ok(expOfferService.createComment(userPrincipal, expOfferCommentDto, offerId));
    }
}
