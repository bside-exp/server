package bundang.exp.controller;

import bundang.exp.common.ExpException;
import bundang.exp.user.User;
import bundang.exp.user.UserService;
import bundang.exp.user.dto.JoinDto;
import bundang.exp.user.dto.LoginDto;
import bundang.exp.user.security.JwtAuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService service;

    @PostMapping("/join")
    public ResponseEntity<User> join(@Valid @RequestBody JoinDto joinDto) throws ExpException{
        return ResponseEntity.ok(service.join(joinDto));
    }

    @PostMapping("/login")
    public ResponseEntity authenticateUser(@Valid @RequestBody LoginDto loginDto) {
        String jwt = service.login(loginDto);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/user/duplicate/email")
    public ResponseEntity<Boolean> isDuplicateEmail(@RequestBody String email) {
        return ResponseEntity.ok(service.duplicateUsername(email));
    }

    @PostMapping("/user/duplicate/nickname")
    public ResponseEntity<Boolean> isDuplicateNickname(@RequestBody String nickname) {
        return ResponseEntity.ok(service.duplicateNickName(nickname));
    }


}
