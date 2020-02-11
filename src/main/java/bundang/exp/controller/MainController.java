package bundang.exp.controller;

import bundang.exp.exp_offer.domain.ExpOffer;
import bundang.exp.exp_offer.repository.ExpOfferRepository;
import bundang.exp.exp_request.domain.ExpRequest;
import bundang.exp.exp_request.repository.ExpRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(("/api/main"))
@RequiredArgsConstructor
public class MainController {

    private final ExpOfferRepository expOfferRepository;
    private final ExpRequestRepository expRequestRepository;


    @GetMapping("/exp-offer")
    public ResponseEntity<List<ExpOffer>> expOfferList() {
        return ResponseEntity.ok(expOfferRepository.findTop3ByOrderByIdDesc());
    }

    @GetMapping("/exp-request")
    public ResponseEntity<List<ExpRequest>> expRequestList() {
        return ResponseEntity.ok(expRequestRepository.findTop3ByOrderByIdDesc());
    }



}
