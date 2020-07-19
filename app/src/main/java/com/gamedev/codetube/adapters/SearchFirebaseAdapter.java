package com.gamedev.codetube.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.gamedev.codetube.R;
import com.gamedev.codetube.models.Course;
import com.gamedev.codetube.ui.MainActivity;
import com.google.firebase.firestore.DocumentSnapshot;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class SearchFirebaseAdapter extends FirestoreRecyclerAdapter<Course, SearchFirebaseAdapter.CourseHolder> {


    private OnItemClickListener listener;



    public SearchFirebaseAdapter(@NonNull FirestoreRecyclerOptions<Course> options) {
        super(options);

    }

    @Override
    protected void onBindViewHolder(@NonNull CourseHolder holder, int position, @NonNull Course model) {
        holder.title.setText(model.getTitle());
        Picasso.get().load(model.getCourse_thumbnail()).into(holder.thumbnail, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(holder.itemView.getContext(), "Didnt get the image", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @NonNull
    @Override
    public CourseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item_for_fragment_layout,
                parent, false);
        return new CourseHolder(v);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    class CourseHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView description;
        ImageView thumbnail;
        public CourseHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.search_fragment_title);
            //description = itemView.findViewById(R.id.text_view_description);
            thumbnail = itemView.findViewById(R.id.search_fragment_cover_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION && listener != null){
                        listener.onItemClick(getSnapshots().getSnapshot(position), position);
                    }
                }
            });


        }
    }


}
