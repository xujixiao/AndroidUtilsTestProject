package com.xjxdemo.tool.test.entity;

import com.base.lib.base.adapter.IHolderType;

/**
 * Copyright (C), 2011-2018 温州贷
 * FileName: com.weex.test.androidutilstestproject.entity.TestMainEntity.java
 * Author: xujixiao
 * Email: xujixiao@wzdai.com
 * Date: 2018/1/22 17:23
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * xujixiao      17:23    1.0        Create
 */
public class TestMainEntity implements IHolderType {
    public String text="xujixiao";
    @Override
    public int holderType() {
        return 0;
    }
}
