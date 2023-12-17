import org.hamcrest.Matchers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestJava {
    public static void main(String[] args) {
        String tempStr = "\\1000";
        Pattern pattern = Pattern.compile("([~`!@#$%^&*_\'/\\-+\\\\=\"])|([a-zA-Z])|([ㄱ-ㅎ][ㅏ-ㅣ][가-힣])");
        Matcher match = pattern.matcher(tempStr);

        if (match.find()) {
            System.out.println("match = "+match.group());
        }

    }
}
