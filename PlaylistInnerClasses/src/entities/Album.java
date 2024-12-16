package entities;

import java.util.ArrayList;
import java.util.LinkedList;

public class Album {
    private String name;
    private String band;
    private SongList songList;

    public Album(String name, String band) {
        this.name = name;
        this.band = band;
        this.songList = new SongList();
    }
    private static class SongList{
        ArrayList<Song> songs = new ArrayList<>();

        private boolean add(Song song){
            if(songs.contains(song)){
                return false;
            }
            songs.add(song);
            return true;
        }
        private Song findSong(String name){
            for(Song song: songs){
                if(song.getName() == name){
                    return song;
                }
            }
            return null;
        }
        private Song findSong(int index){
            if(index >= songs.size() || index < 0){
                return null;
            }
            return songs.get(index);
        }

        private ArrayList<Song> getSongs() {
            return songs;
        }
    }

    public void addSong(String name, double duration){
        songList.add(new Song(name,duration));
    }
    public void addToPlayList(String name, LinkedList<Song> playlist){
        playlist.add(songList.findSong(name));
    }
    public void addToPlayList( int trackIndex, LinkedList<Song> playlist){
        playlist.add(songList.findSong(trackIndex));
    }

    public ArrayList<Song> getSongList() {
        return songList.getSongs();
    }
}

