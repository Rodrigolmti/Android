package net.fumapp_android.presenter;

import android.content.Context;

import net.fumapp_android.business.Business;
import net.fumapp_android.model.Student;

/**
 * Created by Rodrigo Lopes Martins on 11/02/17.
 */

public class Presenter implements BasePresenter.presenter {

    private Business busniess;
    private BasePresenter.view view;

    public Presenter() {
        busniess = new Business(this);
    }

    @Override
    public void setView(BasePresenter.view view) {
        this.view = view;
    }

    @Override
    public Context getContext() {
        return (Context) view;
    }

    @Override
    public void logout() {
        busniess.logout();
    }

    @Override
    public void attempt(Student student) {
        busniess.attempt(student);
    }

    @Override
    public void attemptSuccess() {
        view.successAttempt();
    }

    @Override
    public void points() {
        busniess.points();
    }

    @Override
    public void schedule() {
        busniess.schedule();
    }

    @Override
    public void showProgressBar(int visibility) {
        view.showProgressBar(visibility);
    }

    @Override
    public void showToast(String message) {
        view.showToast(message);
    }
}
