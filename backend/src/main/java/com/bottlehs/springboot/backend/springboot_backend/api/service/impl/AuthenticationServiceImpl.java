package com.bottlehs.springboot.backend.springboot_backend.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.bottlehs.springboot.backend.springboot_backend.api.entity.User;
import com.bottlehs.springboot.backend.springboot_backend.api.exception.UnauthorizedException;
import com.bottlehs.springboot.backend.springboot_backend.api.repository.UserRepository;
import com.bottlehs.springboot.backend.springboot_backend.api.service.AuthenticationService;

/**
 * @author bottlehs
 * @since 2021.02.04
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

  private final UserRepository userRepository;

  @Autowired
  public AuthenticationServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User authenticate(String token) {
    try {
      // authorization으로부터 type과 credential을 분리
      String[] split = token.split(" ");
      String type = split[0];
      String credential = split[1];

      if ("Basic".equalsIgnoreCase(type)) {
        // credential을 디코딩하여 email과 password를 분리
        String decoded = new String(Base64Utils.decodeFromString(credential));
        String[] emailAndPassword = decoded.split(":");

        User user = userRepository.findByEmailAndPassword(emailAndPassword[0], emailAndPassword[1]);
        if (user == null)
          throw new UnauthorizedException("Invalid credentials");
        else
          return user;

      } else {
        throw new UnauthorizedException("Unsupported type: " + type);

      }

    } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException ex) {
      throw new UnauthorizedException("Invalid credentials");
    }
  }
}
