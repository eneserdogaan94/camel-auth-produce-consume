package com.testingcamel.microservices.routes;

import com.testingcamel.microservices.model.Post;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.stereotype.Component;

import static org.apache.camel.LoggingLevel.INFO;

@Component
public class HttpRoute extends RouteBuilder {
	@Override
	public void configure() throws Exception {
		String token="Bearer ->TOKEN HERE<-";
		JacksonDataFormat postDataFormat = new JacksonDataFormat(Post.class);
		postDataFormat.setPrettyPrint(true);

		from("direct:http")
				.log(INFO,this.log, "This will be the second message")
				.setHeader("Authorization", constant(token)) //token is your authentication
				.setHeader(Exchange.HTTP_METHOD, constant("GET")) //method have post-get anothers
				.setHeader("Content-Type",constant("application/json"))
				.to("http://localhost:8080/...") //Method what u want
				.to("log:DEBUG?showBody=true&showHeaders=true")
				.log(INFO,this.log,"${body}");
				//.unmarshal(postDataFormat) // json to java object
				//.log(INFO,this.log, "Deserialized Data: ${body}")
				//.marshal(postDataFormat) // java to json object
				//.log(INFO,this.log, "Serialized Data: ${body}")
				//.to("file:files/output"); //if u need upload file,u can try this.

	//	from("direct:http")
	//			.log("This will be the second message")
	//			.to("https://jsonplaceholder.typicode.com/posts/42")
	//			.unmarshal(postDataFormat)
	//			.log("Deserialized Data: ${body}")
	//			.marshal(postDataFormat)
	//			.log("Serialized Data: ${body}")
	//			.to("file:files/input");
	}
}