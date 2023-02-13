package Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public  class  Regex {
    public  static boolean checkRegexUserName(String name){
        Pattern pattern = Pattern.compile("^[a-z0-9_-]{8,15}$");

        Matcher matcher = pattern.matcher(name);
        if (matcher.matches()){
            return true;
        }
        return false;
    }
    public static boolean checkRegexPassWord(String passWord){
        Pattern pattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");

        Matcher matcher = pattern.matcher(passWord);
        if (matcher.matches()){
            return true;
        }
        return false;
    }
    public static boolean checkRegexEmail(String email){
        Pattern pattern = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$");

        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()){
            return true;
        }
        return false;
    }
    public static  boolean checkRegexPhoneNumber(String numberPhone){
        Pattern pattern = Pattern.compile("^[0-9]{10}$");

        Matcher matcher = pattern.matcher(numberPhone);
        if (matcher.matches()){
            return true;
        }
        return false;
    }
    public static  boolean checkRegexAddress(String address){
        Pattern pattern = Pattern.compile("^(\\w|\\s)*$");

        Matcher matcher = pattern.matcher(address);
        if (matcher.matches()){
            return true;
        }
        return false;
    }
}