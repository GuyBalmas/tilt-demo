package org.example.app.color;

import org.springframework.beans.factory.annotation.Value;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ColorPicker {
    @Value("${color.background}")
    private static String backgroundColor;

    @Value("${color.text}")
    private static String textColor;

    // Random factor for color picking
    private static Random random = new Random();

    private static final String[] HEX_DIGITS = new String[]{"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};

    public static String getBackgroundColor(){
        pick();
        return backgroundColor;
    }

    public static String getTextColor(){
        pick();
        return textColor;
    }

    private static void pick(){
        int r = 0,g = 0,b = 0;
        if (backgroundColor == null || backgroundColor.equals("null") || invalidColor(backgroundColor)){
            r = random.nextInt(256);
            g = random.nextInt(256);
            b = random.nextInt(256);

            // Convert the numbers to hexadecimal strings
            String rs = HEX_DIGITS[(r - (r % 16)) / 16] + HEX_DIGITS[r % 16];
            String gs = HEX_DIGITS[(g - (g % 16)) / 16] + HEX_DIGITS[g % 16];
            String bs = HEX_DIGITS[(b - (b % 16)) / 16] + HEX_DIGITS[b % 16];

            backgroundColor = "#" + rs + gs + bs;
        }

        if (textColor == null || textColor.equals("null") || invalidColor(backgroundColor)){
            double luminance = (0.299 * r + 0.587 * g + 0.114 * b) / 255;
            // dark color text
            if (luminance < 0.5){
                textColor = "#FFF8DC";
            }
            // light color text
            else {
                textColor = "#000000";
            }
        }
    }

    private static boolean invalidColor(String color) {
        boolean isInvalid = true;
        Matcher m = Pattern.compile("#([0-9a-f]{2})([0-9a-f]{2})([0-9a-f]{2})", Pattern.CASE_INSENSITIVE).matcher(color);
        if (m.matches()) {
            isInvalid = false;
            // throw new IllegalArgumentException("Invalid color: " + color);
        }
        return isInvalid;
    }

}
