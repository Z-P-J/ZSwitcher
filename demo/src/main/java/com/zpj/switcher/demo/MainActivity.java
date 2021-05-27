package com.zpj.switcher.demo;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.zpj.widget.switcher.CircleSwitcher;
import com.zpj.widget.switcher.CommonSwitcher;
import com.zpj.widget.switcher.OnCheckedChangeListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int defaultColor = ContextCompat.getColor(this, R.color.text_color_default);
        final int disabledColor = ContextCompat.getColor(this, R.color.text_color_disabled);

        final TextView tvSwitcherX = findViewById(R.id.tv_switcher_x);
        final TextView tvSwitcherC = findViewById(R.id.tv_switcher_c);
        CommonSwitcher switcherX = findViewById(R.id.switcher_x);
        switcherX.setOnColor(Color.BLACK);
        switcherX.setOffColor(Color.LTGRAY);
        final CircleSwitcher switcherC = findViewById(R.id.switcher_c);

        switcherX.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onChange(boolean checked) {
                switcherC.setChecked(checked);
                tvSwitcherX.setTextColor(checked ? defaultColor : disabledColor);
            }
        });

        switcherC.setOnCheckedChangeListener(checked -> tvSwitcherC.setTextColor(checked ? defaultColor : disabledColor));

        findViewById(R.id.dribbble).setOnClickListener(v -> {
            Uri uri = Uri.parse(getString(R.string.dribbble_link));
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });
    }

}
