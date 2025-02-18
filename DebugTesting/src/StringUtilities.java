public class StringUtilities {
    private StringBuilder sb = new StringBuilder();
    private int charAdded = 0;

    public void addChar(StringBuilder sb, char c){
        sb.append(c);
        charAdded++;
    }
    public String upperAndPrefix(String s){
        return "Prefix___" + s.toUpperCase();
    }
    public String addSuffix(String s){
        return s + "___Sufix";
    }
}
