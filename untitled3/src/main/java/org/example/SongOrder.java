package org.example;
/**
 * Make possible initialization using static factory methods.
 * See requirements in task description.
 */
public class SongOrder {
    private String singer;
    private String songName;

    private SongOrder(String singer) {
        this.singer = singer;
    }

    private SongOrder(String singer, String songName) {
        this.singer = singer;
        this.songName = songName;
    }
    public static SongOrder of(String singer, String songName){
        return new SongOrder(singer,songName);
    }
    public static SongOrder of(String singer){
        return new SongOrder(singer,null);
    }

    public String getSinger() {
        return singer;
    }

    public String getSongName() {
        return songName;
    }

    @Override
    public String toString() {
        if (getSongName() == null && getSinger() == null){
            return  "You haven't chosen a singer. Please make your choice)";
        }
        if (getSongName() == null) {
            return "Play any " + getSinger() + " song";
        }
        return "Play " + getSinger() + " song called " +  "\"" + getSongName() + "\"";
    }
}
