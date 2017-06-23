package com.etouse.skinswitch.act;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.etouse.skinswitch.R;

import java.io.File;

import cn.feng.skin.manager.base.BaseActivity;
import cn.feng.skin.manager.listener.ILoaderListener;
import cn.feng.skin.manager.loader.SkinManager;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private static final String SKIN_NAME = "BlackFantacy.skin";
    private static final String SKIN_DIR = Environment
            .getExternalStorageDirectory() + File.separator + SKIN_NAME;
    private Button btnBlack;
    private Button btnDefault;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btnBlack = (Button) findViewById(R.id.btn_black);
        btnDefault = (Button) findViewById(R.id.btn_default);
        btnBlack.setOnClickListener(this);
        btnDefault.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_black:
                onSkinBlack();
                break;
            case R.id.btn_default:
                onSkinReset();
                break;
        }
    }


    protected void onSkinReset() {
        SkinManager.getInstance().restoreDefaultTheme();
    }

    private void onSkinBlack() {

        File skin = new File(SKIN_DIR);

        if(skin == null || !skin.exists()){
            Toast.makeText(getApplicationContext(), "请检查" + SKIN_DIR + "是否存在", Toast.LENGTH_SHORT).show();
            return;
        }

        SkinManager.getInstance().load(skin.getAbsolutePath(),
                new ILoaderListener() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess() {
                        Toast.makeText(getApplicationContext(), "切换成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailed() {
                        Toast.makeText(getApplicationContext(), "切换失败", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
