import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Review {

    public static void main(String[] args) {

        String phoneList = """
                (11) 90148-1852
                (11)94810-6802
                (11)90129 1492
                11 94295-9304
                11 94205 0293
                11 925992953
                11929858853
                """;

        Pattern phonePattern = Pattern.compile("\\(*[0-9]{2}\\)* *[0-9]{5}[- ]*[0-9]{4}");
        var phoneMatcher = phonePattern.matcher(phoneList);
        phoneMatcher.results()
                .forEach(pm -> System.out.println(pm.group()));

        String htmlText = """
            <H1>TITLE</h1>
            <h2> SuB-TiTlE </h2>
            <p style="pipipi> popopo </p>
            <h3 id = "third"> pipipi popopo </h3>
            <br/>
            <p> more pipipi popopo </p>
            """;

        Pattern htmlPattern = Pattern.compile("<(\\w+)[^>]*>([^\\v</>]*)(</\\1>)*",
                Pattern.CASE_INSENSITIVE);
        Matcher m = htmlPattern.matcher(htmlText);

        m.results()
                .forEach(mr -> System.out.println(mr.group()));


        String emailString = """
                john.boy@valid.com
                jane.doe-smith@valid.co.uk
                jane_Doe1976@valid.co.uk
                bob-1964@valid.net
                elaine@valid-test.com.au
                david@valid.io
                john.boy@invalid
                bob!@invalid.com
                elaineinvalid1983@.com
                david@invalid..com
                gusta_email@valid.fd.com.br
                """;

        Pattern emailPattern = Pattern.compile("([\\w.-]+)@(([\\w-]+\\.)+[\\w-]{2,})");
        Matcher emailMatcher = emailPattern.matcher(emailString);

        emailMatcher.results()
                .forEach(mr -> System.out.println(mr.group()));






    }

}
