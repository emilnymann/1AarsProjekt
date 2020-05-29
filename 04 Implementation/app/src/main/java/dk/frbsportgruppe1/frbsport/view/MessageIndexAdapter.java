package dk.frbsportgruppe1.frbsport.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dk.frbsportgruppe1.frbsport.R;
import dk.frbsportgruppe1.frbsport.model.Message;
import dk.frbsportgruppe1.frbsport.model.User;

public class MessageIndexAdapter extends RecyclerView.Adapter<MessageIndexAdapter.ViewHolder> {

    private static final String TAG = "MessageIndexAdapter";

    private List<Message> messages;
    private User loggedInUser;

    public MessageIndexAdapter(List<Message> messages) {
        this.messages = messages;
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

        // hvis afsenderen af beskeden ikke er den bruger der er logget ind, sæt afsender navnet til det fulde navn i stedet for "dig".
        if ("TestUsername" != message.getSender().getUsername()) {
            sender = message.getSender().getName();

            // TODO: ændre cardview appearance når beskeden ikke er brugerens ejen.

            

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

        holder.textViewMessageInfo.setText(messageInfo);
        holder.textViewMessageText.setText(message.getText());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewMessageInfo;
        TextView textViewMessageText;
        ConstraintLayout constraintLayout;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.textViewMessageInfo = itemView.findViewById(R.id.messageInfo);
            this.textViewMessageText = itemView.findViewById(R.id.messageText);
            this.constraintLayout = itemView.findViewById(R.id.messageConstraintLayout);
            this.cardView = itemView.findViewById(R.id.messageCard);

        }
    }
}
