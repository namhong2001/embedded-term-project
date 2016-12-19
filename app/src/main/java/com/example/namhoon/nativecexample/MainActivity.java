package com.example.namhoon.nativecexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        EditText text = (EditText) findViewById(R.id.editText);
        String string = text.getText().toString();
//        writeSeg(string);
        writeDev(string, 0);
        writeDev(string, 1);
//        Toast.makeText(getApplicationContext(), ret, Toast.LENGTH_SHORT).show();
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
//    public native String stringFromJNI();
    public native void writeDev(String string, int type);
//    public native void writeLed(String string);
}
