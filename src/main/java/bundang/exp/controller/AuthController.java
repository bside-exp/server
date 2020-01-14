package bundang.exp.controller;

import bundang.exp.common.ExpException;
import bundang.exp.user.User;
import bundang.exp.user.UserService;
import bundang.exp.user.dto.JoinDto;
import bundang.exp.user.dto.LoginDto;
import bundang.exp.user.dto.UpdateDto;
import bundang.exp.user.security.JwtAuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService service;

    @PostMapping("/join")
    public ResponseEntity<User> join(@Valid @RequestBody JoinDto joinDto) throws ExpException{
        return ResponseEntity.ok(service.join(joinDto));
    }

    @PutMapping("/user")
    public ResponseEntity<User> updateUser(@Valid @RequestBody UpdateDto updateDto) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        User updated = service.update(modelMapper.map(updateDto, User.class));

        return ResponseEntity.ok(updated);
    }

    @PostMapping("/login")
    public ResponseEntity authenticateUser(@Valid @RequestBody LoginDto loginDto) {
        String jwt = service.login(loginDto);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/user/duplicate/email")
    public ResponseEntity<Boolean> isDuplicateEmail(@RequestBody String email) throws ExpException {
        try {
            String emailDecoded = URLDecoder.decode(email, StandardCharsets.UTF_8.name()).replace("=", "");
            return ResponseEntity.ok(service.duplicateUsername(emailDecoded));
        } catch (UnsupportedEncodingException e) {
            throw new ExpException(e.getMessage());
        }
    }

    @PostMapping("/user/duplicate/nickname")
    public ResponseEntity<Boolean> isDuplicateNickname(@RequestBody String nickname) throws ExpException {
        try {
            String nicknameDecoded = URLDecoder.decode(nickname, StandardCharsets.UTF_8.name()).replace("=", "");
            return ResponseEntity.ok(service.duplicateNickName(nicknameDecoded));
        } catch (UnsupportedEncodingException e) {
            throw new ExpException(e.getMessage());
        }
    }


}
