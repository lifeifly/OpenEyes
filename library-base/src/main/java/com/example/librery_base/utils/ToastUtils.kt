package com.example.librery_base.utils

import android.content.Context
import android.text.TextUtils
import android.widget.Toast

/**
 *description : <p>
 *吐司工具类
 *</p>
 *
 *@author : flyli
 *@since :2021/5/20 13
 */
class ToastUtils {
    companion object{
        private var mToast:Toast?=null

        fun show(context: Context,message:String){
            if (!TextUtils.isEmpty(message)){
                if (mToast!=null){
                    mToast?.cancel()
                }
                mToast=Toast.makeText(context,"",Toast.LENGTH_LONG)
                mToast?.setText(message)
                mToast?.show()
            }
        }
    }
}