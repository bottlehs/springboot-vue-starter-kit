package com.bottlehs.springboot.backend.springboot_backend.api.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bottlehs.springboot.backend.springboot_backend.api.entity.Notice;

/**
 * @author bottlehs
 * @since 2021.02.04
 */
public interface NoticeService {
  // 등록
  Notice add(String title, String cont, String tp);

  // 조회
  List<Notice> getAll();

  // 조회-단건
  Notice getId(Integer id);

  // 삭제-단건
  void remove(Integer id);

  // 삭제
  void removeAll();

  // 수정
  Notice modify(Integer id, String title, String cont, String tp);

  // 조회-검색
  Page<Notice> searchNotice(String id, String title, String cont, String tp, Date start_dt,
      Date end_dt, Pageable pageable);
}
