package com.bottlehs.springboot.backend.springboot_backend.api.module.common.base.entity;

import lombok.Getter;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author bottlehs
 * @since 2021.02.04
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {
  private static final long serialVersionUID = 9141498463876264960L;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "Asia/Seoul")
  @Column(name = "reg_dt", updatable = false)
  private Date reg_dt;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "Asia/Seoul")
  @Column(name = "mod_dt", updatable = true)
  private Date mod_dt;

  // 변환 로직을 직접 구현

  public Date getRegDt() {
    return reg_dt;
  }

  public void setRegDt(Date reg_dt) {
    this.reg_dt = reg_dt;
  }

  public Date getModDt() {
    return mod_dt;
  }

  public void setModDt(Date mod_dt) {
    this.mod_dt = mod_dt;
  }
}
