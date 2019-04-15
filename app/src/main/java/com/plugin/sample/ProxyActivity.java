package com.plugin.sample;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import com.plugin.library.IPlugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Copyright (c), 2018-2019
 *
 * @author: lixin
 * Date: 2019/4/15
 * Description:
 */
public class ProxyActivity extends Activity {

    /**
     * 要跳转到插件的Activity
     */
    private String className;

    IPlugin mIPlugin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        className = getIntent().getStringExtra("className");
        try {
            Class activityClass = getClassLoader().loadClass(className);
            Constructor constructor = activityClass.getConstructor(new Class[]{});
            Object instance = constructor.newInstance(new Object[]{});
            mIPlugin = (IPlugin) instance;
            mIPlugin.attach(this);
            Bundle bundle = new Bundle();
            mIPlugin.onCreate(bundle);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mIPlugin.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mIPlugin.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mIPlugin.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mIPlugin.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mIPlugin.onRestart();
    }

    @Override
    public void startActivity(Intent intent) {
        String classNameFromPlugin = intent.getStringExtra("className");
        Intent intentSelf = new Intent(this, ProxyActivity.class);
        intentSelf.putExtra("className", classNameFromPlugin);
        super.startActivity(intentSelf);
    }

    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance().getDexClassLoader();
    }

    @Override
    public Resources getResources() {
        return PluginManager.getInstance().getResources();
    }
}
