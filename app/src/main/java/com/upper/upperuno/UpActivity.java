package com.upper.upperuno;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.IOException;
import java.util.List;

public class UpActivity extends Activity implements SurfaceHolder.Callback{

    private Lumus lumus;

    private Context contexto;
    private ToggleButton btnEncender;

    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;

    private PowerManager.WakeLock wakeLock;
    private static final String WAKE_LOCK_TAG = "Lumus";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up);

        contexto = UpActivity.this;

        //Encender el flash
        lumus = new Lumus();
        lumus.Activar();

        //Evento del Boton
        btnEncender = (ToggleButton) findViewById(R.id.toggleButton);
        btnEncender.setChecked(true);
        btnEncender.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!btnEncender.isChecked()){
                    lumus.Desactivar();
                }else
                {
                    lumus.Activar();
                }
            }
        });

        // Adquirir el wake lock.
        PowerManager powerManager =
                (PowerManager) getSystemService(POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(
                PowerManager.PARTIAL_WAKE_LOCK, WAKE_LOCK_TAG);
        wakeLock.setReferenceCounted(false);
        if (!wakeLock.isHeld())
        {
            wakeLock.acquire();
        }

        surfaceView = (SurfaceView) this.findViewById(R.id.hiddenSurfaceView);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        surfaceHolder.addCallback(this);
    }


    @Override
    public void onBackPressed()
    {
        super.onBackPressed();

        //Apagar la linterna
        lumus.Desactivar();
        lumus.Soltar();
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        try {
            lumus.getCamara().setPreviewDisplay(surfaceHolder);
        } catch (IOException e) {
            //Log.e(TAG, "Unexpected IO Exception in setPreviewDisplay()", e);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
