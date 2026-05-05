package plugin.prep.auth.controllers;

import lombok.*;
import org.springframework.web.bind.annotation.*;

import plugin.prep.auth.api.*;
import plugin.prep.auth.dto.*;
import plugin.prep.auth.service.*;

@RestController
@RequiredArgsConstructor
public class UsersController implements UsersApi {

    private final UsersService usersService;

    @Override
    public UserDto me() {
        return usersService.me();
    }

}
