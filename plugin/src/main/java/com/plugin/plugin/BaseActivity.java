package com.plugin.plugin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.plugin.library.IPlugin;

/**
 * Copyright (c), 2018-2019
 *
 * @author: lixin
 * Date: 2019/4/15
 * Description:
 */
public class BaseActivity extends Activity implements IPlugin {

    protected Activity that;

    /**
     * super 最终调用了系统给注入的上下文
     * @param view
     */
    @Override
    public void setContentView(View view) {
        if (that == null) {
            super.setContentView(view);
        } else {
            that.setContentView(view);
        }
    }

    @Override
    public <T extends View> T findViewById(int id) {
        if (that == null) {
            return super.findViewById(id);
        } else {
            return that.findViewById(id);
        }
    }

    @Override
    public LayoutInflater getLayoutInflater() {
        if (that == null) {
            return super.getLayoutInflater();
        } else {
            return that.getLayoutInflater();
        }
    }

    @Override
    public Window getWindow() {
        if (that == null) {
            return super.getWindow();
        } else {
            return that.getWindow();
        }
    }

    @Override
    public WindowManager getWindowManager() {
        if (that == null) {
            return super.getWindowManager();
        } else {
            return that.getWindowManager();
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onCreate(Bundle savedInstanceState) {
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onStart() {
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRestart() {
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onResume() {
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onPause() {
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onStop() {
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onDestroy() {
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void attach(Activity activity) {
        that = activity;
    }

    @Override
    public void startActivity(Intent intent) {
        Intent newIntent = new Intent();
        newIntent.putExtra("className", intent.getComponent().getClassName());
        that.startActivity(newIntent);
    }
}
