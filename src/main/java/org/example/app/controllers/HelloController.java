package org.example.app.controllers;

import org.example.app.color.ColorPicker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@RestController
public class HelloController {

	@Value("${greeting.message}")
	private String greeting_message;

	@Value("${greeting.description}")
	private String greeting_desc;

	@Value("${hostname}")
	private String podName;

	@Value("${namespace}")
	private String namespace;

	private ColorPicker colorPicker = new ColorPicker();

	// Get all environment variables prefixed with "K8S_"
	private Map<String, String> k8sEnvVars = System.getenv()
			.entrySet()
			.stream()
			.filter(entry -> entry.getKey().startsWith("KUBERNETES_"))
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));


	@RequestMapping("/")
	public String index() {
		 return htmlLandingPage("Hello from example-app!",
				 "a simple Java spring-Boot web application</br>",
				 "Application pod: '" + podName + "'</br> Namespace: '" + namespace + "'");

	}

	@RequestMapping("/welcome")
	public String welcome(){
		return htmlLandingPage(greeting_message,
				greeting_desc);
	}

	@RequestMapping("/k8s")
	public String k8s_env(){
		return htmlLandingPage("Kubernetes Environment Variables",
				k8sEnvVars.toString());
	}

	@RequestMapping("/coffee")
	public String coffee(){
		return htmlLandingPage("Who doesn't like coffee?",
				"my favorite is Cappuccino!");
	}

	private String htmlLandingPage(String header, String description){
		return htmlLandingPage(header, description,"");
	}

	private String htmlLandingPage(String header, String description, String data){
		return "<!DOCTYPE html>\n" +
				"<html>\n" +
				"<head>\n" +
				"    <title>Hello World!</title>\n" +
				"</head>\n" +
				"<body style=\"background: " + colorPicker.getBackgroundColor() + ";\"></body>\n" +
				"<div style=\"color: " + colorPicker.getTextColor() + ";\n" +
				"\t\t\ttext-align:  center;\n" +
				"\t\t\theight: 90px;\n" +
				"\t\t\tvertical-align:  middle;\n" +
				"\t\t\tfont-family:'Trebuchet MS'\">\n" +
				"    <h1>" + header + "</h1>\n" +
				"    <h3>" + description + "</h3>\n\n" +
				"    <h4>" + data + "</h4>\n" +
				"</div>\n" +
				"</body>\n" +
				"</html>";
	}
}