package springApp.infrastructure.security.services;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import springApp.core.application.interfaces.services.ITokenService;
import springApp.core.domain.entities.User;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.CompletableFuture;


@Service
public class TokenService implements ITokenService {
    private final SecretKey jwtAccessSecret;

    private  String jwtSecret;
    private static final Logger logger = LoggerFactory.getLogger(TokenService.class);

    public TokenService(@Value("${jwt.secret.access}") String jwtSecret){
        this.jwtSecret = jwtSecret;
        this.jwtAccessSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }
    @Override
    @Async
    public CompletableFuture<String> generateToken(User user){
       var date = LocalDateTime.now();
       var instant = date.plusDays(30).atZone(ZoneId.systemDefault()).toInstant();
       var expiresAt = Date.from(instant);
       return CompletableFuture.completedFuture(Jwts.builder()
               .setSubject(user.getPhoneNumber())
               .setExpiration(expiresAt)
               .compact());
    }
    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public Boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
    public String getUserPhoneFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJwt(token).getBody().getSubject();
    }


}
