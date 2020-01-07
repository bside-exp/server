package bundang.exp.user;

import bundang.exp.common.ExpException;
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

    public User join(JoinDto joinDto) throws ExpException {

        if(this.duplicateUsername(joinDto.getUsername())) {
            throw new IllegalArgumentException("중복된 이메일입니다.");
        };

        if(this.duplicateNickName(joinDto.getNickName())) {
            throw new IllegalArgumentException("중복된 닉네임입니다.");
        };

        User newUser = modelMapper.map(joinDto, User.class);
        newUser.setPassword(passwordEncoder.encode(joinDto.getPassword()));

        log.info("role repository = {}", roleRepository.count());

        Role userRole = roleRepository.findByName(Roles.ROLE_USER)
                .orElseThrow(() -> new ExpException("User Role not set"));
        newUser.setRoles(Collections.singletonList(userRole));
        userRepository.save(newUser);

        return newUser;
    }

    public boolean duplicateUsername(String email) {
        return userRepository.findByUsername(email).isPresent();
    }

    public boolean duplicateNickName(String nickName) {
        return userRepository.findByNickName(nickName).isPresent();
    }

    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);

        return jwt;
    }

}
