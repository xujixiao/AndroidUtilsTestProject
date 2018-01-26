package com.xjxdemo.tool.test;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.base.lib.base.SActivity;
import com.xjxdemo.tool.test.constants.PathConstants;
import com.xjxdemo.tool.test.databinding.ActConstraintLayoutBinding;
import com.xjxdemo.tool.test.presenter.TestPresenter;

/**
 * Created by 11073 on 2018/1/26.
 */

@Route(path = PathConstants.ConstraintLayoutActivity)
public class ConstraintLayoutActivity extends SActivity<TestPresenter, ActConstraintLayoutBinding> {
    @Override
    protected TestPresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.act_constraint_layout;
    }
}
