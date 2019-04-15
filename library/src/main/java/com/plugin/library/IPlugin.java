package com.plugin.library;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

/**
 * Copyright (c), 2018-2019
 *
 * @author: lixin
 * Date: 2019/4/15
 * Description: 定义规范
 */
public interface IPlugin {

    public void onCreate(Bundle savedInstanceState);
    public void onStart();
    public void onRestart();
    public void onResume();
    public void onPause();
    public void onStop();
    public void onDestroy();
    public void onSaveInstanceState(Bundle outState);
    public boolean onTouchEvent(MotionEvent event);
    public void onBackPressed();

    /**
     * 注入上下文
     */
    public void attach(Activity activity);
}
