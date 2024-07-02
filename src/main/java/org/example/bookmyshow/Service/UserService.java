package org.example.bookmyshow.Service;

import org.example.bookmyshow.Models.User;
import org.example.bookmyshow.Repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository,BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository=userRepository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }

    public User signUp(String name,
                       String email,
                       String password){

        Optional<User> optionalUser = userRepository.findByEmail(email);

        User savedUser =null;
        if (optionalUser.isPresent()){
            //move to signin
        }else {
            User user = new User();
            user.setName(name);
            user.setEmail(email);


            user.setPassword(bCryptPasswordEncoder.encode(password));
            savedUser=userRepository.save(user);
        }

        return savedUser;
    }
}
