package bundang.exp.controller;


import bundang.exp.exp_request.ExpRequestService;
import bundang.exp.exp_request.domain.ExpRequest;
import bundang.exp.exp_request.dto.ExpRequestDto;
import bundang.exp.exp_request.repository.ExpRequestRepository;
import bundang.exp.user.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exp-request")
@Slf4j
public class ExpRequestController {

    private final ExpRequestRepository requestRepository;
    private final ExpRequestService service;

    @GetMapping("/{id}")
    public ResponseEntity<ExpRequest> details(@PathVariable Long id) {
        ExpRequest expRequest = requestRepository.findById(id).orElseThrow(() -> new RuntimeException("일치하는 게시물을 찾을 수 없습니다."));
        return ResponseEntity.ok(expRequest);
    }

    @PostMapping
    public ResponseEntity<ExpRequestDto> create(Authentication authentication, @RequestBody ExpRequestDto expRequestDto) {

        UserPrincipal user = (UserPrincipal) authentication.getPrincipal();
        return ResponseEntity.ok(service.create(user, expRequestDto));
    }


}
