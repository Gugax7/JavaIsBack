import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        LinkedList<City> list = new LinkedList<City>();
        list.add(new City("Itatiba", 0));
        list.add(new City("Campinas", 20));
        list.add(new City("Sao Paulo", 75));
        list.add(new City("Rio claro", 70));
        list.add(new City("Jundiai", 15));
        list.add(new City("Caraguatatuba", 420));
        var iterator =  list.listIterator();
        showMenu();
        String choose = sc.nextLine();
        while (choose.charAt(0) != 'Q'){
            switch (choose.charAt(0)){
                case 'F' -> {
                    if (iterator.hasNext()){
                        System.out.println(iterator.next().toString());
                    }
                    else{
                        System.out.println("The list ended!");
                    }
                }
                case 'B' ->{
                    if (iterator.hasPrevious()){
                        System.out.println(iterator.previous().toString());
                    }
                    else{
                        System.out.println("The list ended!");
                    }
                }
                case 'L'-> listCities(list);
                case 'M'-> showMenu();
                default -> System.out.println("Sorry i don't understand");
            }
            choose = sc.nextLine();
        }


        sc.close();
    }

    public static void returnIteratorCursorTo1(ListIterator iterator){
        while(iterator.hasPrevious()){
            iterator.previous();
        }
    }

    public static void listCities(LinkedList<City> list){
        var iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    public static void showMenu(){
        System.out.println("Available actions (select word or letter)\n(F)oward\n(B)ackward\n(L)ist Places\n(M)enu\n(Q)uit");
    }


}
//    public static void main(String[] args) {
//        LinkedList<String> linkedList = new LinkedList<>();
//        //
//
//        linkedList.add("Peter");
//        linkedList.add("Andreas");
//
//        System.out.println(linkedList);
//        Main.addMoreElementsTo(linkedList);
//        System.out.println(linkedList);
//        var iterator = linkedList.listIterator();
//        while(iterator.hasNext()){
//            System.out.println(iterator.next());
//        }
//
//        while(iterator.hasPrevious()){
//            System.out.println(iterator.previous());
//        }
//
//        iterator.next();
//        System.out.println(iterator.next());
//
//    }
//    public static void addMoreElementsTo(LinkedList<String> linkedList){
//        linkedList.addFirst("Jonathan");
//        linkedList.addLast("Antonio");
//        linkedList.offer("Alejandro");
//        linkedList.offerLast("Alberto");
//    }
//}