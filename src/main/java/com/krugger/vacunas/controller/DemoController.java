package com.krugger.vacunas.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Operation(
            summary = "Demo para pruebas",
            description = "Api para probar el funcionamiento"
    )
    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }
}