package com.example.adambodnar.watchsocket;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.widget.Toast;

import static android.content.Context.SENSOR_SERVICE;

public class SensorHelper implements SensorEventListener {

    private final SensorManager mSensorManager;
    private final Sensor mHeartRate;
    public String sensorValue = "deafult";

    public SensorHelper() {
        Log.i("SENSOR", "SENSOR CONSTRUCTOR");
        mSensorManager = (SensorManager) MyApplication.getAppContext().getSystemService(SENSOR_SERVICE);
        mHeartRate = mSensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE);
        mSensorManager.registerListener(this, mHeartRate, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_HEART_RATE) {
            String msg = "" + (int)event.values[0];
            this.sensorValue = msg;
            Log.d("SENSOR", msg);
        }
        else
            this.sensorValue = "unable to read value";
            Log.d("SENSOR", "Unknown sensor type");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
