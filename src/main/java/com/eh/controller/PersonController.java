package com.eh.controller;


import com.eh.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PersonController implements PersonApiContract{

    @Override
    @SneakyThrows
    public String create(@Valid Person person) {
        return new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(person);
    }
}
