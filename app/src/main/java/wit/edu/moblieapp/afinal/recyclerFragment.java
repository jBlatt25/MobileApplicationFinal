package wit.edu.moblieapp.afinal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class recyclerFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<VideoRVModel> recyclerDataArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recycler_fragment, container, false);

        recyclerView= rootView.findViewById(R.id.idCourseRV);

        // created new array list..
        recyclerDataArrayList=new ArrayList<>();

        // added data to array list
        recyclerDataArrayList.add(new VideoRVModel("DSA",R.mipmap.ic_launcher));
        recyclerDataArrayList.add(new VideoRVModel("JAVA",R.mipmap.ic_launcher));
        recyclerDataArrayList.add(new VideoRVModel("C++",R.mipmap.ic_launcher));
        recyclerDataArrayList.add(new VideoRVModel("Python",R.mipmap.ic_launcher));
        recyclerDataArrayList.add(new VideoRVModel("Node Js",R.mipmap.ic_launcher));

        // added data from arraylist to adapter class.
        RecyclerViewAdapter adapter=new RecyclerViewAdapter(recyclerDataArrayList, rootView.getContext());

        // setting grid layout manager to implement grid view.
        // in this method '2' represents number of columns to be displayed in grid view.
        GridLayoutManager layoutManager=new GridLayoutManager(rootView.getContext(), 2);

        // at last set adapter to recycler view.
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return rootView;
    }
}