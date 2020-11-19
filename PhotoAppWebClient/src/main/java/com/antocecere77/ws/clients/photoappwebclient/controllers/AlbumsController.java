package com.antocecere77.ws.clients.photoappwebclient.controllers;

import com.antocecere77.ws.clients.photoappwebclient.photoappwebclient.response.AlbumRest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AlbumsController {

    private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    private final RestTemplate restTemplate;

    private final WebClient webClient;

    @GetMapping("/albums")
    public String getAlbums(Model model, @AuthenticationPrincipal OidcUser principal,
                            Authentication authentication) {

        //Alternative method to get authentication object
        //Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();

        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken)authentication;
        OAuth2AuthorizedClient oAuth2Client = oAuth2AuthorizedClientService.loadAuthorizedClient(oauthToken.getAuthorizedClientRegistrationId(), oauthToken.getName());
        String jwtAccessToken = oAuth2Client.getAccessToken().getTokenValue();
        System.out.println("jwtAccessToken = " + jwtAccessToken);

        System.out.println("Principal = " + principal);
        OidcIdToken idToken = principal.getIdToken();
        String idTokenValue = idToken.getTokenValue();
        System.out.println("idTokenValue = " + idTokenValue);

        String url = "http://localhost:8082/albums";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwtAccessToken);
        HttpEntity<List<AlbumRest>> entity = new HttpEntity<>(headers);

        ResponseEntity<List<AlbumRest>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<AlbumRest>>() {});
        List<AlbumRest> albums = responseEntity.getBody();

        //AlbumRest album = AlbumRest.builder()
        //        .albumId("albumOne")
        //        .albumTitle("Album one title")
        //        .albumUrl("http://localhost:8082/albums/1")
        //        .build();
        //
        //AlbumRest album2 = AlbumRest.builder()
        //        .albumId("albumTwo")
        //        .albumTitle("Album two title")
        //        .albumUrl("http://localhost:8082/albums/2")
        //        .build();
        //
        //model.addAttribute("albums", Arrays.asList(album, album2));

        model.addAttribute("albums", albums);
        return "albums";
    }

    @GetMapping("/albums-reactive")
    public String getAlbumsReactive(Model model, @AuthenticationPrincipal OidcUser principal,
                            Authentication authentication) {

        String url = "http://localhost:8082/albums";

        List<AlbumRest> albums = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<AlbumRest>>(){})
                .block();

        model.addAttribute("albums", albums);
        return "albums";
    }
}
