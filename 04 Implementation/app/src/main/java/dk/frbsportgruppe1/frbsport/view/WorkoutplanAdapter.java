package dk.frbsportgruppe1.frbsport.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

import dk.frbsportgruppe1.frbsport.R;
import dk.frbsportgruppe1.frbsport.model.Workoutplan;
import dk.frbsportgruppe1.frbsport.model.WorkoutplanImpl;

public class WorkoutplanAdapter extends RecyclerView.Adapter<WorkoutplanAdapter.ViewHolder> {
   public List<Workoutplan> workoutplans;

    private final String TAG = "WorkoutplanAdapter";

    private Context context;


    public WorkoutplanAdapter (Context context, ArrayList<Workoutplan> workoutplans){
        Log.d(TAG, "WorkoutplanAdapter: " + workoutplans.size());
        this.context = context;
        this.workoutplans = workoutplans;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.workoutplan_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        Log.d(TAG, "onCreateViewHolder: ");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: " + workoutplans.size());
        Workoutplan workoutplan = workoutplans.get(position);
        holder.workoutTitle.setText(workoutplan.getTitle());
        Log.d(TAG, "onBindViewHolder: "+ workoutplan.getTitle());

    }


    @Override
    public int getItemCount() {
        return workoutplans.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{

        TextView workoutTitle;
        ConstraintLayout workoutplanLayout;
        CardView workoutCardview;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.workoutTitle = itemView.findViewById(R.id.calenderWorkoutTextView);
            this.workoutplanLayout = itemView.findViewById(R.id.workoutplanLayout);
            this.workoutCardview = itemView.findViewById(R.id.calenderWorkoutCardView);

        }

    }
}

