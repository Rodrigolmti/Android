package net.fumapp_android.model.TO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import net.fumapp_android.model.Note;
import net.fumapp_android.model.Student;

import java.util.List;

/**
 * Created by Rodrigo Lopes Martins on 15/01/17.
 */

public class TONote {

    @Expose
    private Boolean auth;

    @SerializedName("aluno")
    @Expose
    private Student student;

    @SerializedName("notas")
    @Expose
    private List<Note> notes = null;

    public List<Note> getNotes() {
        return notes;
    }
}
