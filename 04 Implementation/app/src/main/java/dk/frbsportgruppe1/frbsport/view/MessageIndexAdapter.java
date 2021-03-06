package dk.frbsportgruppe1.frbsport.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import dk.frbsportgruppe1.frbsport.R;
import dk.frbsportgruppe1.frbsport.model.Message;
import dk.frbsportgruppe1.frbsport.model.MessageImpl;

public class MessageIndexAdapter extends RecyclerView.Adapter<MessageIndexAdapter.ViewHolder> {

    private static final String TAG = "MessageIndexAdapter";

    private List<Message> messages;
    private FirebaseAuth auth;

    public MessageIndexAdapter(List<Message> messages) {
        this.messages = messages;
        auth = FirebaseAuth.getInstance();
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Message message = messages.get(position);

        String sender = "Dig";
        FirebaseUser loggedInUser = auth.getCurrentUser();

        if (loggedInUser != null) {
            // hvis afsenderen af beskeden ikke er den bruger der er logget ind, sæt afsender navnet til det fulde navn i stedet for "dig".
            if (!loggedInUser.getUid().equals(message.getSender().getId())) {
                sender = message.getSender().getName();
                holder.cardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.colorAccentLight));

                ConstraintSet set = new ConstraintSet();
                set.clone(holder.constraintLayout);
                set.connect(holder.textViewMessageInfo.getId(), ConstraintSet.LEFT, holder.constraintLayout.getId(), ConstraintSet.LEFT);
                set.connect(holder.cardView.getId(), ConstraintSet.LEFT, holder.constraintLayout.getId(), ConstraintSet.LEFT);
                set.applyTo(holder.constraintLayout);
            }
            else {
                ConstraintSet set = new ConstraintSet();
                set.clone(holder.constraintLayout);
                set.connect(holder.textViewMessageInfo.getId(), ConstraintSet.RIGHT, holder.constraintLayout.getId(), ConstraintSet.RIGHT);
                set.connect(holder.cardView.getId(), ConstraintSet.RIGHT, holder.constraintLayout.getId(), ConstraintSet.RIGHT);
                set.applyTo(holder.constraintLayout);
            }
        }

        // byg resten af message info teksten.
        String messageInfo = sender + ", d. " +
                message.getDateTime().getDayOfMonth() +
                "/" +
                message.getDateTime().getMonthValue() +
                " " +
                message.getDateTime().getYear() +
                " kl. " +
                message.getDateTime().getHour() +
                ":" + message.getDateTime().getMinute();

        if (!message.isSent()) {
            holder.textViewMessageInfo.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.design_default_color_error));
            holder.textViewMessageInfo.setText("Ikke afsendt...");
        } else {
            holder.textViewMessageInfo.setText(messageInfo);
        }

        holder.textViewMessageText.setText(message.getText());

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewMessageInfo;
        TextView textViewMessageText;
        ConstraintLayout constraintLayout;
        CardView cardView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.textViewMessageInfo = itemView.findViewById(R.id.messageInfo);
            this.textViewMessageText = itemView.findViewById(R.id.messageText);
            this.constraintLayout = itemView.findViewById(R.id.messageConstraintLayout);
            this.cardView = itemView.findViewById(R.id.messageCard);

        }
    }
}
