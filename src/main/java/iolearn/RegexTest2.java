package iolearn;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by useheart on 2019-06-08
 *
 * @author useheart
 */
public class RegexTest2 {

    public static void main(String[] args) {

        Pattern pattern = Pattern.compile("((?<hour>[1-9]|1[0-2]):(?<minute>[0-5][0-9]))[ap]m");
        Matcher matcher = pattern.matcher("11:59am");

        /*while(matcher.find()) {
            System.out.println(matcher.group());
        }*/
        System.out.println(matcher.matches());
        System.out.println(matcher.group("hour"));
        System.out.println(matcher.group("minute"));
        /*System.out.println(matcher.find());
        System.out.println(matcher.group());*/
        System.out.println(matcher.groupCount());
    }
}
