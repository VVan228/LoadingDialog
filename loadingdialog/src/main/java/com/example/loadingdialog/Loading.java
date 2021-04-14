package com.example.loadingdialog;


import androidx.fragment.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentManager;


public class Loading extends DialogFragment {
    int background = Color.rgb(255,255,255);
    int fragmentBackground = Color.rgb(0,0,0);;
    int foreground = Color.rgb(255,0,0);;
    float radius;
    String text = "Loading";

    LoadingAnim ld;
    View v;
    boolean flag = false;
    public Loading(int background, int fragmentBackground, int foreground, float radius){
        this.background = background;
        this.fragmentBackground = fragmentBackground;
        this.foreground = foreground;
        this.radius = radius;
    }
    public Loading(int background, int fragmentBackground, int foreground, float radius, String text){
        this.background = background;
        this.fragmentBackground = fragmentBackground;
        this.foreground = foreground;
        this.radius = radius;
        this.text = text;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //проверка на null чтобы не появлялось много окон
        if(v==null)
            v = inflater.inflate(R.layout.dialog, null);
        if(ld==null)
            ld = v.findViewById(R.id.load);

        //смена цветов
        GradientDrawable ldBack = new GradientDrawable();
        ldBack.setColor(background);
        ldBack.setCornerRadii(new float[]{radius, radius, radius, radius, 0, 0, 0, 0});

        GradientDrawable txtBack = new GradientDrawable();
        txtBack.setColor(foreground);
        txtBack.setCornerRadii(new float[]{0, 0, 0, 0, radius, radius, radius, radius});

        TextView txt = v.findViewById(R.id.txt);
        ld.setBackground(ldBack);
        ld.color = foreground;
        txt.setText(text);
        txt.setTextColor(background);
        txt.setBackground(txtBack);

        //прозрачность фона для закругленных углов
        try {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }catch (Exception e){ }
        if(!ld.flag) getDialog().dismiss();
        if(!flag) {
            flag = true;
            return v;
        }else
            return null;
    }

    //listener на отмену диалога
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(getDialog());
        ld.changeFlag();
        ld.stopMotion();
    }
    //listener на отмену диалога нажатием кнопки back
    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        ld.changeFlag();
        ld.stopMotion();
    }

}
