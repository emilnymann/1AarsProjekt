package dk.frbsportgruppe1.frbsport.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import dk.frbsportgruppe1.frbsport.R;
import dk.frbsportgruppe1.frbsport.model.BookingExceptionRange;

public class SetUnavailableHoursAdapter extends RecyclerView.Adapter<SetUnavailableHoursAdapter.ViewHolder> {
    ArrayList<BookingExceptionRange> bookingExceptionRanges;

    // TODO skal tage imod BookingExceptionRange
    public SetUnavailableHoursAdapter(ArrayList<BookingExceptionRange> bookingExceptionRanges) {
        this.bookingExceptionRanges = bookingExceptionRanges;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tider_layout, parent, false);
        return new SetUnavailableHoursAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookingExceptionRange bookingExceptionRange = bookingExceptionRanges.get(position);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        String startDate = bookingExceptionRange.getStartDateTime().format(dateTimeFormatter);
        String endDate = bookingExceptionRange.getEndDateTime().format(dateTimeFormatter);
        holder.dagTextView.setText("Fra: " + startDate);
        holder.tidspunktTextView.setText("Til: " + endDate);
    }

    @Override
    public int getItemCount() {
        return bookingExceptionRanges.size();
    }

    public ArrayList<BookingExceptionRange> getBookingExceptionRanges() {
        return bookingExceptionRanges;
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
