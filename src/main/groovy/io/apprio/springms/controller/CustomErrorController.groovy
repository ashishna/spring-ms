package io.apprio.springms.controller

import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping('/error')
class CustomErrorController implements ErrorController {
    
    @RequestMapping
    String error() {
        return 'An error occurred while invoking api'
    }
    
    @Override
    String getErrorPath() {
        return '/error'
    }
}
