package wit.edu.moblieapp.afinal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class EditVideoFragment extends Fragment {
    SQLiteDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_edit_video, container, false);

        String path = "/data/data/" + getActivity().getPackageName() + "/login.db";
        db = SQLiteDatabase.openOrCreateDatabase(path, null);

        Bundle bundle = this.getArguments();
        int uuid = bundle.getInt("uuid");
        int vid = bundle.getInt("vid");
        String streamURL = bundle.getString("URI");
        String streamKey = bundle.getString("StreamKey");


        EditText videoName = rootView.findViewById(R.id.nameInputCreate);
        EditText videoPath = rootView.findViewById(R.id.pathInputCreate);
        Button changeBtn = rootView.findViewById(R.id.changeBtn);
        Button deleteBtn = rootView.findViewById(R.id.deleteBtn);

        videoName.setText(streamURL);
        videoPath.setText(streamKey);

        changeBtn.setOnClickListener(v -> {
            ContentValues values = new ContentValues();
            values.put("name", videoName.getText().toString());
            values.put("path", videoPath.getText().toString());

            String where = "vid=? AND uuid=?";
            String[] whereArgs = {String.valueOf(vid), String.valueOf(uuid)};

            db.update("Video", values, where, whereArgs);

            db.close();

            Bundle newBundle = new Bundle();
            newBundle.putInt("uuid", uuid);

            FragmentManager fm = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            Fragment recyclerFragment = new RecyclerFragment();
            recyclerFragment.setArguments(newBundle);
            transaction.replace(R.id.container, recyclerFragment);
            transaction.commit();

        });

        deleteBtn.setOnClickListener(v -> {
            String where = "vid=? AND uuid=?";
            String[] whereArgs = {String.valueOf(vid), String.valueOf(uuid)};
            db.delete("Video", where, whereArgs);


            Bundle newBundle = new Bundle();
            newBundle.putInt("uuid", uuid);

            FragmentManager fm = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            Fragment recyclerFragment = new RecyclerFragment();
            recyclerFragment.setArguments(newBundle);
            transaction.replace(R.id.container, recyclerFragment);
            transaction.commit();
        });

        return rootView;
    }
}