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
    int stock = 666666;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        writeDev("0", 0);
        writeDev(String.format(Locale.US, "%07d", stock), 1);
    }

    public void onClickBuy(View view) {
        Button b = (Button)view;
        String buttonText = b.getText().toString();
        int price = Integer.parseInt(buttonText);
        int myStock = getStock(b.getId());
        int stockPosition = getStockPosition(b.getId());
        if (myStock <= 0) {
            Toast.makeText(getApplicationContext(), "no stock", Toast.LENGTH_SHORT).show();
        } else if (price > money) {
            Toast.makeText(getApplicationContext(), "insert more money", Toast.LENGTH_SHORT).show();
        } else {
            money -= price;
            int myStockInt = 1;
            for (int i=6; i>stockPosition; --i) {
                myStockInt *= 10;
            }
            stock -= myStockInt;
            writeDev(String.format(Locale.US, "%07d", stock), 1);
            writeDev(String.format(Locale.US, "%07d", money), 0);
        }

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


    public int getStock(int id) {
        switch(id) {
            case R.id.product1 :
                return (int)String.format(Locale.US, "%07d", stock).charAt(1) - '0';
            case R.id.product2 :
                return (int)String.format(Locale.US, "%07d", stock).charAt(2) - '0';
            case R.id.product3 :
                return (int)String.format(Locale.US, "%07d", stock).charAt(3) - '0';
            case R.id.product4 :
                return (int)String.format(Locale.US, "%07d", stock).charAt(4) - '0';
            case R.id.product5 :
                return (int)String.format(Locale.US, "%07d", stock).charAt(5) - '0';
            case R.id.product6 :
                return (int)String.format(Locale.US, "%07d", stock).charAt(6) - '0';
        }
        return 0;
    }


    public int getStockPosition(int id) {
        switch(id) {
            case R.id.product1 :
                return 1;
            case R.id.product2 :
                return 2;
            case R.id.product3 :
                return 3;
            case R.id.product4 :
                return 4;
            case R.id.product5 :
                return 5;
            case R.id.product6 :
                return 6;
        }
        return 6;
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
//    public native String stringFromJNI();
    public native void writeDev(String string, int type);
//    public native void writeLed(String string);
}
