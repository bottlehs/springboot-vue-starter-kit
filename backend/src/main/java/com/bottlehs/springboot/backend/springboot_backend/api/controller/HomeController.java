package com.bottlehs.springboot.backend.springboot_backend.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author bottlehs
 * @since 2021.02.04
 */
@Controller
public class HomeController {
  private Logger log = LoggerFactory.getLogger(this.getClass());

  @GetMapping("/")
  public String index() {
    return "index.html";
  }

}
