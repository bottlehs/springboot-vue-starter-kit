package com.bottlehs.springboot.backend.springboot_backend.api.service;

import com.bottlehs.springboot.backend.springboot_backend.api.entity.User;

/**
 * @author bottlehs
 * @since 2021.02.04
 */
public interface AuthenticationService {

  User authenticate(String token);
}
