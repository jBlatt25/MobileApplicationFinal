package wit.edu.moblieapp.afinal;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.CallSuper;
import androidx.annotation.MainThread;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.util.VLCVideoLayout;

import java.io.IOException;
import java.util.ArrayList;


public class Video_View extends Fragment {

    private static final boolean USE_TEXTURE_VIEW = false;
    private static final boolean ENABLE_SUBTITLES = true;
    private static final String ASSET_FILENAME = "";

    private VLCVideoLayout mVideoLayout = null;

    private LibVLC mLibVLC = null;
    private MediaPlayer mMediaPlayer = null;


    private static String PACKAGE_NAME;

    /***
     * Main Activity may handle the video loading, but fragment, 'video-view.java' should handle only the UI portion
     *  - Correct me if I'm wrong, just writing what might be how it works or not - Frankie
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_video__view, container, false);


        final ArrayList<String> args = new ArrayList<>();
        args.add("-vvv");
        mLibVLC = new LibVLC(inflater.getContext(), args);
        mMediaPlayer = new MediaPlayer(mLibVLC);
        mVideoLayout = rootView.findViewById(R.id.video_layout);


        PACKAGE_NAME = inflater.getContext().getPackageName();


        return rootView;
    }


    @MainThread
    @CallSuper
    public void onStart() {

        super.onStart();

        mMediaPlayer.attachViews(mVideoLayout, null, ENABLE_SUBTITLES, USE_TEXTURE_VIEW);

        //final Media media = new Media(mLibVLC, String.valueOf(getResources().openRawResource(R.raw.boston_split)));
        final Media media;
        try {
            media = new Media(mLibVLC, getContext().getResources().getAssets().openFd("boston_split.mp4"));
            mMediaPlayer.setMedia(media);
            media.release();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mMediaPlayer.play();
    }
}