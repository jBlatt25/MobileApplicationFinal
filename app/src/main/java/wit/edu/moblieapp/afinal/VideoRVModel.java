package wit.edu.moblieapp.afinal;

import android.graphics.Bitmap;

public class VideoRVModel
{

    //variable names
    private  String VideoName;
    private  String VideoPath;
    private Bitmap  thumbNail;

    // construtor of the model
    public VideoRVModel(String videoName, String videoPath, Bitmap thumbNail)
    {
       this.VideoName = videoName;
       this.VideoPath = videoPath;
       this.thumbNail = thumbNail;
    }

    public String getVideoName() {
        return VideoName;
    }

    public void setVideoName(String videoName) {
        VideoName = videoName;
    }

    public String getVideoPath() {
        return VideoPath;
    }

    public void setVideoPath(String videoPath) {
        VideoPath = videoPath;
    }

    public Bitmap getThumbNail() {
        return thumbNail;
    }

    public void setThumbNail(Bitmap thumbNail) {
        this.thumbNail = thumbNail;
    }
}
