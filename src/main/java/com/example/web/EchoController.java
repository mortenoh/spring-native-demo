package com.example.web;

import com.example.domain.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/api/echo" )
public class EchoController
{
    @PostMapping
    public ResponseEntity<Response> postEcho( @RequestBody Response response )
    {
        return ResponseEntity.ok( response );
    }
}