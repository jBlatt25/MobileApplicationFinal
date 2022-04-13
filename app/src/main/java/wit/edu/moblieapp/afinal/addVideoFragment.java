package wit.edu.moblieapp.afinal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class addVideoFragment extends Fragment {
    SQLiteDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_video, container, false);

        String path = "/data/data/" + getActivity().getPackageName() + "/login.db";
        db = SQLiteDatabase.openOrCreateDatabase(path, null);
        Bundle bundle = this.getArguments();
        int uuid = bundle.getInt("uuid");



        EditText videoName = rootView.findViewById(R.id.nameInputCreate);
        EditText videoPath = rootView.findViewById(R.id.pathInputCreate);
        Button submitBtn = rootView.findViewById(R.id.submitBtn);

        submitBtn.setOnClickListener(v -> {
            String vidName = videoName.getText().toString().trim();
            String vidPath = videoPath.getText().toString().trim();

            ContentValues values = new ContentValues();
            values.put("uuid", uuid);
            values.put("name", vidName);
            values.put("path", vidPath);
            db.insert("Video", null, values);

            db.close();

            Bundle newBundle = new Bundle();
            newBundle.putInt("uuid", uuid);

            FragmentManager fm = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            Fragment recyclerFragment = new recyclerFragment();
            recyclerFragment.setArguments(newBundle);
            transaction.replace(R.id.container, recyclerFragment);
            transaction.commit();



        });


        return rootView;
    }
}