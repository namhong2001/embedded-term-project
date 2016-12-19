package com.example.namhoon.nativecexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends Activity {
    int money = 0;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        writeDev("0", 0);
    }

    public void onClickBuy(View view) {
        Button b = (Button)view;
        String buttonText = b.getText().toString();
        int price = Integer.parseInt(buttonText);
        if (price > money) {
            Toast.makeText(getApplicationContext(), "insert more money", Toast.LENGTH_SHORT).show();
        } else {
            money -= price;
            writeDev(String.format(Locale.US, "%07d", money), 0);
        }

//        writeDev(string, 0);
//        writeDev(string, 1);
//        Toast.makeText(getApplicationContext(), ret, Toast.LENGTH_SHORT).show();
    }

    public void onClickInsertCoin(View view) {
        Button b = (Button)view;
        String buttonText = b.getText().toString();
        money += Integer.parseInt(buttonText);
        writeDev(String.format(Locale.US, "%07d", money), 0);
    }


    public void onClickDone(View view) {
        money = 0;
        writeDev(String.format(Locale.US, "%07d", money), 0);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
//    public native String stringFromJNI();
    public native void writeDev(String string, int type);
//    public native void writeLed(String string);
}
