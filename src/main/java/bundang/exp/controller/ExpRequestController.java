package bundang.exp.controller;


import bundang.exp.exp_request.ExpRequestService;
import bundang.exp.exp_request.dto.ExpRequestDto;
import bundang.exp.user.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exp-request")
@Slf4j
public class ExpRequestController {

    private final ExpRequestService service;

    @PostMapping
    private ResponseEntity<ExpRequestDto> create(Authentication authentication, @RequestBody ExpRequestDto expRequestDto) {

        UserPrincipal user = (UserPrincipal) authentication.getPrincipal();
        return ResponseEntity.ok(service.create(user, expRequestDto));
    }
}
