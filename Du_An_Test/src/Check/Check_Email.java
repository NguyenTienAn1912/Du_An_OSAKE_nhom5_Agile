/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Check;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
/**
 *
 * @author Admin
 */
public class Check_Email {
    public static void parseEmail(String email) throws Exception{
        String mau = "\\w+@\\w+(\\.\\w+){1,2}";
        Pattern pattern = Pattern.compile(mau);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches() == false) 
            throw new Exception();
    }
}
