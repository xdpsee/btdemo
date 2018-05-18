package com.zhenhui.demo.falcon.core.support.handler.filter;

import com.zhenhui.demo.falcon.core.support.Context;

public abstract class AbstractFilterPolicy implements FilterPolicy {

    protected final Context context;

    public AbstractFilterPolicy(Context context) {
        this.context = context;
    }
}


