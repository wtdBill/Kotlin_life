package com.example.ypp.life.activity;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.view.animation.Animation;

import com.example.ypp.life.R;
import com.example.ypp.life.utils.Logger;
import com.example.ypp.life.utils.StatusBarUtil;
import com.example.ypp.life.utils.Utils;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class Main3Activity extends AppCompatActivity {


    private String aa[] = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
//        StatusBarUtil.setTranslucentForCoordinatorLayout(this, 127);
        Main3ActivityPermissionsDispatcher.requestCameraWithPermissionCheck(this);
    }

    private void init() {
        //直接在style里面设置了共享元素的Transition
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setSharedElementEnterTransition(new ChangeBounds());
            getWindow().setSharedElementExitTransition(new ChangeBounds());
            getWindow().setSharedElementReenterTransition(new ChangeBounds());
            getWindow().setSharedElementReturnTransition(new ChangeBounds());
            getWindow().getSharedElementEnterTransition().addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {

                }

                @Override
                public void onTransitionEnd(Transition transition) {

                }

                @Override
                public void onTransitionCancel(Transition transition) {

                }

                @Override
                public void onTransitionPause(Transition transition) {

                }

                @Override
                public void onTransitionResume(Transition transition) {

                }
            });
        }
    }


    @NeedsPermission(Manifest.permission.CAMERA)
    void requestCamera() {
        Logger.e("camera");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Main3ActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void requestWrite() {
        Logger.e("write");
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return null;
        }

        @Override
        public int getCount() {
            return aa.length;
        }
    }
}
