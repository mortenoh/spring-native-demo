package com.example.web;

import com.example.domain.Response;
import com.example.domain.Status;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/api/status" )
public class StatusController
{
    @GetMapping
    public ResponseEntity<Response> getStatus()
    {
        Response response = Response.builder()
            .status( Status.OK )
            .build();

        return ResponseEntity.ok( response );
    }
}

