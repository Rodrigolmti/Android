package net.fumapp_android.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.fumapp_android.model.Schedule;
import net.fumapp_android.model.Student;
import net.fumapp_android.model.TO.TONote;
import net.fumapp_android.model.TO.TOSchedule;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rodrigo Lopes Martins on 15/01/17.
 */

public class Preferences {

    private static final String SHARED_PREFERENCES = "shared_preferences";
    private final String CLASS_RESPONSE = "class_response";
    private final String SCHEDULE_RESPONSE = "schedule_response";
    private final String USER_REGISTRY = "user_registry";
    private final String USER_NAME = "user_name";
    private final String USER_STATUS = "user_status";
    private final String USER_PASSWORD = "user_password";

    private static Preferences instance;
    private SharedPreferences sharedPreferences;

    public static Preferences getInstance() {

        if (instance == null) {
            instance = new Preferences();
            instance.sharedPreferences = SinAppApplication.getInstance().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        }
        return instance;
    }

    public void setClassResponse(String response) {

        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(CLASS_RESPONSE, response);
        editor.apply();
    }

    public TONote getClassResponse() {

        TONote toNote = null;

        try {
            Gson gson = new Gson();
            String stringGson = sharedPreferences.getString(CLASS_RESPONSE, null);
            toNote = gson.fromJson(stringGson, TONote.class);
        } catch (Exception error) {
            Log.e("SinApp", "Cast response class error!");
        }

        return toNote;
    }

    public void setScheduleResponse(String response) {

        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SCHEDULE_RESPONSE, response);
        editor.apply();
    }

    public List<Schedule> getScheduleResponse() {

        List<Schedule> schedules = new ArrayList<>();

        try {
            Gson gson = new Gson();
            String stringGson = sharedPreferences.getString(SCHEDULE_RESPONSE, null);
            schedules = gson.fromJson(stringGson, new TypeToken<List<Schedule>>(){}.getType());
        } catch (Exception error) {
            Log.e("SinApp", "Cast response schedule error!");
        }

        return schedules;
    }

    public void setUserAuthenticated(final Student student) {

        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_REGISTRY, student.getAcademicRegistry());
        editor.putString(USER_NAME, student.getName());
        editor.putBoolean(USER_STATUS, student.getActive());
        editor.putString(USER_PASSWORD, student.getPassword());
        editor.apply();
    }

    public Student getCurrentStudent() {

        final Student student = new Student();
        student.setAcademicRegistry(sharedPreferences.getString(USER_REGISTRY, null));
        student.setActive(sharedPreferences.getBoolean(USER_STATUS, false));
        student.setName(sharedPreferences.getString(USER_NAME, null));
        student.setPassword(sharedPreferences.getString(USER_PASSWORD, null));
        if (student.getAcademicRegistry() == null)
            return null;
        return student;
    }

    public void clear() {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
