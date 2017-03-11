package net.fumapp_android.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rodrigo Lopes Martins on 15/01/17.
 */

public class Schedule {

    @SerializedName("horario_inicio")
    @Expose
    private String startAt;

    @SerializedName("horario_termino")
    @Expose
    private String endAt;

    @SerializedName("disciplina")
    @Expose
    private String discipline;

    @SerializedName("classe")
    @Expose
    private String classNumber;

    @SerializedName("sala")
    @Expose
    private String sala;

    private String diaSemana;

    public String getStartAt() {
        return startAt;
    }

    public String getEndAt() {
        return endAt;
    }

    public String getDiscipline() {
        return discipline;
    }

    public String getSala() {
        return sala;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }
}
