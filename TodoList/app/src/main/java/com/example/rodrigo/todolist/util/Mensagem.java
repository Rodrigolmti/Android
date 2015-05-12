package com.example.rodrigo.todolist.util;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by rodrigo on 03/05/15.
 */
public class Mensagem {

    public static void Msg(Activity activity, String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }
}
