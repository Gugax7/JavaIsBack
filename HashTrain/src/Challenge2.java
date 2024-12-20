public class Challenge2 {

    public static void main(String[] args) {
        int[] arrayNumber = {1,0,1};
        System.out.println(isSomeNearEquals(arrayNumber, 1));

    }

    public static boolean isSomeNearEquals(int[] nums, int k){
        for(int i = 0; i < nums.length ; i++){
            for(int j = i+1; j <= i+k && j < nums.length; j++){
                if(nums[i] == nums[j]){
                    return true;
                }
            }
        }
        return false;
    }
}
