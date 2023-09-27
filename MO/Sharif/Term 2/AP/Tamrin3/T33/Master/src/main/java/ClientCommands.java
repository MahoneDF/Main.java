import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ClientCommands {
    CREATE_TASK("^create task --name=(?<name>\\S+)( --node=worker(?<node>\\d+))?$"),
    GET_TASK("^get tasks$"),
    GET_NODE("^get nodes$"),
    DELETE_TASK("^k delete task --name=(?<name>\\S+)$")
    ;
    private String regex;
    ClientCommands(String regex1) {
        regex = regex1;
    }

    public static Matcher getMatcher(String input, ClientCommands mainRegex) {
        Matcher matcher = Pattern.compile(mainRegex.regex).matcher(input);
        if (matcher.matches())
            return matcher;
        return null;
    }
}
