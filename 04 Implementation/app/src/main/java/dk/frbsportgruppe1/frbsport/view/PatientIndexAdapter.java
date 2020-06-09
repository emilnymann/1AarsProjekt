package dk.frbsportgruppe1.frbsport.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import dk.frbsportgruppe1.frbsport.R;
import dk.frbsportgruppe1.frbsport.model.Patient;

public class PatientIndexAdapter extends RecyclerView.Adapter<PatientIndexAdapter.ViewHolder> {
    private List<Patient> patients;
    private FirebaseAuth auth;
    private final String TAG = "PatientIndexAdapter";

    private Context context;
    private int position;
    private ItemClickListener mItemClickListener;


    /**
     * Constructoren til PatientIndexAdapter
     * @param context her referes der til den state programmet er i.
     * @param patients bare en liste af patienter.
     */
    public PatientIndexAdapter(Context context, List<Patient> patients) {
        this.context = context;
        this.patients = patients;
        auth = FirebaseAuth.getInstance();
    }


    /**
     * Denne bliver kaldt når adapteren inflates i PatientIndexFragmentet/recyclerviewet.
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    /**
     * Det er her man kan håndtere hvilken data der skal fyldes i hvert view.
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Patient patient = patients.get(position);
        holder.patientNameTextView.setText(patient.getName());
        holder.materialCardView.setOnClickListener(new View.OnClickListener() {
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

    /**
     * Denne indre klasse sørger for at hvert view er instantieret.
     */
    class ViewHolder extends RecyclerView.ViewHolder{

        TextView patientNameTextView;
        ConstraintLayout patientConstraintLayout;
        MaterialCardView materialCardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.patientNameTextView = itemView.findViewById(R.id.patientNameTextView);
            this.patientConstraintLayout = itemView.findViewById(R.id.patientConstraintLayout);
            this.materialCardView = itemView.findViewById(R.id.patientCardView);
        }

    }

    public void addItemClickListener(ItemClickListener listener) {
        mItemClickListener = listener;
    }

    /**
     * et lokalt interface der bruges for at håndtere klik på knappen showMessageIndexButton i andre klasser
     */
    public interface ItemClickListener {
        void onItemClick(int position);
    }

    public List<Patient> getPatients() {
        return patients;
    }
}
