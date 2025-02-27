import java.util.ArrayList;

public class MyStack <T>{

    ArrayList<T> myList;

    public MyStack(){
        this.myList = new ArrayList<>();
    }

    public void push(T item){
        myList.add(item);
    }

    public T pop(){
        return myList.removeFirst();
    }

    public T peek(){
        return myList.getFirst();
    }

    public boolean isEmpty(){
        return myList.size() == 0;
    }
}
