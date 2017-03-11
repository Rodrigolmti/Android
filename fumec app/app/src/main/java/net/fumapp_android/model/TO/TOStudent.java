package net.fumapp_android.model.TO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import net.fumapp_android.model.Student;

/**
 * Created by Rodrigo Lopes Martins on 15/01/17.
 */

public class TOStudent {

    @Expose
    private Boolean auth;

    @SerializedName("aluno")
    @Expose
    private Student student;

    @SerializedName("mensagem")
    @Expose
    private String message;

    public Boolean getAuth() {
        return auth;
    }

    public void setAuth(Boolean auth) {
        this.auth = auth;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
