package bundang.exp.user;

import bundang.exp.config.security.JwtTokenProvider;
import bundang.exp.role.Role;
import bundang.exp.role.RoleRepository;
import bundang.exp.role.Roles;
import bundang.exp.user.dto.JoinDto;
import bundang.exp.user.dto.LoginDto;
import bundang.exp.user.security.JwtAuthenticationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public ResponseEntity<User> join(JoinDto joinDto) {
        //TODO: 이메일 정규식 테스트
//        String regExp = "^[a-zA-Z0-9._$%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
//        if(joinDto.getUsername().matches(regExp)) {
//            new RuntimeException("이메일 형식이 올바르지 않습니다.");
//        }

        User newUser = modelMapper.map(joinDto, User.class);
        newUser.setPassword(passwordEncoder.encode(joinDto.getPassword()));

        log.info("role repository = {}", roleRepository.count());

        Role userRole = roleRepository.findByName(Roles.ROLE_USER).orElseThrow(() -> new RuntimeException("User Role not set"));
        newUser.setRoles(Collections.singletonList(userRole));
        userRepository.save(newUser);

        return ResponseEntity.ok(newUser);
    }

    public ResponseEntity login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

}
