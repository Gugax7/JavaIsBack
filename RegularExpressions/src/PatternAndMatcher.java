import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternAndMatcher {
    public static void main(String[] args) {

        String sentence = "I love ride... on my bike.";
        boolean matched = Pattern.matches("[A-Z].*[.]",sentence);
        System.out.println(matched + "-> " + sentence);

        Pattern firstPattern = Pattern.compile("[A-Z].*?[.]");
        var matcher = firstPattern.matcher(sentence);
        System.out.println(matcher.matches() + " " + sentence);
        System.out.println("Sentence length = " + sentence.length());
        System.out.println("Matched Ending index = " + matcher.end());

        System.out.println(matcher.lookingAt() + " = " + sentence);
        System.out.println(matcher.end());
        System.out.println("Matched on : " + sentence.substring(0, matcher.end()));

        matcher.reset();

        System.out.println(matcher.find() + " = " + sentence);
        System.out.println(matcher.end());
        System.out.println("Matched on : " + sentence.substring(matcher.start(), matcher.end()));

        System.out.println("Matched on : " + matcher.group());

        String htmlSnippet = """
                <H1>My title</H1>
                <h2>subtitle</h2>
                <p>okay now here i have to tell you my story</p>
                <p>and here i am trying to sell you something really expensive</p>
                <h3>Summary</h3>
                """;

        Pattern htmlPattern = Pattern.compile("<[hH](?<level>[0-9])>(.*)</[hH][0-9]>");
        Matcher matcher1 = htmlPattern.matcher(htmlSnippet);
        while(matcher1.find()){
            System.out.println("Group = " + matcher1.group(2) + "Of level -> " + matcher1.group("level"));
        }


    }
}
