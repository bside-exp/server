package bundang.exp.controller;


import bundang.exp.ExpRequest.ExpRequestService;
import bundang.exp.ExpRequest.dto.ExpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exp-request")
public class ExpRequestController {

    private final ExpRequestService service;

    @PostMapping
    private ResponseEntity<ExpRequestDto> create(@RequestBody ExpRequestDto expRequestDto) {

        

        return ResponseEntity.ok(service.create(expRequestDto));
    }






}
