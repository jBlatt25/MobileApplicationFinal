package wit.edu.moblieapp.afinal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.util.VLCVideoLayout;

import java.util.ArrayList;


public class Video_View extends Fragment {

    private static final boolean USE_TEXTURE_VIEW = false;
    private static final boolean ENABLE_SUBTITLES = true;
    private static final String ASSET_FILENAME = "";

    private VLCVideoLayout mVideoLayout = null;

    private LibVLC mLibVLC = null;
    private MediaPlayer mMediaPlayer = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /***
         * Main Activity may handle the video loading, but fragment, 'video-view.java' should handle only the UI portion
         *  - Correct me if I'm wrong, just writing what might be how it works or not - Frankie
         */

        //final ArrayList<String> args = new ArrayList<>();
        //args.add("-vvv");
        //mLibVLC = new LibVLC(inflater.getContext(), args);



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video__view, container, false);
    }






}