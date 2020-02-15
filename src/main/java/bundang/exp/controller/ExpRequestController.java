package bundang.exp.controller;


import bundang.exp.exp_request.ExpRequestService;
import bundang.exp.exp_request.domain.ExpRequest;
import bundang.exp.exp_request.domain.ExpRequestComment;
import bundang.exp.exp_request.dto.ExpRequestCommentDto;
import bundang.exp.exp_request.dto.ExpRequestDto;
import bundang.exp.exp_request.repository.ExpRequestCommentRepository;
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

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exp-request")
@Slf4j
public class ExpRequestController {

    private final ExpRequestRepository requestRepository;
    private final ExpRequestCommentRepository requestCommentRepository;
    private final ExpRequestService service;

    @GetMapping
    public ResponseEntity<Page<ExpRequest>> list(@RequestParam Integer page, @RequestParam(required = false) Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size != null ? size : 5, Sort.Direction.DESC, "id");
        return ResponseEntity.ok(requestRepository.findAll(pageable));
    }

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

    @PutMapping("/{id}")
    public ResponseEntity<ExpRequestDto> update(Authentication authentication, @RequestBody ExpRequestDto expRequestDto, @PathVariable Long id) {
        UserPrincipal user = (UserPrincipal) authentication.getPrincipal();
        return ResponseEntity.ok(service.update(user, expRequestDto, id));
    }

    @DeleteMapping("/{id}")
    public void delete(Authentication authentication, @PathVariable Long id) {

        UserPrincipal user = (UserPrincipal) authentication.getPrincipal();

        if(user != null) {
            requestRepository.deleteById(id);
        }
    }

    @GetMapping("/{id}/comment/{pNo}")
    public ResponseEntity<Page<ExpRequestComment>> listComment(@PathVariable Long id, @PathVariable Integer pNo) {
        Pageable pageable = PageRequest.of(pNo - 1, 5, Sort.Direction.DESC, "id");
        return ResponseEntity.ok(requestCommentRepository.findAllByExpRequest_Id(id, pageable));
    }


    @PostMapping("/{id}/comment")
    public ResponseEntity<ExpRequestCommentDto> createComment(Authentication authentication, @RequestBody @Valid ExpRequestCommentDto expRequestCommentDto, @PathVariable Long id) {
        UserPrincipal user = (UserPrincipal) authentication.getPrincipal();
        return ResponseEntity.ok(service.createComment(user, expRequestCommentDto, id));
    }

}
