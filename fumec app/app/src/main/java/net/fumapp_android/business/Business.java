package net.fumapp_android.business;

import android.view.View;

import com.google.gson.Gson;

import net.fumapp_android.R;
import net.fumapp_android.app.Preferences;
import net.fumapp_android.model.Calendar;
import net.fumapp_android.model.Schedule;
import net.fumapp_android.model.Student;
import net.fumapp_android.model.TO.TONote;
import net.fumapp_android.model.TO.TOSchedule;
import net.fumapp_android.model.TO.TOStudent;
import net.fumapp_android.presenter.BasePresenter;
import net.fumapp_android.presenter.Presenter;
import net.fumapp_android.service.RetrofitService;
import net.fumapp_android.service.SinAppService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rodrigo Lopes Martins on 11/02/17.
 */

public class Business implements BasePresenter.business {

    private Presenter presenter;

    public Business(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void attempt(final Student student) {

        presenter.showProgressBar(View.VISIBLE);
        SinAppService service = RetrofitService.getInstance().getRetrofit().create(SinAppService.class);
        Call<TOStudent> call = service.attempt(student.getAcademicRegistry(), student.getPassword());
        call.enqueue(new Callback<TOStudent>() {
            @Override
            public void onResponse(Call<TOStudent> call, Response<TOStudent> response) {

                if (response.isSuccessful()) {
                    TOStudent toStudent = response.body();
                    toStudent.getStudent().setPassword(student.getPassword());
                    if (toStudent.getAuth()) {
                        Preferences.getInstance().setUserAuthenticated(toStudent.getStudent());
                    }
                    presenter.attemptSuccess();
                    presenter.showProgressBar(View.GONE);
                } else {

                    presenter.showProgressBar(View.GONE);
                    if (response.code() == 403) {
                        presenter.showToast(presenter.getContext().getResources().getString(R.string.general_authentication_message));
                    } else {
                        presenter.showToast(presenter.getContext().getResources().getString(R.string.general_error_message));
                    }
                }
            }

            @Override
            public void onFailure(Call<TOStudent> call, Throwable t) {

                presenter.showProgressBar(View.GONE);
                presenter.showToast(presenter.getContext().getResources().getString(R.string.general_error_message));
            }
        });
    }

    @Override
    public void points() {

        presenter.showProgressBar(View.VISIBLE);
        Student student = Preferences.getInstance().getCurrentStudent();
        SinAppService service = RetrofitService.getInstance().getRetrofit().create(SinAppService.class);
        Call<TONote> call = service.points(student.getAcademicRegistry(), student.getPassword());
        call.enqueue(new Callback<TONote>() {
            @Override
            public void onResponse(Call<TONote> call, retrofit2.Response<TONote> response) {

                Gson gson = new Gson();
                String stringGson = gson.toJson(response.body());
                Preferences.getInstance().setClassResponse(stringGson);
                presenter.attemptSuccess();
                presenter.showProgressBar(View.GONE);
            }

            @Override
            public void onFailure(Call<TONote> call, Throwable t) {

                presenter.showProgressBar(View.GONE);
                presenter.showToast(presenter.getContext().getResources().getString(R.string.general_error_message));
            }
        });
    }

    @Override
    public void schedule() {

        presenter.showProgressBar(View.VISIBLE);
        Student student = Preferences.getInstance().getCurrentStudent();
        SinAppService service = RetrofitService.getInstance().getRetrofit().create(SinAppService.class);
        Call<TOSchedule> call = service.schedule(student.getAcademicRegistry(), student.getPassword());
        call.enqueue(new Callback<TOSchedule>() {

            @Override
            public void onResponse(Call<TOSchedule> call, Response<TOSchedule> response) {

                Gson gson = new Gson();
                String stringGson = gson.toJson(buildFromResponse(response.body().getCalendarioSemanal()));
                Preferences.getInstance().setScheduleResponse(stringGson);
                presenter.attemptSuccess();
                presenter.showProgressBar(View.GONE);
            }

            @Override
            public void onFailure(Call<TOSchedule> call, Throwable t) {

                presenter.showProgressBar(View.GONE);
                presenter.showToast(presenter.getContext().getResources().getString(R.string.general_error_message));
            }
        });
    }

    @Override
    public void logout() {

        presenter.showProgressBar(View.VISIBLE);
        SinAppService service = RetrofitService.getInstance().getRetrofit().create(SinAppService.class);
        Call<TOStudent> call = service.logout();
        call.enqueue(new Callback<TOStudent>() {
            @Override
            public void onResponse(Call<TOStudent> call, Response<TOStudent> response) {

                Preferences.getInstance().clear();
                presenter.showProgressBar(View.GONE);
                presenter.attemptSuccess();
            }

            @Override
            public void onFailure(Call<TOStudent> call, Throwable t) {

                presenter.showProgressBar(View.GONE);
                presenter.showToast(presenter.getContext().getResources().getString(R.string.general_error_message));
            }
        });
    }

    private List<Schedule> buildFromResponse(List<Calendar> calendars) {

        List<Schedule> schedules = new ArrayList<>();

        int aux = 0;

        for (Calendar calendar : calendars) {

            for (Schedule schedule : calendar.getSchedules()) {
                if (calendar.getSchedules().size() != 0) {
                    if (aux == 0) {
                        schedule.setDiaSemana(calendar.getDayOfWeek());
                    }
                    schedules.add(schedule);
                    aux ++;
                }
            }
            aux = 0;
        }

        return schedules;
    }
}
