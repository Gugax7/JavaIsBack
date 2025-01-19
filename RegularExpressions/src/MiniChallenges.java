import java.util.List;

public class MiniChallenges {
    public static void main(String[] args) {

        // first challenge
        String hello = "Hello, World!";
        System.out.println(hello.matches("Hello, World!"));

        // second challenge
        String secondPhrase = "How are you?";
        boolean matches = secondPhrase.matches("[A-Z].*\\.");
        System.out.println(matches);

        String challenge3 = "[A-Z].+[.?!]";
        String anotherWay3 = "^[A-Z][\\p{all}]+[?!.]";

        for(String s : List.of(
                "The bike is red, and has flat tires.",
                "I love being a new L.P.A. student!",
                "Hello, friends and family: Welcome!",
                "How are you, Mary!",
                "AKAY."
        )){
            boolean itMatches = s.matches(anotherWay3);
            System.out.println(itMatches + " -> " + s);
        }


    }
}
