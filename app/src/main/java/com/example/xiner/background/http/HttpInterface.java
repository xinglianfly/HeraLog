package com.example.xiner.background.http;

import com.example.xiner.background.entity.ModifyRes;

/**
 * Created by seald on 2017/12/7.
 */

public interface HttpInterface {
    public void onSuccess(ModifyRes res);
    public void onFailed();
}
