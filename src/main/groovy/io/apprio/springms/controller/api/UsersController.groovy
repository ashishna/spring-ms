package io.apprio.springms.controller.api

import io.apprio.springms.api.UserApi
import io.apprio.springms.api.model.UserProfile
import io.apprio.springms.service.UserService
import org.apache.camel.EndpointInject
import org.apache.camel.ProducerTemplate
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.task.TaskExecutor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import java.util.concurrent.CompletableFuture

@RestController
@RequestMapping('/users')
class UsersController implements UserApi {
    private static final Logger LOG = LoggerFactory.getLogger(UsersController);
    
    @Value('${db.password}')
    String dbPassword
    
    @Autowired
    UserService userService
    @Autowired
    TaskExecutor taskExecutor
    
   @EndpointInject('direct:publishUserEvent')
   ProducerTemplate publishUserEvent
    
    @GetMapping
    ResponseEntity<?> greeting(@RequestParam(value="name", defaultValue="World") String name) {
        LOG.info(dbPassword)
        CompletableFuture.supplyAsync ({
           userService.create(new UserProfile(firstName: name))
        }, taskExecutor).thenAccept({
            LOG.info('User created. Publishing message')
            publishUserEvent.send({ it.in.body = 'USER_CREATION_SUCCESS'})
        }).exceptionally({
            LOG.error(it.message)
            publishUserEvent.send({ it.in.body = 'USER_CREATION_FAILED'})
        })
        
        return ResponseEntity.accepted().build()
    }

    @Override
    @PostMapping
    UserProfile create(@RequestBody UserProfile userProfile) {
        CompletableFuture.supplyAsync({
            userService.create(userProfile)
        })
         return ResponseEntity.status(202).body('Request Accepted')
    }

    @Override
    UserProfile update(UserProfile userProfile) {
        return null
    }

    @Override
    UserProfile get(String id) {
        return null
    }

    @Override
    UserProfile findById(String id) {
        return null
    }

    @Override
    List<UserProfile> findBy(UserProfile userProfile) {
        return null
    }
}
