package com.filizuzulmez.turkiyefinans;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.FragmentManager;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.filizuzulmez.turkiyefinans.model.Result;
import com.filizuzulmez.turkiyefinans.view.DetailPageFragment;
import com.filizuzulmez.turkiyefinans.view.SearchPageFragment;

public class MainActivity extends AppCompatActivity {

    FrameLayout frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frame=findViewById(R.id.frameLayout);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new SearchPageFragment()).commit();

    }

}