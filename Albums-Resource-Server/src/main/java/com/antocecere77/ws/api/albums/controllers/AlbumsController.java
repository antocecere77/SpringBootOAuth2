/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antocecere77.ws.api.albums.controllers;

import com.antocecere77.ws.api.albums.response.AlbumRest;
import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/albums")
public class AlbumsController {
    
    @GetMapping
    public List<AlbumRest> getAlbums() { 
        
        AlbumRest album1 = AlbumRest.builder()
                .albumId("albumIdHere")
                .userId("1")
                .albumTitle("Album 1 title")
                .albumDescription("Album 1 description")
                .albumUrl("Album 1 URL")
                .build();

        AlbumRest album2 = AlbumRest.builder()
                .albumId("albumIdHere")
                .userId("2")
                .albumTitle("Album 2 title")
                .albumDescription("Album 2 description")
                .albumUrl("Album 2 URL")
                .build();
         
        return Arrays.asList(album1, album2);
    }
 
}
