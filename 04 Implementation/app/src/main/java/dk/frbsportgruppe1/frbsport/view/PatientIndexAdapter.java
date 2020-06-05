package dk.frbsportgruppe1.frbsport.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import dk.frbsportgruppe1.frbsport.R;
import dk.frbsportgruppe1.frbsport.model.Patient;

public class PatientIndexAdapter extends RecyclerView.Adapter<PatientIndexAdapter.ViewHolder> {
    private List<Patient> patients;
    private FirebaseAuth auth;
    private OnItemClickListener mOnItemClickListener;
    private final String TAG = "PatientIndexAdapter";

    public PatientIndexAdapter(List<Patient> patients, OnItemClickListener onItemClickListener) {
        this.patients = patients;
        this.mOnItemClickListener = onItemClickListener;
        auth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_layout, parent, false);
        return new ViewHolder(view, mOnItemClickListener);
    }

    //TODO
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Patient patient = patients.get(position);

        holder.patientNameTextView.setText(patient.getName());
        holder.patientLastMessageTextView.setText("Jeg tester lige hvad der foregår her");

    }

    @Override
    public int getItemCount() {
        return patients.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView patientNameTextView;
        TextView patientLastMessageTextView;
        ConstraintLayout patientConstraintLayout;
        MaterialCardView materialCardView;

        OnItemClickListener onItemClickListener;

        public ViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            this.patientNameTextView = itemView.findViewById(R.id.patientNameTextView);
            this.patientLastMessageTextView = itemView.findViewById(R.id.patientLastMessageTextView);
            this.patientConstraintLayout = itemView.findViewById(R.id.patientConstraintLayout);
            this.materialCardView = itemView.findViewById(R.id.patientCardView);
            this.onItemClickListener = onItemClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}
