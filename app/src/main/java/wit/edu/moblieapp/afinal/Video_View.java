package wit.edu.moblieapp.afinal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Video_View extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        /***
         * Main Activity may handle the video loading, but fragment, 'video-view.java' should handle only the UI portion
         *  - Correct me if I'm wrong, just writing what might be how it works or not - Frankie
         */


        return inflater.inflate(R.layout.fragment_video__view, container, false);
    }
}