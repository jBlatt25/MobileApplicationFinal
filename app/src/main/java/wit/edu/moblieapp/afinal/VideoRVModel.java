package wit.edu.moblieapp.afinal;


public class VideoRVModel {

    private String title;
    private int imgid;
    private String streamKey = "";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }

    public void setStreamKey(String streamKey){ this.streamKey = streamKey;}

    public String getStreamKey(){
        return this.streamKey;
    }


    public VideoRVModel(String title, String streamKey, int imgid) {
        this.title = title;
        if(!streamKey.isEmpty()){
            this.streamKey = streamKey;
        }

        this.imgid = imgid;

    }
}