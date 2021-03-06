package com.cniao.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cniao.CNiaoApplication;
import com.cniao.activity.LoginActivity;
import com.cniao.bean.User;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 高磊华
 * Time  2017/8/9
 * Describe: fragment的基类
 */

public abstract class BaseFragment extends Fragment {

    private   View           mView;
    protected Bundle         savedInstanceState;
    public    Context        mContext;
    protected LayoutInflater mInflater;
    Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mInflater = inflater;
        this.savedInstanceState=savedInstanceState;
        mView=mInflater.inflate(getContentResourseId(), null);
        unbinder= ButterKnife.bind(this,mView);
        init();
        return mView;
    }

    protected abstract void init();

    protected abstract int getContentResourseId();

    public void startActivity(Intent intent, boolean isNeedLogin){

        if (isNeedLogin) {
            User user = CNiaoApplication.getInstance().getUser();
            if (user != null) {
                super.startActivity(intent);    //需要登录,切已经登录.直接跳到目标activity中
            } else {
                CNiaoApplication.getInstance().putIntent(intent);
                Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
                super.startActivity(loginIntent);
            }
        } else {
            super.startActivity(intent);
        }
    }

}
