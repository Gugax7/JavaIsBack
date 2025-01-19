import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println(format("%s %s","Hello","World"));


        String testString = "Anyone can learn abc's, 123's and any regular expressions!";
        String replacementString = "(x)";
        String[] patterns = {
                "[A-Z]*",
                "[a-z]*",
                "[0-9]*"
        };


        for (String pattern : patterns){
            String output = testString.replaceFirst(pattern,replacementString);
            System.out.println(pattern + " == > " + output);
        }

        String paragraph = """
                Lately, I've been
                I've been losing sleep
                Dreaming about the things that we could be
                But baby, I've been
                I've been praying hard
                Said, no more counting dollars
                We'll be counting stars
                """;

        String[] words = paragraph.split("\\s");
        System.out.println("How many words on paragraph: "+ words.length);
        paragraph = paragraph.replaceAll("[a-zA-Z]+ars|been", "WOW");

        System.out.println(paragraph);

        Scanner scanner = new Scanner(paragraph);
        scanner.useDelimiter("\\R");

//        while(scanner.hasNext()){
//            String element = scanner.next();
//            System.out.println(element);
//        }

//        scanner.tokens()
//                .map(s -> s.replaceAll("\\p{Punct}",""))
//                .flatMap(s -> Arrays.stream(s.split("\\R+")))
//                //.filter(s-> s.matches("[a-zA-Z]+ve"))
//                .forEach(System.out::println);

        System.out.println(scanner.findInLine("[a-zA-Z]+OW"));
        System.out.println(scanner.findInLine("[a-zA-Z]+OW"));

        scanner.close();
    }
    public static String format(String regex, String... args){
        int index = 0;
        while(regex.matches(".*%s.*")){
            regex = regex.replaceFirst("%s", args[index++]);
        }
        return regex;
    }


}