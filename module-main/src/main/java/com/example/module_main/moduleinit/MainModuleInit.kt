/*
 * @author flyli
 */

package com.example.module_main.moduleinit
import com.example.library_base.base.BaseApplication
import com.example.library_common.IModuleInit
import com.example.library_common.adapter.ScreenAutoAdapter
import com.example.librery_base.loadsir.*
import com.gyf.immersionbar.ImmersionBar
import com.kingja.loadsir.core.LoadSir

/**
 *description : <p>
 *main组件的业务初始化
 *</p>
 *
 *@author : flyli
 *@since :2021/5/19 13
 */
class MainModuleInit : IModuleInit {
    override fun onInitAhead(application: BaseApplication): Boolean {
        //初始化屏幕适配
        ScreenAutoAdapter.setUp(application)
        //初始化LoadSir加载反馈框架
        LoadSir.beginBuilder()
            .addCallback(ErrorCallback())//错误回调
            .addCallback(LoadingCallback())//加载中回调
            .addCallback(ShimmerCallback())//框架占位回调
            .addCallback(EmptyCallback())//空页面回调
            .addCallback(TimeoutCallback())//超时回调
            .setDefaultCallback(LoadingCallback::class.java)//默认加载中回调
            .commit()
        return false
    }

    override fun onInitBehind(application: BaseApplication): Boolean {
        return false
    }

}