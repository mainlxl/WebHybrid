package com.mainli.hybrid.interaction;


import com.mainli.hybrid.javajs.JSAction;

import java.util.HashMap;

/**
 * Created by Mainli on 2016/11/2.
 * 分发管理器
 */
public interface DistributManage {
  JSAction onIntercept(HashMap<String, String> param);
}
