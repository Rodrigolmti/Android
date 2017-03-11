package net.fumapp_android.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rodrigo Lopes Martins on 15/01/17.
 */

public class Calendar {

    @SerializedName("dia_semana")
    @Expose
    private String dayOfWeek;

    @SerializedName("horarios")
    @Expose
    public final List<Schedule> schedules = new ArrayList<>();

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }
}
