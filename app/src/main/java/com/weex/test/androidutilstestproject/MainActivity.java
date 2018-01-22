package com.weex.test.androidutilstestproject;

import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.base.lib.base.BaseListActivity;
import com.base.lib.base.adapter.IHolderType;
import com.base.lib.databinding.CommonListLayoutBinding;
import com.weex.test.androidutilstestproject.databinding.ActivityMainBinding;
import com.weex.test.androidutilstestproject.entity.TestMainEntity;
import com.weex.test.androidutilstestproject.presenter.TestPresenter;

import java.util.ArrayList;

/**
 * 测试列表的使用方法
 */
public class MainActivity extends BaseListActivity<TestPresenter, CommonListLayoutBinding> {
    private ArrayList<TestMainEntity> mList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (int i = 0; i < 20; i++) {
            mList.add(new TestMainEntity());
        }
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
    public void bindData(IHolderType entity, int position, ViewDataBinding binding) {
        if (binding instanceof ActivityMainBinding) {
            ((ActivityMainBinding) binding).setEntity((TestMainEntity) entity);
        }

    }

}
