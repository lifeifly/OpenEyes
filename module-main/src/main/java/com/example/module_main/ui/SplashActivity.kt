

package com.example.module_main.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.library_common.adapter.ScreenAutoAdapter
import com.example.librery_base.storage.MmkvHelper
import com.example.module_main.R
import com.gyf.immersionbar.BarHide
import com.gyf.immersionbar.ImmersionBar
import kotlinx.android.synthetic.main.activity_splash.*

/**
*description :
*主业务模块
 *欢迎页面
*@author : flyli
*@since :
 *
*/
class SplashActivity : AppCompatActivity() {
    private val mHandler=Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //适配屏幕
        ScreenAutoAdapter.match(this,375)
        setContentView(R.layout.activity_splash)
        //实现沉浸式
        ImmersionBar.with(this)
            .titleBar(view)
            .hideBar(BarHide.FLAG_HIDE_BAR)//隐藏状态栏和导航栏
            .init()
        //延迟3秒发送消息,跳转到MainActivity
        mHandler.postDelayed(this::startToMain,3000)
    }
    //跳转到MainActivity
    private fun startToMain(){
        if (MmkvHelper.getMMKV().decodeBool("first",true)){
            startActivity(Intent(this,MainActivity::class.java))
        }else{
            MainActivity.start(this)
        }
    }

    override fun onStop() {
        super.onStop()
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        //移除所有消息，防止内存泄漏
        mHandler.removeCallbacksAndMessages(null)
    }
}