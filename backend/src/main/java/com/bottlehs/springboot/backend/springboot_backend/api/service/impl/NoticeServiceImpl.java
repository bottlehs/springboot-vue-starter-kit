package com.bottlehs.springboot.backend.springboot_backend.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bottlehs.springboot.backend.springboot_backend.api.entity.Notice;
import com.bottlehs.springboot.backend.springboot_backend.api.repository.NoticeRepository;
import com.bottlehs.springboot.backend.springboot_backend.api.service.NoticeService;

import java.util.Date;
import java.util.List;

/**
 * @author bottlehs
 * @since 2020.04.20
 */
@Service
public class NoticeServiceImpl implements NoticeService {

  private NoticeRepository noticeRepository;

  @Autowired
  public NoticeServiceImpl(NoticeRepository noticeRepository) {
    this.noticeRepository = noticeRepository;
  }

  // 등록
  @Override
  public Notice add(String title, String cont, String tp) {
    return noticeRepository.save(new Notice(title, cont, tp));
  }

  // 조회
  @Override
  public List<Notice> getAll() {
    return noticeRepository.findAll();
  }

  // 조회-단건
  @Override
  public Notice getId(Integer id) {
    return noticeRepository.getOne(id);
  }

  // 삭제-단건
  @Override
  public void remove(Integer id) {
    noticeRepository.deleteById(id);
  }

  // 삭제
  @Override
  public void removeAll() {
    noticeRepository.deleteAll();
  }

  // 수정
  @Override
  public Notice modify(Integer id, String title, String cont, String tp) {
    Notice notice = noticeRepository.getOne(id);
    notice.setTitle(title);
    notice.setCont(cont);
    notice.setTp(tp);
    return noticeRepository.save(notice);
  }

  // 조회-검색
  @Override
  public Page<Notice> searchNotice(String id, String title, String cont, String tp, Date start_dt,
      Date end_dt, Pageable pageable) {
    return noticeRepository.searchNotice(id, title, cont, tp, start_dt, end_dt, pageable);
  }
}
