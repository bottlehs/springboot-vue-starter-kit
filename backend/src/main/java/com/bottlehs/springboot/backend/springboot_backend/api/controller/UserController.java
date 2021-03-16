package com.bottlehs.springboot.backend.springboot_backend.api.controller;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.*;
import com.bottlehs.springboot.backend.springboot_backend.api.config.security.JwtTokenProvider;
import com.bottlehs.springboot.backend.springboot_backend.api.entity.User;
import com.bottlehs.springboot.backend.springboot_backend.api.service.UserService;

/**
 * @author bottlehs
 * @since 2021.02.04
 */
@RestController
@Api(tags = "회원")
@RequestMapping(value = "/api")
public class UserController {
  private Logger log = LoggerFactory.getLogger(this.getClass());

  private final UserService userService;
  private final JwtTokenProvider jwtTokenProvider;

  @Autowired
  public UserController(UserService userService, JwtTokenProvider jwtTokenProvider) {
    this.userService = userService;
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @ApiOperation(value = "로그인", notes = "로그인", tags = "회원")
  @PostMapping(value = "/login")
  public String login(@RequestParam String email, String password) {
    User user = userService.login(email, password);
    return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
  }

  @ApiOperation(value = "회원가입", notes = "회원가입", tags = "회원")
  @PostMapping(value = "/register")
  public User create(@RequestParam String email, String password) {
    return userService.join(email, password, Collections.singletonList("ROLE_USER"));
  }

  @ApiOperation(value = "로그인회원 정보 조회", notes = "로그인회원 정보 조회", tags = "회원")
  @GetMapping(value = "/me")
  public User getMe() {
    // SecurityContext에서 인증받은 회원의 정보를 얻어온다.
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String id = authentication.getName();
    return userService.getEmail(id);
  }

  @ApiOperation(value = "비밀번호 변경", notes = "비밀번호 변경", tags = "회원")
  @PutMapping(value = "/password")
  public User updatePassword(@RequestAttribute User user, @RequestParam String password) {
    return userService.updatePassword(user.getId(), password);
  }

  @ApiOperation(value = "탈퇴", notes = "탈퇴", tags = "회원")
  @DeleteMapping(value = "/withdraw")
  public void withdraw(@RequestAttribute User user) {
    userService.withdraw(user.getId());
  }

  @ManyToMany(fetch = FetchType.LAZY)
  @ApiOperation(value = "조회", notes = "전체 조회", tags = "회원")
  @GetMapping(value = "/user")
  @ResponseStatus(value = HttpStatus.OK)
  public List<User> getAll() {
    return userService.getAll();
  }

  @ManyToMany(fetch = FetchType.LAZY)
  @ApiOperation(value = "조회-단건", notes = "단건 조회", tags = "회원")
  @GetMapping(value = "/user/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public User get(@PathVariable Integer id) {
    return userService.getId(id);
  }

  @ApiOperation(value = "등록", notes = "등록", tags = "회원")
  @PostMapping(value = "/user")
  @ResponseStatus(value = HttpStatus.OK)
  public User add(@RequestBody User user) {
    return userService.add(user.getEmail(), user.getPassword());
  }

  @ApiOperation(value = "삭제", notes = "전체 삭제", tags = "회원")
  @DeleteMapping(value = "/user")
  @ResponseStatus(value = HttpStatus.OK)
  public void removeAll() {
    userService.removeAll();
  }

  @ApiOperation(value = "삭제-단건", notes = "단건 삭제", tags = "회원")
  @DeleteMapping(value = "/user/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public void remove(@PathVariable Integer id) {
    userService.remove(id);
  }

  @ApiOperation(value = "수정", notes = "단건 수정", tags = "회원")
  @PutMapping(value = "/user/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public User modify(@PathVariable Integer id, @RequestBody User user) {
    return userService.modify(id, user.getEmail(), user.getPassword(), user.getFirstName(),
        user.getLastName());
  }

  @ManyToMany(fetch = FetchType.LAZY)
  @ApiOperation(value = "조회-검색", notes = "전체 조회-검색", tags = "회원")
  @GetMapping(value = "/user/search")
  @ResponseStatus(value = HttpStatus.OK)
  public Page<User> searchUser(@RequestParam(value = "id", required = false) String id,
      @RequestParam(value = "email", required = false) String email,
      @RequestParam(value = "first_name", required = false) String first_name,
      @RequestParam(value = "last_name", required = false) String last_name,
      @RequestParam(value = "start_dt", required = false)
      @DateTimeFormat(pattern = "yyyy-MM-dd") Date start_dt,
      @RequestParam(value = "end_dt", required = false)
      @DateTimeFormat(pattern = "yyyy-MM-dd") Date end_dt,
      @PageableDefault(sort = {"reg_dt"}, direction = Sort.Direction.DESC) Pageable pageable) {
    return userService.searchUser(id, email, first_name, last_name, start_dt, end_dt, pageable);
  }
}
