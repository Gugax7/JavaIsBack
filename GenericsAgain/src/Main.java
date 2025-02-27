public class Main {
    public static void main(String[] args) {
        // Create a stack for Integer type
        MyStack<Integer> intStack = new MyStack<>();
        intStack.push(10);
        intStack.push(20);
        intStack.push(30);

        System.out.println("Top of intStack: " + intStack.peek()); // Should print 30
        System.out.println("Popped from intStack: " + intStack.pop()); // Should print 30
        System.out.println("Top of intStack after pop: " + intStack.peek()); // Should print 20

        // Create a stack for String type
        MyStack<String> stringStack = new MyStack<>();
        stringStack.push("Apple");
        stringStack.push("Banana");
        stringStack.push("Cherry");

        System.out.println("Top of stringStack: " + stringStack.peek()); // Should print "Cherry"
        System.out.println("Popped from stringStack: " + stringStack.pop()); // Should print "Cherry"
        System.out.println("Top of stringStack after pop: " + stringStack.peek()); // Should print "Banana"

        // Check if a stack is empty
        System.out.println("Is stringStack empty? " + stringStack.isEmpty()); // Should print false
        stringStack.pop(); // Remove "Banana"
        stringStack.pop(); // Remove "Apple"
        System.out.println("Is stringStack empty after removing all elements? " + stringStack.isEmpty()); // Should print true
    }
}