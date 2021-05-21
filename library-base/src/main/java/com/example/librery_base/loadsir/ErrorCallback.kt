package com.example.librery_base.loadsir

import com.example.librery_base.R
import com.kingja.loadsir.callback.Callback

/**
 *description : <p>
 *错误页面
 *</p>
 *
 *@author : flyli
 *@since :2021/5/20 13
 */
class ErrorCallback:Callback() {
    //返回自定义布局
    override fun onCreateView(): Int {
       return R.layout.base_layout_error
    }
}