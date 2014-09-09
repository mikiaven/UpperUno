package com.upper.upperuno;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class UpActivity extends Activity {

    //private Camera camera;
    //private Parameters parameters;

    private Camera _camera;
    protected static final String MODE_TORCH = Camera.Parameters.FLASH_MODE_TORCH;
    protected static final String MODE_OFF = Camera.Parameters.FLASH_MODE_OFF;

    private void initCamera(){
        if(this._camera == null){
            this._camera = Camera.open();
            this._camera.startPreview();
        }
    }

    private void releaseCamera(){
        if(this._camera != null)
        {
            this._camera.stopPreview();
            this._camera.release();
        }

        this._camera = null;
    }

    private void setCameraParameter(String value){
        if(this._camera != null){
            Camera.Parameters params = this._camera.getParameters();
            params.setFlashMode(value);
            this._camera.setParameters(params);
        }
    }

    //To turn on just use:
    private void turnOn(){
        initCamera();
        setCameraParameter(MODE_TORCH);
    }

    //to turn off just use:
    private void turnOff(){
        setCameraParameter(MODE_OFF);
    }

    //To release resorces use:
    private void releaseResources(){
        releaseCamera();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up);

        //Encender el flash
       this.turnOn();
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();

        //Apagar la linterna
       this.turnOff();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
