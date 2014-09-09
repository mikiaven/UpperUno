package com.upper.upperuno;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;

public class UpActivity extends Activity {

    private Camera camera;
    private Parameters parameters;
    private Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up);

        //Encender el flash

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();

        //Apagar la linterna

    }

}
