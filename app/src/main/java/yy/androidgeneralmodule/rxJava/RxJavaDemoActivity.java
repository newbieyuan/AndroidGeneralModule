package yy.androidgeneralmodule.rxJava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import yy.androidgeneralmodule.R;

public class RxJavaDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_demo);
    }

    public void click(View view) {
        RxDemo rxDemo = new RxDemo();
        rxDemo.getDrawableFromNet();
    }
}
