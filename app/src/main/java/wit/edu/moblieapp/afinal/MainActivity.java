package wit.edu.moblieapp.afinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            Fragment video_view = new Video_View();
            transaction.replace(R.id.container, video_view);
            transaction.commit();
        }

        /***
         * Main Activity may handle the video loading, but fragment, 'video-view.java' should handle only the UI portion
         *  - Correct me if I'm wrong, just writing what might be how it works or not - Frankie
         */

    }
}