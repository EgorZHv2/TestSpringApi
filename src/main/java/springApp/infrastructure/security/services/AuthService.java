package springApp.infrastructure.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import springApp.core.application.dto.auth.LoginDTO;
import springApp.core.application.exceptions.UserAlreadyExistsException;
import springApp.core.application.exceptions.UserNotFoundException;
import springApp.core.application.exceptions.WrongPasswordException;
import springApp.core.application.interfaces.repositories.IUserRepository;
import springApp.core.application.interfaces.services.ITokenService;
import springApp.core.application.interfaces.services.IAuthService;
import springApp.core.domain.entities.User;
import org.springframework.stereotype.Service;
import springApp.core.application.dto.auth.RegisterDTO;
import springApp.persistence.repositories.spring.interfaces.IUserSpringRepository;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class AuthService implements IAuthService {

    private IUserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private ITokenService tokenService;

    @Autowired
    public AuthService(IUserRepository userRepository, PasswordEncoder passwordEncoder, ITokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @Override
    @Async
    public void register(RegisterDTO dto)  {
        var existingUser = userRepository.getByPhoneNumber(dto.getPhoneNumber());
        if (!existingUser.isEmpty()) {
            throw new UserAlreadyExistsException();
        }
        var newUser = new User();

        newUser.setAge(dto.getAge());
        newUser.setName(dto.getName());
        newUser.setPhoneNumber(dto.getPhoneNumber());
        newUser.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(newUser);

    }

    @Override
    @Async
    public CompletableFuture<String> login(LoginDTO dto)  throws InterruptedException,ExecutionException {
        var existingUser = userRepository.getByPhoneNumber(dto.getPhoneNumber());
        if (existingUser.isEmpty()) {
            throw new UserNotFoundException();
        }
        if (!passwordEncoder.matches(dto.getPassword(), existingUser.get().getPasswordHash())) {
            throw new WrongPasswordException();
        }
        var token = tokenService.generateToken(existingUser.get()).get();
        return CompletableFuture.completedFuture(token) ;

    }

}
