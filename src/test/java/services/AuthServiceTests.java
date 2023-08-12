package services;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;
import springApp.core.application.dto.auth.LoginDTO;
import springApp.core.application.dto.auth.RegisterDTO;
import springApp.core.application.interfaces.repositories.IUserRepository;
import springApp.core.application.interfaces.services.IAuthService;
import springApp.core.application.interfaces.services.ITokenService;
import springApp.core.domain.entities.User;
import springApp.infrastructure.security.services.AuthService;
import springApp.infrastructure.security.services.TokenService;
import springApp.persistence.repositories.UserRepository;
import springApp.persistence.repositories.spring.interfaces.IUserSpringRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AuthServiceTests {
    @Mock
    private IUserRepository userRepository;
    @Mock
    private ITokenService tokenService;
    @Mock
    private PasswordEncoder passwordEncoder;
    private List<User> usersList = new ArrayList<>();

    private final String USER_PHONE = "88005553535";
    private final String USER_PASSWORD = "12345678";
    private final String ACCESS_TOKEN = "AccesToken";

    @Test
    @Order(1)
    public void registerTest() {
        Mockito.doAnswer(e->usersList.add(e.getArgument(0))).when(userRepository).save(any(User.class));
        Mockito.doReturn(Optional.empty()).when(userRepository).getByPhoneNumber(any(String.class));
        Mockito.doReturn(USER_PASSWORD).when(passwordEncoder).encode(any(String.class));
        IAuthService service = new AuthService(userRepository, passwordEncoder, tokenService);
        var registerDto = new RegisterDTO();
        registerDto.setPhoneNumber(USER_PHONE);
        registerDto.setPassword(USER_PASSWORD);
        service.register(registerDto);
        Assert.isTrue(!usersList.isEmpty(),"Юзер добавился");
    }
    @Test
    @Order(2)
    public void loginTest()  throws InterruptedException, ExecutionException {
        var loginDto = new LoginDTO();
        loginDto.setPassword(USER_PASSWORD);
        loginDto.setPhoneNumber(USER_PHONE);
        Mockito.doReturn(Optional.of(usersList.get(0))).when(userRepository).getByPhoneNumber(any(String.class));
        Mockito.doReturn(true).when(passwordEncoder).matches(any(String.class),any(String.class));
        Mockito.doReturn(ACCESS_TOKEN).when(tokenService).generateToken(any(User.class));
        IAuthService service = new AuthService(userRepository, passwordEncoder, tokenService);
        Assert.isTrue(ACCESS_TOKEN == service.login(loginDto).get(),"Логин произошёл");
    }


}
