package net.fumapp_android.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.fumapp_android.R;
import net.fumapp_android.model.Schedule;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rodrigo Lopes Martins on 15/01/17.
 */

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.DataObjectHolder> {

    private List<Schedule> schedules = new ArrayList<>();

    public ScheduleAdapter(List<Schedule> schedules) {

        this.schedules = schedules;
    }

    @Override
    public ScheduleAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_list_item, parent, false);
        return new ScheduleAdapter.DataObjectHolder(view);
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, int position) {

        holder.setIsRecyclable(false);
        Schedule schedule = schedules.get(position);

        if (schedule.getDiaSemana() != null) {
            holder.textViewDayOfWeek.setVisibility(View.VISIBLE);
            holder.textViewDayOfWeek.setText(schedule.getDiaSemana());
        }

        holder.textViewDiscipline.setText(schedule.getDiscipline());
        holder.textViewClass.setText(schedule.getSala());
        holder.textViewStart.setText(schedule.getStartAt());
        holder.textViewEnd.setText(schedule.getEndAt());
    }

    @Override
    public int getItemCount() {

        return schedules.size();
    }

    class DataObjectHolder extends RecyclerView.ViewHolder {

        final TextView textViewDiscipline, textViewClass, textViewStart, textViewEnd, textViewDayOfWeek;

        DataObjectHolder(final View itemView) {

            super(itemView);
            textViewDayOfWeek = (TextView) itemView.findViewById(R.id.text_view_week_day);
            textViewDiscipline = (TextView) itemView.findViewById(R.id.text_view_discipline);
            textViewClass = (TextView) itemView.findViewById(R.id.text_view_class);
            textViewStart = (TextView) itemView.findViewById(R.id.text_view_start);
            textViewEnd = (TextView) itemView.findViewById(R.id.text_view_end);
        }
    }
}
