package com.bawei.liangjinzi20190415.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.liangjinzi20190415.R;
import com.bumptech.glide.Glide;

public class ShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        ImageView image=findViewById(R.id.image);
        TextView ni = findViewById(R.id.ni);


    }
}
