package org.sopt.Seminar.domain.user.controller;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.sopt.Seminar.domain.user.dto.request.UserRequest;
import org.sopt.Seminar.domain.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/")
public class UserController {

    private final UserService userService;

    @PostMapping("sign-up")
    public ResponseEntity<Void> signUp(@RequestBody UserRequest request) {
        URI location = URI.create(userService.create(request));
        return ResponseEntity.created(location).build();
    }

    @PostMapping("sign-in")
    public ResponseEntity<Void> signIn(@RequestBody UserRequest request) {
        userService.signIn(request);
        return ResponseEntity.noContent().build();
    }
}
