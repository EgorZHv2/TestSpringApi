package springApp.core.application.interfaces.services;

import springApp.core.application.dto.auth.LoginDTO;
import springApp.core.application.dto.auth.RegisterDTO;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public interface IAuthService {
    void register(RegisterDTO dto);
    CompletableFuture<String> login(LoginDTO dto)  throws InterruptedException,ExecutionException;

}
