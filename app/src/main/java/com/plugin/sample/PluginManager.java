package com.plugin.sample;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * Copyright (c), 2018-2019
 *
 * @author: lixin
 * Date: 2019/4/15
 * Description:
 */
public class PluginManager {

    private DexClassLoader mDexClassLoader;
    private Resources mResources;
    private Context mContext;
    public String entryActivityName;

    private static final PluginManager ourInstance = new PluginManager();

    public static PluginManager getInstance() {
        return ourInstance;
    }

    private PluginManager() {
    }

    public void loadPath(String path) {
        File dexOutFile = mContext.getDir("dex", Context.MODE_PRIVATE);
        mDexClassLoader = new DexClassLoader(path, dexOutFile.getAbsolutePath(), null, mContext.getClassLoader());

        //获取plugin.apk文件中的第一个Activity
        PackageManager packageManager = mContext.getPackageManager();
        PackageInfo packageInfo = packageManager.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES);
        entryActivityName = packageInfo.activities[0].name;

        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = AssetManager.class.getMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, path);
            mResources = new Resources(assetManager, mContext.getResources().getDisplayMetrics(), mContext.getResources().getConfiguration());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public DexClassLoader getDexClassLoader() {
        return mDexClassLoader;
    }

    public Resources getResources() {
        return mResources;
    }

    public String getEntryActivityName() {
        return entryActivityName;
    }

    public void setContext(Context context) {
        mContext = context.getApplicationContext();
    }
}
