package dk.frbsportgruppe1.frbsport.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.apache.commons.lang3.StringUtils;

import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

import dk.frbsportgruppe1.frbsport.R;
import dk.frbsportgruppe1.frbsport.model.BookingRange;

public class SetAvailableHoursAdapter extends  RecyclerView.Adapter<SetAvailableHoursAdapter.ViewHolder>{
    private ArrayList<BookingRange> bookingRanges;

    public SetAvailableHoursAdapter(ArrayList<BookingRange> bookingRanges) {
        this.bookingRanges = bookingRanges;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tider_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookingRange bookingRange = bookingRanges.get(position);
        String dagTextViewString = bookingRange.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());
        holder.dagTextView.setText(StringUtils.capitalize(dagTextViewString));
        holder.tidspunktTextView.setText(bookingRange.getStartTime().toString() + " - " + bookingRange.getEndTime().toString());
    }

    @Override
    public int getItemCount() {
        return bookingRanges.size();
    }

    public ArrayList<BookingRange> getBookingRanges() {
        return bookingRanges;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView dagTextView;
        TextView tidspunktTextView;
        CardView angivLedigeTiderCardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.dagTextView = itemView.findViewById(R.id.dagTextView);
            this.tidspunktTextView = itemView.findViewById(R.id.tidspunktTextView);
            this.angivLedigeTiderCardView = itemView.findViewById(R.id.angiveLedigeTiderCardView);
        }
    }
}
