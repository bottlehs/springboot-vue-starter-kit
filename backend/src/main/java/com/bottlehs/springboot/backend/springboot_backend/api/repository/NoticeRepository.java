package com.bottlehs.springboot.backend.springboot_backend.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bottlehs.springboot.backend.springboot_backend.api.entity.Notice;

/**
 * @author bottlehs
 * @since 2021.02.04
 */
public interface NoticeRepository extends JpaRepository<Notice, Integer> {
  Notice findByTitle(String title);

  @Query("select m from Notice m where "
      + "(?1 is null or upper(m.id) like concat('%', upper(?1), '%')) "
      + "and (?2 is null or upper(m.title) like concat('%', upper(?2), '%')) "
      + "and (?3 is null or upper(m.cont) like concat('%', upper(?3), '%')) "
      + "and (?4 is null or upper(m.tp) = upper(?4) ) "
      + "and (?6 is null or upper(m.reg_dt) BETWEEN ?5 and ?6 ) ")
  Page<Notice> searchNotice(String id, String title, String cont, String tp, Date start_dt,
      Date end_dt, final Pageable pageable);
}
