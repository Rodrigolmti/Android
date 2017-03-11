package net.fumapp_android.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.fumapp_android.R;
import net.fumapp_android.model.Note;

/**
 * Created by Rodrigo Lopes Martins on 15/01/17.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.DataObjectHolder> {

    private final Note notes;
    private final Context context;

    public NoteAdapter(Context context, Note notes) {

        this.notes = notes;
        this.context = context;
    }

    @Override
    public NoteAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_list_item, parent, false);
        return new NoteAdapter.DataObjectHolder(view);
    }

    @Override
    public void onBindViewHolder(final NoteAdapter.DataObjectHolder holder, int position) {

        String message = "";

        switch (position) {

            case 0:
                message = context.getResources().getString(R.string.fragment_note_instructional);
                if (checkNote(holder, notes.getSelfInstructional())) {
                    holder.textViewNote.setText(String.valueOf(notes.getSelfInstructional()));
                }
                break;
            case 1:
                message = context.getResources().getString(R.string.fragment_note_content);
                if (checkNote(holder, notes.getReviewOfContent())) {
                    holder.textViewNote.setText(String.valueOf(notes.getReviewOfContent()));
                }
                break;
            case 2:
                message = context.getResources().getString(R.string.fragment_note_practice);
                if (checkNote(holder, notes.getAval1())) {
                    holder.textViewNote.setText(String.valueOf(notes.getAval2()));
                }
                break;
            case 3:
                message = context.getResources().getString(R.string.fragment_note_firts_evaluation);
                if (checkNote(holder, notes.getAval2())) {
                    holder.textViewNote.setText(String.valueOf(notes.getAval1()));
                }
                break;
            case 4:
                message = context.getResources().getString(R.string.fragment_note_second_evaluation);
                if (checkNote(holder, notes.getAval3())) {
                    holder.textViewNote.setText(String.valueOf(notes.getAval3()));
                }
                break;
            case 5:
                message = context.getResources().getString(R.string.fragment_note_special_evaluation);
                if (checkNote(holder, notes.getSpecialExam())) {
                    holder.textViewNote.setText(String.valueOf(notes.getSpecialExam()));
                }
                break;
            case 6:
                message = context.getResources().getString(R.string.fragment_note_total);
                holder.textViewNote.setText(String.valueOf(notes.getTotal()));
        }

        holder.textViewClass.setText(message);
    }

    @Override
    public int getItemCount() {

        return 7;
    }

    private boolean checkNote(final NoteAdapter.DataObjectHolder holder, Object obj) {
        if (obj == null) {
            holder.textViewNote.setText("0");
            holder.textViewInfo.setVisibility(View.VISIBLE);
            return false;
        }
        return true;
    }

    class DataObjectHolder extends RecyclerView.ViewHolder {

        final TextView textViewNote, textViewClass, textViewInfo;

        DataObjectHolder(final View itemView) {

            super(itemView);
            textViewNote = (TextView) itemView.findViewById(R.id.text_view_note);
            textViewClass = (TextView) itemView.findViewById(R.id.text_view_class);
            textViewInfo = (TextView) itemView.findViewById(R.id.text_view_static_info);
        }
    }
}
