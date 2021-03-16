package com.bottlehs.springboot.backend.springboot_backend.api.controller;

import java.util.Date;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
import com.bottlehs.springboot.backend.springboot_backend.api.entity.Notice;
import com.bottlehs.springboot.backend.springboot_backend.api.service.NoticeService;

/**
 * @author bottlehs
 * @since 2021.02.04
 */
@RestController
@Api(tags = "공지사항")
@RequestMapping(value = "/api")
public class NoticeController {
  private Logger log = LoggerFactory.getLogger(this.getClass());

  private final NoticeService noticeService;

  public NoticeController(NoticeService noticeService) {
    this.noticeService = noticeService;
  }

  @ManyToMany(fetch = FetchType.LAZY)
  @ApiOperation(value = "조회", notes = "전체 조회", tags = "공지사항")
  @GetMapping(value = "/notice")
  @ResponseStatus(value = HttpStatus.OK)
  public List<Notice> getAll() {
    return noticeService.getAll();
  }

  @ManyToMany(fetch = FetchType.LAZY)
  @ApiOperation(value = "조회-단건", notes = "단건 조회", tags = "공지사항")
  @GetMapping(value = "/notice/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public Notice get(@PathVariable Integer id) {
    return noticeService.getId(id);
  }

  @ApiOperation(value = "등록", notes = "등록", tags = "공지사항")
  @PostMapping(value = "/notice")
  @ResponseStatus(value = HttpStatus.OK)
  public Notice add(@RequestBody Notice notice) {
    return noticeService.add(notice.getTitle(), notice.getCont(), notice.getTp());
  }

  @ApiOperation(value = "삭제", notes = "전체 삭제", tags = "공지사항")
  @DeleteMapping(value = "/notice")
  @ResponseStatus(value = HttpStatus.OK)
  public void removeAll() {
    noticeService.removeAll();
  }

  @ApiOperation(value = "삭제-단건", notes = "단건 삭제", tags = "공지사항")
  @DeleteMapping(value = "/notice/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public void remove(@PathVariable Integer id) {
    noticeService.remove(id);
  }

  @ApiOperation(value = "수정", notes = "단건 수정", tags = "공지사항")
  @PutMapping(value = "/notice/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public Notice modify(@PathVariable Integer id, @RequestBody Notice notice) {
    return noticeService.modify(id, notice.getTitle(), notice.getCont(), notice.getTp());
  }

  @ManyToMany(fetch = FetchType.LAZY)
  @ApiOperation(value = "조회-검색", notes = "전체 조회-검색", tags = "공지사항")
  @GetMapping(value = "/notice/search")
  @ResponseStatus(value = HttpStatus.OK)
  public Page<Notice> searchNotice(@RequestParam(value = "id", required = false) String id,
      @RequestParam(value = "title", required = false) String title,
      @RequestParam(value = "cont", required = false) String cont,
      @RequestParam(value = "tp", required = false) String tp,
      @RequestParam(value = "start_dt", required = false)
      @DateTimeFormat(pattern = "yyyy-MM-dd") Date start_dt,
      @RequestParam(value = "end_dt", required = false)
      @DateTimeFormat(pattern = "yyyy-MM-dd") Date end_dt,
      @PageableDefault(sort = {"reg_dt"}, direction = Sort.Direction.DESC) Pageable pageable) {
    return noticeService.searchNotice(id, title, cont, tp, start_dt, end_dt, pageable);
  }
}
