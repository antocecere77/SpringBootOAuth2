package com.antocecere77.clients.sociallogin.social_login_webclient.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexPageController {

    @GetMapping("/")
    public String displayIndexPage(Model mode) {

        return "index";
    }
}
