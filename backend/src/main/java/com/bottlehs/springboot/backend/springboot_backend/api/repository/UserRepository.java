package com.bottlehs.springboot.backend.springboot_backend.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bottlehs.springboot.backend.springboot_backend.api.entity.User;

/**
 * @author bottlehs
 * @since 2021.02.04
 */
public interface UserRepository extends JpaRepository<User, Integer> {
  Optional<User> findByEmail(String email);

  User findByEmailAndPassword(String email, String password);
  // User findById(Integer id);

  @Query("select m from User m where "
      + "(?1 is null or upper(m.id) like concat('%', upper(?1), '%')) "
      + "and (?2 is null or upper(m.email) like concat('%', upper(?2), '%')) "
      + "and (?3 is null or upper(m.first_name) like concat('%', upper(?3), '%')) "
      + "and (?4 is null or upper(m.last_name) = upper(?4) ) "
      + "and (?6 is null or upper(m.reg_dt) BETWEEN ?5 and ?6 ) ")
  Page<User> searchUser(String id, String email, String first_name, String last_name, Date start_dt,
      Date end_dt, final Pageable pageable);
}
