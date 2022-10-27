/*
 * Copyright (c) 2022-2022 Morten Olav Hansen <morten@winterop.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.example.web;

import java.net.URI;
import java.util.List;

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

import com.example.domain.DataElement;
import com.example.domain.DataElementWrapper;

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

        ResponseEntity<DataElementWrapper> response = restTemplate.getForEntity( uriComponents.toUri(),
            DataElementWrapper.class );

        if ( !response.getStatusCode().is2xxSuccessful() || response.getBody() == null )
        {
            throw new HttpServerErrorException( HttpStatus.BAD_GATEWAY );
        }

        return ResponseEntity.ok( response.getBody().getDataElements() );
    }
}