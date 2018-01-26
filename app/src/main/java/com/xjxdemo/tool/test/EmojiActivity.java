package com.xjxdemo.tool.test;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.apkfuns.logutils.LogUtils;
import com.base.lib.base.SActivity;
import com.dd.plist.NSArray;
import com.dd.plist.PropertyListFormatException;
import com.dd.plist.PropertyListParser;
import com.xjxdemo.tool.test.constants.PathConstants;
import com.xjxdemo.tool.test.databinding.ActEmojiTestLayoutBinding;
import com.xjxdemo.tool.test.presenter.TestPresenter;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by 11073 on 2018/1/23.
 */

@Route(path = PathConstants.EmojiActivity)
public class EmojiActivity extends SActivity<TestPresenter, ActEmojiTestLayoutBinding> {

    private Map<String, String> map;

    @Override
    protected TestPresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.act_emoji_test_layout;
    }

    @Override
    protected void initTitle() {
        super.initTitle();
    }

    @Override
    protected void initBind() {
        super.initBind();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Thread(new Runnable() {
            @Override
            public void run() {
                parsePList();
            }
        }).start();
    }

    private void parsePList() {
        try {
            NSArray nsArray = (NSArray) PropertyListParser.parse(getAssets().open("Emoji.plist"));
            int count = nsArray.count();
            final StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < count; i++) {
                LogUtils.d(nsArray.objectAtIndex(i).toJavaObject().toString());
                stringBuffer.append(nsArray.objectAtIndex(i).toJavaObject().toString());
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mBinding.tvEmoji.setText(stringBuffer.toString());
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        } catch (PropertyListFormatException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}
