/*
 * @author flyli
 */

package com.example.library_common.views

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import android.widget.TextView

/**
 *description : <p>
 *使用定制字体的textview
 *</p>
 *
 *@author : flyli
 *@since :2021/5/19 15
 */
class CommonCustomTextView : androidx.appcompat.widget.AppCompatTextView {
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView(context)
    }

    /**
     * 定制字体
     * @param context Context?
     */
    private fun initView(context: Context?) {
        //获取资源文件
        val assets = context?.assets
        if (assets != null) {
            val font = Typeface.createFromAsset(assets, "fonts/Lobster-1.4.otf")
            setTypeface(font)
        }
    }


}