package org.example.app.controllers;

import org.example.app.html.HtmlTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.stream.Collectors;


@RestController
public class HelloController {
	private final String coffeeImagePath = "https://media.istockphoto.com/id/1142411258/photo/how-to-make-coffee-latte-art.jpg?b=1&s=170667a&w=0&k=20&c=Yy1VEmlihscejIQ7o5hd43Jq5elwEpAiF32suyiS2M0=";

	@Value("${greeting.message}")
	private String greeting_message;

	@Value("${greeting.description}")
	private String greeting_desc;

	@Value("${hostname}")
	private String podName;

	@Value("${namespace}")
	private String namespace;

	/**
	 * 	Get all environment variables prefixed with "KUBERNETES_"
 	 */
	private Map<String, String> k8sEnvVars = System.getenv()
			.entrySet()
			.stream()
			.filter(entry -> entry.getKey().startsWith("KUBERNETES_"))
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));


	@RequestMapping("/")
	public String index() {
		 return HtmlTemplate.htmlLandingPage(
				 "Hello from example-app!",
				 "a simple Java spring-Boot web application</br>",
				 "Application pod: '" + podName + "'</br> Namespace: '" + namespace + "'"
		 );

	}

	@RequestMapping("/welcome")
	public String welcome(){
		return HtmlTemplate.htmlLandingPage(
				greeting_message,
				greeting_desc
		);
	}

	@RequestMapping("/k8s")
	public String k8s_env(){
		return HtmlTemplate.htmlLandingPage(
				"Kubernetes Environment Variables",
				k8sEnvVars.toString()
		);
	}

	@RequestMapping("/coffee")
	public String coffee(){
		return HtmlTemplate.htmlLandingPage(
				"Who doesn't like coffee?",
				"my favorite is Cappuccino!",
				"",
				coffeeImagePath
		);
	}
}