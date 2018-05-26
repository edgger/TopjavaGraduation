package com.github.edgarzed.topjavagraduation.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AdminRestController.REST_URL)
public class UserRestController {
    static final String REST_URL = "/rest/";

}