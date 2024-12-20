
import java.util.HashSet;
import java.util.Scanner;

        public class Main {

            public static boolean containsNearbyDuplicate(int[] nums, int k) {
                HashSet<Integer> set = new HashSet<>();

                for (int i = 0; i < nums.length; i++) {
                    if (set.contains(nums[i])) {
                        return true;
                    }
                    set.add(nums[i]);

                    // Maintain the size of the HashSet to be no larger than k
                    if (set.size() > k) {
                        set.remove(nums[i - k]);
                    }
                }

                return false;
            }

            public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);

                System.out.println("Enter the length of the array:");
                int n = scanner.nextInt();

                int[] nums = new int[n];
                System.out.println("Enter the elements of the array:");
                for (int i = 0; i < n; i++) {
                    nums[i] = scanner.nextInt();
                }

                System.out.println("Enter the value of k:");
                int k = scanner.nextInt();

                boolean result = containsNearbyDuplicate(nums, k);
                System.out.println("Output: " + result);
            }
        }

        // all repeated numbers must be within k right?


//        // Create a TreeSet of integers
//        TreeSet<Integer> numbers = new TreeSet<>();
//        TreeSet<String> names = new TreeSet<>();
//
//        // Adding elements to the TreeSet
//        numbers.add(5);
//        numbers.add(1);
//        numbers.add(10);
//        numbers.add(7);
//        numbers.add(3);
//
//        names.add("Gustavo");
//        names.add("Giovana");
//        names.add("Guilherme");
//
//        System.out.println(names);
//
//        // Displaying the TreeSet (sorted order)
//        System.out.println("TreeSet: " + numbers);
//
//        // Trying to add a duplicate element
//        boolean isAdded = numbers.add(7);
//        System.out.println("Was 7 added again? " + isAdded);
//
//        // First (smallest) element
//        System.out.println("First element: " + numbers.first());
//
//        // Last (largest) element
//        System.out.println("Last element: " + numbers.last());
//
//        // Removing an element
//        numbers.remove(5);
//        System.out.println("After removing 5: " + numb
