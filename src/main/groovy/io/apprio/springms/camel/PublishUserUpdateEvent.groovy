package io.apprio.springms.camel

import org.apache.camel.LoggingLevel
import org.apache.camel.spring.SpringRouteBuilder
import org.springframework.stereotype.Component

@Component
class PublishUserUpdateEvent  extends SpringRouteBuilder  {
    
    @Override
    void configure() {
        from('direct:publishUserEvent')
        .routeId('publishUserEventRoute')
            .log(LoggingLevel.INFO, 'Received ${body} event. Sending an email')
            .choice()
                .when(simple('${body} == \'USER_CREATION_FAILED\''))
                    .log('Sending email for user creation failed.')
                .when(simple('${body} == \'USER_CREATION_SUCCESS\''))
                    .log('Sending email for user creation successful.')
            .end()
        .to('rabbitmq:user_events')
    }
}
