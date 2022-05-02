package wit.edu.moblieapp.afinal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RecyclerFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<VideoRVModel> recyclerDataArrayList;
    SQLiteDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recycler, container, false);

        recyclerView= rootView.findViewById(R.id.thumbnailRV);

        // created new array list..

        //COMMENT
        recyclerDataArrayList = new ArrayList<>();

        registerForContextMenu(recyclerView);


        String path = "/data/data/" + getActivity().getPackageName() + "/login.db";
        db = SQLiteDatabase.openOrCreateDatabase(path, null);

        Bundle bundle = this.getArguments();
        int uuid = bundle.getInt("uuid");

        FloatingActionButton fab = rootView.findViewById(R.id.addBtn);

        fab.setOnClickListener(v -> {
            FragmentManager fm = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            Fragment addVideoFrag = new AddVideoFragment();
            addVideoFrag.setArguments(bundle);
            transaction.replace(R.id.container, addVideoFrag).addToBackStack("backVideo");
            transaction.commit();
        });

        String[] column = {"vid", "uuid", "name", "path"};
        String selection = "uuid=?";
        String[] selectionArgs = {String.valueOf(uuid)};

        Cursor c = db.query("Video", column, selection, selectionArgs, null, null, null);

        while(c.moveToNext()){
            String name = c.getString(c.getColumnIndexOrThrow("name"));
            String streamKey = c.getString(c.getColumnIndexOrThrow("path"));
            int vid = c.getInt(c.getColumnIndexOrThrow("vid"));
            recyclerDataArrayList.add(new VideoRVModel(name, streamKey, vid, R.mipmap.ic_launcher));
        }




        // added data from arraylist to adapter class.
        RecyclerViewAdapter adapter=new RecyclerViewAdapter(recyclerDataArrayList, rootView.getContext(), new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(VideoRVModel item) {
                Toast.makeText(getContext(), "Switching to Video", Toast.LENGTH_SHORT).show();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                Fragment video_view = new VideoViewFragment();
                Bundle bundle = new Bundle();
                bundle.putString("URI",item.getTitle());
                bundle.putString("StreamKey",item.getStreamKey());
                video_view.setArguments(bundle);

                transaction.replace(R.id.container, video_view).addToBackStack("tag");
                transaction.commit();
            }

            @Override
            public void onLongItemClick(VideoRVModel item) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                Fragment editVideoView = new EditVideoFragment();
                Bundle bundle = new Bundle();
                bundle.putString("URI",item.getTitle());
                bundle.putString("StreamKey",item.getStreamKey());
                bundle.putInt("vid", item.getVid());
                bundle.putInt("uuid", uuid);
                editVideoView.setArguments(bundle);

                transaction.replace(R.id.container, editVideoView).addToBackStack("tag");
                transaction.commit();



            }
        });

        // setting grid layout manager to implement grid view.
        // in this method '2' represents number of columns to be displayed in grid view.
        GridLayoutManager layoutManager=new GridLayoutManager(rootView.getContext(), 2);

        // at last set adapter to recycler view.
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

}