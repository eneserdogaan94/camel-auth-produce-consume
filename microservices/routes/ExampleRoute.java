package com.testingcamel.microservices.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import static org.apache.camel.LoggingLevel.INFO;

@Component
public class ExampleRoute extends RouteBuilder {

    @Override
    public void configure() {

        from("timer:foo?period=100000")
                .routeId("Main Route")
                .log(INFO,this.log, "first message")
                .to("direct:http");


    }
}