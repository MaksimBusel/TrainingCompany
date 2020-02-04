package main.java.com.epam.training;

public class RedirectUrlCreator {

    public static String create(String command){
        return "{request.contextPath}controller?command=" + command;
    }
}
