package com.example.web;

import com.example.domain.DataElement;
import com.example.domain.DataElementWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping( "/api/remote" )
@RequiredArgsConstructor
public class RemoteCallController
{
    private final RestTemplate restTemplate;

    @GetMapping
    public ResponseEntity<List<DataElement>> callRemote()
    {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
            .uri( URI.create( "https://play.dhis2.org/dev" ) )
            .path( "/api/dataElements" )
            .queryParam( "fields", "id,displayName" )
            .queryParam( "paging", false )
            .build()
            .encode();

        ResponseEntity<DataElementWrapper> response = restTemplate.getForEntity( uriComponents.toUri(), DataElementWrapper.class );

        if ( !response.getStatusCode().is2xxSuccessful() || response.getBody() == null )
        {
            throw new HttpServerErrorException( HttpStatus.BAD_GATEWAY );
        }

        return ResponseEntity.ok( response.getBody().getDataElements() );
    }
}