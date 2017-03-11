package net.fumapp_android.ui.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.fumapp_android.R;
import net.fumapp_android.model.Note;
import net.fumapp_android.ui.interfaces.ClassAdapterItemOnClick;

import java.util.List;

/**
 * Created by Rodrigo Lopes Martins on 15/01/17.
 */

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.DataObjectHolder>{

    private final List<Note> notes;
    private final Context context;
    private final ClassAdapterItemOnClick itemOnClick;

    public ClassAdapter(List<Note> notes, Context context, ClassAdapterItemOnClick itemOnClick) {

        this.notes = notes;
        this.context = context;
        this.itemOnClick = itemOnClick;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_list_item, parent, false);
        return new DataObjectHolder(view);
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, int position) {

        final Note note = notes.get(position);
        holder.textViewClassName.setText(note.getDisciplina());
        holder.textViewHour.setText(note.getWorkload());
        holder.textViewTeam.setText(note.getClassNumber());
        holder.textViewFaults.setText(String.valueOf(note.getTotalFaults()));
        holder.textViewNote.setText(String.valueOf(note.getTotal()));
        final int maximumFaults = note.getMaximumFaults();
        final int faults = note.getTotalFaults();
        if (maximumFaults == 10) {
            if (faults <= (maximumFaults / 2)) {
                holder.textViewFaults.setTextColor(ContextCompat.getColor(context, R.color.nephritis));
            } else if (faults > (maximumFaults / 2) && faults  <maximumFaults){
                holder.textViewFaults.setTextColor(ContextCompat.getColor(context, R.color.carrot));
            } else {
                holder.textViewFaults.setTextColor(ContextCompat.getColor(context, R.color.alizarin));
            }
        } else {
            if (faults <= (maximumFaults / 2)) {
                holder.textViewFaults.setTextColor(ContextCompat.getColor(context, R.color.nephritis));
            } else if (faults > (maximumFaults / 2) && faults < maximumFaults) {
                holder.textViewFaults.setTextColor(ContextCompat.getColor(context, R.color.carrot));
            } else {
                holder.textViewFaults.setTextColor(ContextCompat.getColor(context, R.color.alizarin));
            }
        }
    }

    @Override
    public int getItemCount() {

        return notes.size();
    }

    class DataObjectHolder extends RecyclerView.ViewHolder {

        final TextView textViewClassName, textViewHour, textViewTeam, textViewFaults, textViewNote;

        DataObjectHolder(final View itemView) {

            super(itemView);
            textViewClassName = (TextView) itemView.findViewById(R.id.text_view_class_name);
            textViewHour = (TextView) itemView.findViewById(R.id.text_view_hour);
            textViewTeam = (TextView) itemView.findViewById(R.id.text_view_team);
            textViewFaults = (TextView) itemView.findViewById(R.id.text_view_faults);
            textViewNote = (TextView) itemView.findViewById(R.id.text_view_note);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    itemOnClick.onClick(notes.get(getAdapterPosition()));
                }
            });
        }
    }
}
