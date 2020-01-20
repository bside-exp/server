package bundang.exp.controller;


import bundang.exp.ExpRequest.ExpRequestService;
import bundang.exp.ExpRequest.dto.ExpRequestDto;
import bundang.exp.config.security.JwtTokenProvider;
import bundang.exp.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exp-request")
@Slf4j
public class ExpRequestController {

    private final UserRepository userRepository;
    private final ExpRequestService service;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping
    private ResponseEntity<ExpRequestDto> create(HttpServletRequest request, @RequestBody ExpRequestDto expRequestDto) {
        String token = getAuthorization(request);

        jwtTokenProvider.validateToken(token);
        Long id = jwtTokenProvider.getUserFromJWT(token);

        userRepository.findById(id).orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));
        return ResponseEntity.ok(service.create(expRequestDto));
    }

    private String getAuthorization(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");

        log.debug("Authorization : {} ", authorization);

        if (authorization == null) {
            throw new RuntimeException("Authorization is null");
        }

        return authorization.substring(7, authorization.length());
    }




}
