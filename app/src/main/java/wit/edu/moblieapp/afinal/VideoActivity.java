package wit.edu.moblieapp.afinal;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import android.os.Bundle;

public class VideoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<VideoRVModel> recyclerDataArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        recyclerView=findViewById(R.id.idCourseRV);

        // created new array list..
        recyclerDataArrayList=new ArrayList<>();

        // added data to array list
        recyclerDataArrayList.add(new VideoRVModel("DSA",R.drawable.ic_gfglogo));
        recyclerDataArrayList.add(new VideoRVModel("JAVA",R.drawable.ic_gfglogo));
        recyclerDataArrayList.add(new VideoRVModel("C++",R.drawable.ic_gfglogo));
        recyclerDataArrayList.add(new VideoRVModel("Python",R.drawable.ic_gfglogo));
        recyclerDataArrayList.add(new VideoRVModel("Node Js",R.drawable.ic_gfglogo));

        // added data from arraylist to adapter class.
        RecyclerViewAdapter adapter=new RecyclerViewAdapter(recyclerDataArrayList,this);

        // setting grid layout manager to implement grid view.
        // in this method '2' represents number of columns to be displayed in grid view.
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);

        // at last set adapter to recycler view.
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }
}