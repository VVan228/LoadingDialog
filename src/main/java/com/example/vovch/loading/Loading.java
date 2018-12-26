package com.example.vovch.loading;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import fr.tvbarthel.lib.blurdialogfragment.SupportBlurDialogFragment;

public class Loading extends SupportBlurDialogFragment {
    LoadingAnim ld;
    View v;
    boolean flag = false;
    public Loading(){
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //проверка на null чтобы не появлялось много окон
        if(v==null)
            v = inflater.inflate(R.layout.dialog, null);
        if(ld==null)
            ld = v.findViewById(R.id.load);
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
    @Override
    protected int getBlurRadius() {
        return 10;
    }

    @Override
    protected float getDownScaleFactor() {
        return 7;
    }

    @Override
    protected boolean isRenderScriptEnable() {
        return true;
    }
}
