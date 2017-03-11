package net.fumapp_android.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.fumapp_android.BuildConfig;
import net.fumapp_android.R;
import net.fumapp_android.app.Preferences;
import net.fumapp_android.model.Student;
import net.fumapp_android.ui.fragments.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rodrigo Lopes Martins on 15/01/17.
 */

public class InfoFragment  extends BaseFragment {

    @BindView(R.id.text_view_name)
    TextView textViewName;

    @BindView(R.id.text_view_registry)
    TextView textViewRegistry;

    @BindView(R.id.text_view_status)
    TextView textViewStatus;

    @BindView(R.id.text_view_version)
    TextView textViewVersion;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View fragmentView = inflater.inflate(R.layout.info_fragment, container, false);
        ButterKnife.bind(this, fragmentView);

        setTitle(R.string.fragment_info_title);

        Student student = Preferences.getInstance().getCurrentStudent();
        if (student != null) {
            textViewName.setText(student.getName());
            textViewRegistry.setText(student.getAcademicRegistry());
            textViewStatus.setText("Ativo");
        }

        textViewVersion.setText(BuildConfig.VERSION_NAME);

        return fragmentView;
    }
}
