package bundang.exp.controller;

import bundang.exp.user.dto.JoinDto;
import bundang.exp.user.User;
import bundang.exp.user.UserService;
import bundang.exp.user.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService service;

    @PostMapping("/join")
    public ResponseEntity<User> join(@Valid @RequestBody JoinDto joinDto) {
        return service.join(joinDto);
    }

    @PostMapping("/login")
    public ResponseEntity authenticateUser(@Valid @RequestBody LoginDto loginDto) {
        return service.login(loginDto);
    }



}
