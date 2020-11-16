/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antocecere77.ws.api.photos.controllers;

import com.antocecere77.ws.api.photos.response.PhotoRest;
import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/photos")
public class PhotosController {
    
    @GetMapping
    public List<PhotoRest> getPhotos() { 
        
        PhotoRest photo1 = PhotoRest.builder()
                .albumId("albumIdHere")
                .photoId("1")
                .userId("1")
                .photoTitle("Photo 1 title")
                .photoDescription("Photo 1 description")
                .photoUrl("Photo 1 URL")
                .build();

        PhotoRest photo2 = PhotoRest.builder()
                .albumId("albumIdHere")
                .photoId("2")
                .userId("1")
                .photoTitle("Photo 2 title")
                .photoDescription("Photo 2 description")
                .photoUrl("Photo 2 URL")
                .build();

        return Arrays.asList(photo1, photo2);
    }
 
}
