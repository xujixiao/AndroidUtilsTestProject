package com.xjxdemo.tool.test;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.apkfuns.logutils.LogUtils;
import com.base.lib.base.SActivity;
import com.xjxdemo.tool.test.constants.PathConstants;
import com.xjxdemo.tool.test.databinding.ActJsoupParseExampleLayoutBinding;
import com.xjxdemo.tool.test.presenter.TestPresenter;
import com.xjxdemo.tool.test.utils.HtmlUtils;

import java.util.Map;

/**
 * Created by 11073 on 2018/1/23.
 */

@Route(path = PathConstants.JsoupActivity)
public class JsoupActivity extends SActivity<TestPresenter, ActJsoupParseExampleLayoutBinding> {

    private Map<String, String> map;

    @Override
    protected TestPresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.act_jsoup_parse_example_layout;
    }

    @Override
    protected void initTitle() {
        super.initTitle();
    }

    @Override
    protected void initBind() {
        super.initBind();
        mBinding.btParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String urlString = mBinding.etUrl.getText().toString();
                        String urlcontent = HtmlUtils.getHtmlString(urlString);
                        map = HtmlUtils.parseHtmlString(urlString, urlcontent);
                        LogUtils.d(map);

                        String desc = HtmlUtils.getHtmlDesc(urlString);
                        LogUtils.d("描述文本：" + desc + "----title----" + HtmlUtils.getHtmlTitle(urlString));
                        HtmlUtils.getHtmlInfo(urlString);

                    }
                }).start();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
