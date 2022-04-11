package wit.edu.moblieapp.afinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(VideoRVModel item);
    }

    private ArrayList<VideoRVModel> courseDataArrayList;
    private Context mcontext;
    private OnItemClickListener listener;

    public RecyclerViewAdapter(ArrayList<VideoRVModel> recyclerDataArrayList, Context mcontext, OnItemClickListener listener) {
        this.courseDataArrayList = recyclerDataArrayList;
        this.mcontext = mcontext;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_rv_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview and imageview.
        VideoRVModel recyclerData = courseDataArrayList.get(position);
        holder.itemName.setText(recyclerData.getTitle());
        holder.thumbnail.setImageResource(recyclerData.getImgid());
        holder.bind(recyclerData, listener);
    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return courseDataArrayList.size();
    }

    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView itemName;
        private ImageView thumbnail;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            thumbnail = itemView.findViewById(R.id.thumbnail);
        }
        public void bind(final VideoRVModel item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}

