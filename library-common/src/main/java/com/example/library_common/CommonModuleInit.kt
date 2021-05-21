package com.example.library_common

import com.alibaba.android.arouter.launcher.ARouter
import com.example.library_base.base.BaseApplication
import com.tencent.mmkv.MMKV

/**
 *description : <p>
 *通用库common 基础库 base 自身初始化
 *</p>
 *
 *@author : flyli
 *@since :2021/5/19 22
 */
class CommonModuleInit:IModuleInit {
    override fun onInitAhead(application: BaseApplication): Boolean {
        //初始化MMKV
        MMKV.initialize(application)
        //初始化ARouter
        if (application.getDebug()){
            ARouter.openLog()//开启日志
            ARouter.openDebug()// 使用InstantRun的时候，需要打开该开关，上线之后关闭，否则有安全风险
        }
        ARouter.init(application)

        return false
    }

    override fun onInitBehind(application: BaseApplication): Boolean {
        return false
    }
}