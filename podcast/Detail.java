package campus.podcast;

/**
 * Created by Akhila on 9/8/2018.
 */
public class Detail {
    private String songname;
    private String duration;
    private String url;

    public Detail(String songname,String duration,String url) {
        this.duration=duration;
        this.songname = songname;
        this.url=url;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSongname() {
        return songname;
    }

    public void setSongname(String songname) {
        this.songname = songname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
