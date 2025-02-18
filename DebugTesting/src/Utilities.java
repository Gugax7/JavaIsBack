public class Utilities {


    //Returns a char array containing every nth char.
    //When sourceArray.length < n, returns sourceArray
    public char[] everyNthChar(char[] sourceArray, int n){
        if(sourceArray == null || sourceArray.length < n){
            return sourceArray;
        }
        int returnedLength = (sourceArray.length / n);
        char[] result = new char[returnedLength];
        int index = 0;
        for(int i = n-1; i < sourceArray.length; i+=n){
            result[index++] = sourceArray[i];
        }
        return result;
    }

    //Removes pairs of the same characters that are next
    //to each other, by removing one occurrence of the character
    //"AABBCCDD" -> "ABCD"
    //"ABABBACEEC" -> "ABABACEC" notice here that even A, B and C
    // having pairs, they aren't next, so they wont be removed.

    public String removePairs(String source){

        //if length is less than 2, there wont be any pairs
        if(source == null ||source.length() < 2 ){
            return source;
        }
        StringBuilder sb = new StringBuilder();
        char[] string = source.toCharArray();
        for(int i = 0; i < string.length - 1; i++){
            if(string[i] != string[i+1]){
                sb.append(string[i]);
            }
        }
        sb.append(string[string.length - 1]);
        return sb.toString();
    }

    //convert based on internal business rule
    public int converter(int a, int b){
        if(b == 0){
            return 0;
        }
        return (a/b) *10 * b - 27;
    }

    public String nullIfOddLength(String source){
        if(source.length() % 2 == 0){
            return source;
        }
        return null;
    }
}
