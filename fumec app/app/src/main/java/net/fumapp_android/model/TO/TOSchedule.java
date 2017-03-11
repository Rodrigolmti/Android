package net.fumapp_android.model.TO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import net.fumapp_android.model.Calendar;

import java.util.List;

/**
 * Created by Rodrigo Lopes Martins on 15/01/17.
 */

public class TOSchedule {

    @SerializedName("auth")
    @Expose
    private Boolean auth;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("calendario_semanal")
    @Expose
    private List<Calendar> calendarioSemanal = null;

    public Boolean getAuth() {
        return auth;
    }

    public void setAuth(Boolean auth) {
        this.auth = auth;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Calendar> getCalendarioSemanal() {
        return calendarioSemanal;
    }

    public void setCalendarioSemanal(List<Calendar> calendarioSemanal) {
        this.calendarioSemanal = calendarioSemanal;
    }
}
