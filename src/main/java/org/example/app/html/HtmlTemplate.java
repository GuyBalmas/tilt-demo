package org.example.app.html;

import org.example.app.color.ColorPicker;

public abstract class HtmlTemplate {

    private static ColorPicker colorPicker;

    public static String htmlLandingPage(String header, String description){
        return htmlLandingPage(header, description,"", "");
    }

    public static String htmlLandingPage(String header, String description, String data){
        return htmlLandingPage(header, description,data, "");
    }

    public static String htmlLandingPage(String header, String description, String data, String image_path){
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
                "    <img src=\"" + image_path + "\" alt=\"\"/>" +
                "</div>\n" +
                "</body>\n" +
                "</html>";
    }

}
