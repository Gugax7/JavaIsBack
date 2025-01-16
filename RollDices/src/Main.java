import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        Random random = new Random();
        Scanner sc = new Scanner(System.in);
       List<Integer> dices = random.ints(5,1,7)
               .boxed()
               .collect(Collectors.toList());
       while(true){
            System.out.print("Your dices: [");
            String diceString = dices.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));

            System.out.println(diceString + "]");

            System.out.println("""
                    Press Enter to score
                    Type "ALL" to re-roll all dices
                    List numbers (separated by spaces) to re-roll selected dice""");

            System.out.print("--->");
            String answer = sc.nextLine();

            if(answer.isEmpty()){
                break;
            }
            if(answer.equals("ALL")){
                dices = random.ints(5,1,7)
                        .boxed()
                        .collect(Collectors.toList());
            }
            else{
                List<String> dicesToRoll = new ArrayList<>(List.of(answer.split(" ")));
                List<Integer> keptDices = new ArrayList<>();
                for(int i = 0; i < dices.size(); i++){
                    if(!dicesToRoll.contains(String.valueOf(i+1))){
                        keptDices.add(dices.get(i));
                    }
                }
                String keptDicesString = keptDices.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(", "));
                for(String n : dicesToRoll){
                    dices.set(Integer.parseInt(n)-1, random.nextInt(1,7));
                }

                System.out.println("Keeping: [" + keptDicesString + "]");
            }
       }

    }
}