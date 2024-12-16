package entities;

public class Song {
    private String name;
    private double time;

    public Song(String name, double time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }
}
