package net.fumapp_android.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Rodrigo Lopes Martins on 15/01/17.
 */

public class Student {

    @SerializedName("nome")
    @Expose
    private String name;

    @SerializedName("registro_academico")
    @Expose
    private String academicRegistry;

    @SerializedName("senha")
    @Expose
    private String password;

    @SerializedName("ativo")
    @Expose
    private Boolean active;

    public Student() {}

    public Student(String academicRegistry, String password) {
        this.academicRegistry = academicRegistry;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcademicRegistry() {
        return academicRegistry;
    }

    public void setAcademicRegistry(String academicRegistry) {
        this.academicRegistry = academicRegistry;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
