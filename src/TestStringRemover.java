import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestStringRemover {

	public static void main(String[] args) {
		String result = removeUrl("\"Tom, Dick, and hairy \" ?????????? #sa?df Leave my girls alone #LIF?HOUSTON tonight FREE ENTRY…");
		System.out.println(result);
	}
	
	private static String removeUrl(String commentstr)
    {
		String urlPattern = "#.[^\\s]+";
        //String urlPattern = "((https?|ftp|gopher|telnet|file|Unsure|http):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        Pattern p = Pattern.compile(urlPattern,Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(commentstr);
        int i = 0;
        while (m.find()) {
        	System.out.println("found: " + i + " : " + m.start() + " - " + m.end());
        	System.out.println(m.group(0));
            commentstr = commentstr.replaceAll(m.group(0),"").trim();
            i++;
        }
        return commentstr;
    }

}
