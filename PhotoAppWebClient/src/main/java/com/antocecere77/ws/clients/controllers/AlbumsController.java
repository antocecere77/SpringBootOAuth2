package com.antocecere77.ws.clients.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AlbumsController {

    @GetMapping("/albums")
    public String getAlbums(Model model) {
        return "albums";
    }
}