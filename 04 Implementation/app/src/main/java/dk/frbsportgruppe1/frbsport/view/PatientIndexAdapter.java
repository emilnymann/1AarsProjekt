package dk.frbsportgruppe1.frbsport.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;
import java.util.concurrent.BlockingDeque;

import dk.frbsportgruppe1.frbsport.R;
import dk.frbsportgruppe1.frbsport.model.Patient;

public class PatientIndexAdapter extends RecyclerView.Adapter<PatientIndexAdapter.ViewHolder> {
    private List<Patient> patients;
    private FirebaseAuth auth;
    private final String TAG = "PatientIndexAdapter";

    private Context context;
    private int position;
    private ItemClickListener mItemClickListener;


    public PatientIndexAdapter(Context context, List<Patient> patients) {
        this.context = context;
        this.patients = patients;
        auth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Patient patient = patients.get(position);
        holder.patientNameTextView.setText(patient.getName());
        holder.patientLastMessageTextView.setText("Jeg tester lige hvad der foregår her");
        holder.showMessageIndexButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return patients.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView patientNameTextView;
        TextView patientLastMessageTextView;
        Button showMessageIndexButton;
        ConstraintLayout patientConstraintLayout;
        MaterialCardView materialCardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.patientNameTextView = itemView.findViewById(R.id.patientNameTextView);
            this.patientLastMessageTextView = itemView.findViewById(R.id.patientLastMessageTextView);
            this.patientConstraintLayout = itemView.findViewById(R.id.patientConstraintLayout);
            this.materialCardView = itemView.findViewById(R.id.patientCardView);
            this.showMessageIndexButton = itemView.findViewById(R.id.toMessageIndexButton);
        }

    }
    public void addItemClickListener(ItemClickListener listener) {
        mItemClickListener = listener;
    }

    public interface ItemClickListener {
        void onItemClick(int position);
    }

    public List<Patient> getPatients() {
        return patients;
    }
}
