package com.bottlehs.springboot.backend.springboot_backend.api.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bottlehs.springboot.backend.springboot_backend.api.entity.User;

/**
 * @author bottlehs
 * @since 2021.02.04
 */

public interface UserService {
  // 로그인
  User login(String email, String password);

  // 가입
  User join(String email, String password, List<String> roles);

  // jwt id 조회
  User getEmail(String id);

  // 비밀번호 변경
  User updatePassword(Integer userId, String newPassword);

  // 탈퇴
  void withdraw(Integer userId);

  // 등록
  User add(String email, String password);

  // 조회
  List<User> getAll();

  // 조회-단건
  User getId(Integer id);

  // 삭제-단건
  void remove(Integer id);

  // 삭제
  void removeAll();

  // 수정
  User modify(Integer id, String email, String password, String first_name, String last_name);

  // 조회-검색
  Page<User> searchUser(String id, String email, String first_name, String last_name, Date start_dt,
      Date end_dt, Pageable pageable);
}
