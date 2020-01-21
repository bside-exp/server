package bundang.exp.controller;


import bundang.exp.exp_request.ExpRequestService;
import bundang.exp.exp_request.domain.ExpRequest;
import bundang.exp.exp_request.dto.ExpRequestDto;
import bundang.exp.exp_request.repository.ExpRequestRepository;
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

import java.text.ParseException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exp-request")
@Slf4j
public class ExpRequestController {

    private final ExpRequestRepository requestRepository;
    private final ExpRequestService service;


    @GetMapping("/{pNo}")
    public ResponseEntity<Page<ExpRequest>> list(@PathVariable Integer pNo) throws ParseException {
        Pageable pageable = PageRequest.of(pNo - 1,5, Sort.Direction.DESC, "id");
        return ResponseEntity.ok(requestRepository.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<ExpRequestDto> create(Authentication authentication, @RequestBody ExpRequestDto expRequestDto) {

        UserPrincipal user = (UserPrincipal) authentication.getPrincipal();
        return ResponseEntity.ok(service.create(user, expRequestDto));
    }
















    




    @DeleteMapping("/{id}")
    public void delete(Authentication authentication, @PathVariable Long id) {

        UserPrincipal user = (UserPrincipal) authentication.getPrincipal();

        if(user != null) {
            requestRepository.deleteById(id);
        }
    }

}
