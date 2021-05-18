package com.split.expenses.domain.services;

import java.util.ArrayList;

import com.split.expenses.domain.dtos.UserDto;
import com.split.expenses.domain.entities.UserEntity;
import com.split.expenses.domain.mappers.MapperObject;
import com.split.expenses.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    MapperObject mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new User(user.getUsername(), user.getPassword(),
                    new ArrayList<>());
    }

    public UserDto save(UserDto user){
        var userEntity = mapper.userDtoTOUser(user);
        userRepository.save(userEntity);
        return mapper.userToUserDto(userEntity);
    }

}