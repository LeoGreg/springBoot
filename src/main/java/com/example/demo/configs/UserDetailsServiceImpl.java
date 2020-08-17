package com.example.demo.configs;

import com.example.demo.model.ISP.UserStatus;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.exception.user.userExceptionHandler.UserExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import static com.example.demo.util.message.Message.UNVERIFIED_STATUS_MESSAGE;
import static com.example.demo.util.message.Message.USER_NOT_FOUND_MESSAGE;

@Component
@Qualifier("detailImpl")
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, LockedException {
        User user = userRepository.getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(USER_NOT_FOUND_MESSAGE);
        }
        if(user.getStatus()!= UserStatus.ACTIVE){
            throw new LockedException(UNVERIFIED_STATUS_MESSAGE);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }


}
