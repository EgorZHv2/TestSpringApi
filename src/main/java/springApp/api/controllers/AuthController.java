package springApp.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.bind.annotation.*;
import springApp.core.application.dto.auth.LoginDTO;
import springApp.core.application.dto.auth.RegisterDTO;
import springApp.core.application.interfaces.services.IAuthService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private IAuthService authService;

    @Async
    @PostMapping("/register")
    public void register(@RequestBody RegisterDTO dto) {
        authService.register(dto);
    }
    @Async
    @PostMapping("/login")
    public CompletableFuture<String> login(@RequestBody LoginDTO dto) throws InterruptedException, ExecutionException {
        return authService.login(dto);
    }

}
