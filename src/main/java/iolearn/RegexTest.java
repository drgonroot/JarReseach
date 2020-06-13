package iolearn;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This program tests regular expression matching. Enter a pattern adn strings to match,
 * or hit Cancel to exit. If the pattern contains groups, the group boundaries are displayed
 * int the match.
 * Created by useheart on 2019-06-08
 *
 * @author useheart
 */
public class RegexTest {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter pattern: ");
        String patternString = in.nextLine();

        Pattern pattern = Pattern.compile(patternString);
        while (true) {
            System.out.println("Enter string to match: ");
            String input = in.nextLine();
            if (input == null || input.equals("")) {
                return;
            }

            Matcher matcher = pattern.matcher(input);

            if (matcher.matches()) {
                System.out.println("Match: ");
                int g = matcher.groupCount();
                if (g > 0) {
                    for (int i = 0; i < input.length(); i++) {
                        // Print any empty groups
                        for (int j = 1; j <= g; j++) {
                            if (i == matcher.start(j) && i == matcher.end(j)) {
                                System.out.println("()");
                            }
                        }
                        //Print ( for non-empty groups starting here
                        for (int j = 1; j <= g; j++) {
                            if (i == matcher.start(j) && i != matcher.end(j)) {
                                System.out.println('(');
                            }
                        }

                        System.out.println(input.charAt(i));

                        // print ) for non-empty groups ending here
                        //Print ( for non-empty groups starting here
                        for (int j = 1; j <= g; j++) {
                            if (i + 1 != matcher.start(j) && i != matcher.end(j)) {
                                System.out.println(')');
                            }
                        }
                    }
                    System.out.println();
                } else {
                    System.out.println("No match");
                }
            }
        }
    }
}
