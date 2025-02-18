public class Main {
    public static void main(String[] args) {
        StringUtilities utilities = new StringUtilities();
        StringBuilder sb = new StringBuilder();
        while(sb.length() < 10){
            utilities.addChar(sb, 'a');
        }
        System.out.println(sb);

        String str = "mynameiswhatmynameiswho";
        str = utilities.addSuffix(utilities.upperAndPrefix(str));
        System.out.println(str);
    }
}