package net.fumapp_android.presenter;

import android.content.Context;

import net.fumapp_android.model.Student;

/**
 * Created by Rodrigo Lopes Martins on 11/02/17.
 */

public interface BasePresenter {

    interface business {

        void attempt(Student student);
        void points();
        void schedule();
        void logout();
    }

    interface presenter {

        void attempt(Student student);
        void attemptSuccess();
        void points();
        void schedule();
        void showProgressBar(int visibility);
        void setView(BasePresenter.view view);
        Context getContext();
        void showToast(String message);
        void logout();
    }

    interface view {

        void showProgressBar(int visibility);
        void showToast(String message);
        void successAttempt();
    }
}
