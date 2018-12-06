package com.dragger2.reactnativetest1022.ok;

import com.dragger2.reactnativetest1022.Replace;

/**
 * Description :服务端已经修复好的代码
 * <p>
 * Author:yang
 * <p>
 * Email:1318392199@qq.com
 * <p>
 * Date: 2018/11/21
 */

public class Caculutor {

    //计算方法
    @Replace(Class = "com.dragger2.reactnativetest1022.Caculutor",Method = "caculutor")
    public int caculutor() {
        int i = 1;
        int j = 100;
        return j / i;
    }
}
