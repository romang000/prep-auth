package plugin.prep.auth.service;

import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import plugin.prep.auth.*;
import plugin.prep.auth.dto.*;
import plugin.prep.auth.exceptions.*;
import plugin.prep.auth.repository.*;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final JwtAuthHolder authHolder;

    private final UsersRepository usersRepository;

    @Transactional(readOnly = true)
    public UserDto me() {
        var auth = authHolder.get();
        if (!(auth instanceof JwtAuth jwtAuth) || !jwtAuth.isAuthenticated()) {
            throw new AuthorizationException();
        }

        var user = usersRepository.findById(jwtAuth.getUserId())
            .orElseThrow(AuthorizationException::new);

        return UserDto.builder()
            .id(user.getId())
            .email(user.getEmail())
            .login(user.getLogin())
            .grade(user.getGrade())
            .role(user.getRole().getCode())
            .build();
    }

}
