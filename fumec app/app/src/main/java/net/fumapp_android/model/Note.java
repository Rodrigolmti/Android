package net.fumapp_android.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Rodrigo Lopes Martins on 15/01/17.
 */

public class Note implements Serializable {

    @SerializedName("disciplina")
    @Expose
    private String discipline;

    @SerializedName("carga_horaria")
    @Expose
    private String workload;

    @SerializedName("turma")
    @Expose
    private String classNumber;

    @SerializedName("faltas_total")
    @Expose
    private Integer totalFaults;

    @SerializedName("faltas_maxima")
    @Expose
    private Integer maximumFaults;

    @SerializedName("atividade_autoinstrucional")
    @Expose
    private Object selfInstructional;

    @SerializedName("revisao_conteudo")
    @Expose
    private Object reviewOfContent;

    @SerializedName("exame_especial")
    @Expose
    private Object specialExam;

    @SerializedName("aval_1")
    @Expose
    private Object aval1;

    @SerializedName("aval_2")
    @Expose
    private Object aval2;

    @SerializedName("aval_3")
    @Expose
    private Object aval3;

    @SerializedName("aval_4")
    @Expose
    private Object aval4;

    @SerializedName("aval_5")
    @Expose
    private Object aval5;

    @SerializedName("aval_6")
    @Expose
    private Object aval6;

    @SerializedName("aval_7")
    @Expose
    private Object aval7;

    @SerializedName("aval_8")
    @Expose
    private Object aval8;

    @SerializedName("aval_9")
    @Expose
    private Object aval9;

    @SerializedName("aval_10")
    @Expose
    private Object aval10;

    @SerializedName("total")
    @Expose
    private Integer total;

    public String getDisciplina() {
        return discipline;
    }

    public String getWorkload() {
        return workload;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public Integer getTotalFaults() {
        return totalFaults;
    }

    public Integer getMaximumFaults() {
        return maximumFaults;
    }

    public Object getSelfInstructional() {
        return selfInstructional;
    }

    public Object getReviewOfContent() {
        return reviewOfContent;
    }

    public Object getSpecialExam() {
        return specialExam;
    }

    public Object getAval1() {
        return aval1;
    }

    public Object getAval2() {
        return aval2;
    }

    public Object getAval3() {
        return aval3;
    }

    public Object getAval4() {
        return aval4;
    }

    public Object getAval5() {
        return aval5;
    }

    public Object getAval6() {
        return aval6;
    }

    public Object getAval7() {
        return aval7;
    }

    public Object getAval8() {
        return aval8;
    }

    public Object getAval9() {
        return aval9;
    }

    public Object getAval10() {
        return aval10;
    }

    public Integer getTotal() {
        return total;
    }

}
