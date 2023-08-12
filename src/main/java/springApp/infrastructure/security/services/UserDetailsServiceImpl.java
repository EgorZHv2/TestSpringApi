package springApp.infrastructure.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import springApp.core.application.exceptions.UserNotFoundException;
import springApp.core.application.interfaces.repositories.IUserRepository;
import springApp.core.domain.entities.User;
import springApp.infrastructure.security.models.UserDetailsImpl;
import springApp.persistence.repositories.spring.interfaces.IUserSpringRepository;

import java.util.concurrent.ExecutionException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    IUserRepository userRepository;

    //In this project I use phone number as login
    @Override
    public UserDetails loadUserByUsername(String phone)  {
        var user = userRepository.getByPhoneNumber(phone);
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }
        return UserDetailsImpl.build(user.get());
    }

}
