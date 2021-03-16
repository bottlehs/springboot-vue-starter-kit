package com.bottlehs.springboot.backend.springboot_backend.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.bottlehs.springboot.backend.springboot_backend.api.module.common.base.entity.BaseEntity;
import com.bottlehs.springboot.backend.springboot_backend.api.module.common.base.entity.Comment;

/**
 * @author bottlehs
 * @since 2020.04.18
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Notice extends BaseEntity {
  private static final long serialVersionUID = 9141498463876264960L;

  @Comment("식별자")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Comment("제목")
  @Column(length = 100)
  private String title;

  @Comment("내용")
  @Column(length = 10000)
  private String cont;

  @Comment("분류")
  @Column(length = 10)
  private String tp;

  protected Notice() {
  }

  public Notice(String title, String cont, String tp) {
    this.title = title;
    this.cont = cont;
    this.tp = tp;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getCont() {
    return cont;
  }

  public void setCont(String cont) {
    this.cont = cont;
  }

  public String getTp() {
    return tp;
  }

  public void setTp(String tp) {
    this.tp = tp;
  }
}
