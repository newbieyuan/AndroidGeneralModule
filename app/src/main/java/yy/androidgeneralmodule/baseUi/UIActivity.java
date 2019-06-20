package yy.androidgeneralmodule.baseUi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import yy.androidgeneralmodule.R;

public class UIActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);
    }

    public void baseActivity(View view) {
        startActivity(new Intent(this, BaseActivity.class));
    }

    public void baseFragment(View view) {
    }
}
