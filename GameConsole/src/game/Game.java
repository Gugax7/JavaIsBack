package game;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class Game <T extends Player> {

    private final String gameName;
    private final List<T> players = new ArrayList<>();
    private Map<Character, GameAction> standartActions = null;

    public Game(String gameName){
        this.gameName = gameName;
    }
    public Map<Character, GameAction> getStandartActions() {
        if (standartActions == null) {
            standartActions = new LinkedHashMap<>(Map.of(
                    'I', new GameAction('I', "Print player info", i -> this.printPlayer(i)),
                    'Q', new GameAction('Q', new GameAction('Q', "Quit Game", this::quitGame))

            ));
        }
        return standartActions;
    }

    public abstract T createNewPlayer(String name);
    public abstract Map<Character,GameAction> setGameActions(int playerIndex);

}
