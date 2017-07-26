package com.customrelativelayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    private CustomRelativeLayout setting;
    private CustomRelativeLayout mine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setting = (CustomRelativeLayout) findViewById(R.id.setting);
        setting.setOnSettingItemonClick(new CustomRelativeLayout.OnSettingItemonClick() {
            @Override
            public void onClick() {
                setting.setSettingData("版本2.0", false);
            }
        });
        mine = (CustomRelativeLayout) findViewById(R.id.mine);
        mine.setOnSettingItemonClick(new CustomRelativeLayout.OnSettingItemonClick() {
            @Override
            public void onClick() {
                mine.setSettingData("您有新消息", true);
            }
        });


    }
}
