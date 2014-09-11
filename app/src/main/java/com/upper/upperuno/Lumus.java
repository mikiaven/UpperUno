package com.upper.upperuno;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;

/**
 * Created by Miguel on 10/09/2014.
 */
public class Lumus {

    private Camera camara;
    private Parameters parametros;
    private boolean Activo;

    public Lumus()
    {
        camara = Camera.open();
        parametros = camara.getParameters();
        camara.startPreview();
        Activo = false;
    }

    public void Activar()
    {
        if(!Activo){
            parametros.setFlashMode(Parameters.FLASH_MODE_TORCH);
            camara.setParameters(parametros);
            Activo = true;
        }
    }

    public void Desactivar()
    {
        if(Activo)
        {
            parametros.setFlashMode(Parameters.FLASH_MODE_OFF);
            camara.setParameters(parametros);
            Activo = false;
        }
    }

    public void Soltar(){
        camara.stopPreview();
        camara.release();
    }

    public boolean estaActivo() {
        return  Activo;
    }

    public Camera getCamara(){
        return  camara;
    }
}
