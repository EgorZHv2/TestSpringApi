package springApp.core.application.interfaces.services;

import io.jsonwebtoken.Claims;
import org.springframework.lang.NonNull;
import springApp.core.domain.entities.User;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.concurrent.CompletableFuture;

public interface ITokenService {
    Boolean validateJwtToken(String authToken);

    CompletableFuture<String> generateToken(User user);

    public String getUserPhoneFromJwtToken(String token);
}
