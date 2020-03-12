package bundang.exp.controller;

import bundang.exp.common.ExpException;
import bundang.exp.link.ConfirmLinkDto;
import bundang.exp.link.CreateLinkDto;
import bundang.exp.link.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/link")
@RequiredArgsConstructor
public class LinkController {

    private final LinkService linkService;

    @PostMapping("/request")
    public ResponseEntity<String> createLinkByRequest(@Valid @RequestBody CreateLinkDto createLinkDto) throws ExpException {
        linkService.createLinkByRequest(createLinkDto);

        return ResponseEntity.ok("success");
    }

    @PostMapping("/offer")
    public ResponseEntity createLinkByOffer(@Valid @RequestBody CreateLinkDto createLinkDto) throws ExpException {
        linkService.createLinkByOffer(createLinkDto);

        return ResponseEntity.ok("success");
    }

    @PutMapping("/request")
    public ResponseEntity confirmLinkByRequest(@Valid @RequestBody ConfirmLinkDto confirmLinkDto) throws ExpException {
        linkService.confirmLinkByRequest(confirmLinkDto);

        return ResponseEntity.ok("success");
    }

    @PutMapping("/offer")
    public ResponseEntity confirmLinkByOffer(@Valid @RequestBody ConfirmLinkDto confirmLinkDto) throws ExpException {
        linkService.confirmLinkByOffer(confirmLinkDto);

        return ResponseEntity.ok("success");
    }
}
