package com.example.librery_base.loadsir

import com.example.librery_base.R
import com.kingja.loadsir.callback.Callback

/**
 *description : <p>
 *空页面
 *</p>
 *
 *@author : flyli
 *@since :2021/5/20 13
 */
class EmptyCallback:Callback() {
    override fun onCreateView(): Int {
        return R.layout.base_layout_empty
    }
}