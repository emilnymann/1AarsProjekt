package dk.frbsportgruppe1.frbsport.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import dk.frbsportgruppe1.frbsport.R;

public class SetUnavailableHoursAdapter extends RecyclerView.Adapter<SetUnavailableHoursAdapter.ViewHolder> {

    // TODO skal tage imod BookingExceptionRange
    public SetUnavailableHoursAdapter() {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tider_layout, parent);
        return new SetUnavailableHoursAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.dagTextView.setText("MANDAG I DAG");
        holder.tidspunktTextView.setText("JA HELE DAGEN");
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView dagTextView;
        TextView tidspunktTextView;
        MaterialCardView angivLedigeTiderCardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.dagTextView = itemView.findViewById(R.id.dagTextView);
            this.tidspunktTextView = itemView.findViewById(R.id.tidspunktTextView);
            this.angivLedigeTiderCardView = itemView.findViewById(R.id.angiveLedigeTiderCardView);
        }
    }
}
