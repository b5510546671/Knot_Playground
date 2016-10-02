import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestStringRemover {

	public static void main(String[] args) {
		String result = removeUrl("RT @coreyspowell: Eclipse of the sun by a comet, and some of my other favorite #Rosetta moments.  @DiscoverMag http://");
		System.out.println(result);
	}
	
	private static String removeUrl(String commentstr)
    {
		String urlPattern = "http.*";
        //String urlPattern = "((https?|ftp|gopher|telnet|file|Unsure|http):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        Pattern p = Pattern.compile(urlPattern,Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(commentstr);
        int i = 0;
        while (m.find()) {
        	System.out.println("found: " + i + " : " + m.start() + " - " + m.end());
            commentstr = commentstr.replaceAll(m.group(i),"").trim();
            i++;
        }
        return commentstr;
    }

}
