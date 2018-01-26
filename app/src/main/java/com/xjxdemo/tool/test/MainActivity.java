package com.xjxdemo.tool.test;

import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.base.lib.base.BaseListActivity;
import com.base.lib.base.adapter.IHolderType;
import com.base.lib.databinding.CommonListLayoutBinding;
import com.base.lib.utils.ArouterUtils;
import com.xjxdemo.tool.test.constants.PathConstants;
import com.xjxdemo.tool.test.databinding.ActivityMainBinding;
import com.xjxdemo.tool.test.entity.TestMainEntity;
import com.xjxdemo.tool.test.presenter.TestPresenter;

import java.util.ArrayList;

/**
 * 测试列表的使用方法
 */
public class MainActivity extends BaseListActivity<TestPresenter, CommonListLayoutBinding> {
    private ArrayList<TestMainEntity> mList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TestMainEntity e = new TestMainEntity();
        e.text = "jsoup解析数据测试";
        mList.add(e);
        e = new TestMainEntity();
        e.text = "emoji表情的自定义控件测试";
        mList.add(e);
        e = new TestMainEntity();
        e.text = "constraintlayout测试";
        mList.add(e);
        mAdapter.refreshData(mList);

    }

    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.activity_main;
    }

    @Override
    public void refreshNetData() {

    }

    @Override
    public void loadMoreData() {

    }

    @Override
    public void callBack(IHolderType entity, ViewDataBinding binding, int position) {
        super.callBack(entity, binding, position);
        if (position == 0) {
            ArouterUtils.go(PathConstants.JsoupActivity);
        } else if (position == 1) {
            ArouterUtils.go(PathConstants.EmojiActivity);
        } else if (position == 2) {
            ArouterUtils.go(PathConstants.ConstraintLayoutActivity);
        }
    }

    @Override
    public void bindData(IHolderType entity, int position, ViewDataBinding binding) {
        if (binding instanceof ActivityMainBinding) {
            ((ActivityMainBinding) binding).setEntity((TestMainEntity) entity);
        }

    }

}
