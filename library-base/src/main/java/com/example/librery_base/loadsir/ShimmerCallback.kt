package com.example.librery_base.loadsir

import android.content.Context
import android.view.View
import com.example.librery_base.R
import com.kingja.loadsir.callback.Callback

/**
 *description : <p>
 *骨架屏
 *</p>
 *
 *@author : flyli
 *@since :2021/5/20 13
 */
class ShimmerCallback: Callback() {
    override fun onCreateView(): Int {
        return R.layout.base_layout_placeholder
    }

    override fun onReloadEvent(context: Context?, view: View?): Boolean {
        return true
    }
}