package bundang.exp.controller;

import bundang.exp.common.ExpException;
import bundang.exp.link.Link;
import bundang.exp.link.LinkDto;
import bundang.exp.link.LinkService;
import bundang.exp.user.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/link")
@RequiredArgsConstructor
public class LinkController {

    private final LinkService linkService;

    @PostMapping("/request")
    public ResponseEntity<String> createLinkByRequest(@Valid @RequestBody LinkDto linkDto) throws ExpException {
        linkService.linkByRequest(linkDto);

        return ResponseEntity.ok("success");
    }

    @GetMapping("/request")
    public ResponseEntity<Link> getLinkByRequestAndProvider(@RequestParam Long requestId, Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        List<Link> links = linkService.getLinkByRequestIdAndProvider(requestId, userPrincipal.getId());

        return ResponseEntity.ok(links.get(0));
    }

    @PostMapping("/offer")
    public ResponseEntity createLinkByOffer(@Valid @RequestBody LinkDto linkDto) throws ExpException {
        linkService.linkByOffer(linkDto);

        return ResponseEntity.ok("success");
    }

    @GetMapping("/offer")
    public ResponseEntity<Link> getLinkByOfferAndRequester(@RequestParam Long offerId, Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        List<Link> links = linkService.getLinkByOfferIdandRequester(offerId, userPrincipal.getId());

        return ResponseEntity.ok(links.get(0));
    }
}
