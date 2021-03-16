package com.bottlehs.springboot.backend.springboot_backend.api.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bottlehs.springboot.backend.springboot_backend.api.entity.User;
import com.bottlehs.springboot.backend.springboot_backend.api.exception.AlreadyExistsException;
import com.bottlehs.springboot.backend.springboot_backend.api.repository.UserRepository;
import com.bottlehs.springboot.backend.springboot_backend.api.service.UserService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author bottlehs
 * @since 2020.04.16
 */
@Service
public class UserServiceImpl implements UserService {
  private Logger log = LoggerFactory.getLogger(this.getClass());

  private UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  // 로그인
  @Override
  public User login(String email, String password) {
    return userRepository.findByEmailAndPassword(email, password);
  }

  // 가입
  @Override
  public User join(String email, String password, List<String> roles) {
    Optional<User> user = userRepository.findByEmail(email);
    if (user.isPresent())
      throw new AlreadyExistsException("Duplicate email");

    return userRepository.save(new User(email, password, roles));
  }

  // 로그인
  @Override
  public User getEmail(String id) {
    Optional<User> user = userRepository.findByEmail(id);
    return user.get();
  }

  // 비밀번호 변경
  @Override
  public User updatePassword(Integer userId, String newPassword) {
    User user = userRepository.getOne(userId);
    user.setPassword(newPassword);
    return userRepository.save(user);
  }

  // 탈퇴
  @Override
  public void withdraw(Integer userId) {
    userRepository.deleteById(userId);
  }

  // 등록
  @Override
  public User add(String email, String password) {
    User user = userRepository.getOne(1);
    user.setPassword("");
    return userRepository.save(user);
    // return userRepository.save(new User(email, password, name, nick_name, tp));
  }

  // 조회
  @Override
  public List<User> getAll() {
    return userRepository.findAll();
  }

  // 조회-단건
  @Override
  public User getId(Integer id) {
    return userRepository.getOne(id);
  }

  // 삭제-단건
  @Override
  public void remove(Integer id) {
    userRepository.deleteById(id);
  }

  // 삭제
  @Override
  public void removeAll() {
    userRepository.deleteAll();
  }

  // 수정
  @Override
  public User modify(Integer id, String email, String password, String first_name,
      String last_name) {
    User user = userRepository.getOne(id);
    user.setEmail(email);
    user.setPassword(password);
    user.setFirstName(first_name);
    user.setLastName(last_name);
    return userRepository.save(user);
  }

  // 조회-검색
  @Override
  public Page<User> searchUser(String id, String email, String first_name, String last_name,
      Date start_dt, Date end_dt, Pageable pageable) {
    return userRepository.searchUser(id, email, first_name, last_name, start_dt, end_dt, pageable);
  }
}
