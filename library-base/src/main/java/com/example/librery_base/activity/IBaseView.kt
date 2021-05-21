package com.example.librery_base.activity

/**
 *description : <p>
 *界面ui的顶级接口，显示切换流程
 *</p>
 *
 *@author : flyli
 *@since :2021/5/20 12
 */
interface IBaseView {
    /**
     * 显示内容
     */
    fun showContent()

    /**
     * 显示加载提示
     */
    fun showLoading()

    /**
     * 显示空页面
     */
    fun showEmpty()

    /**
     * 刷新失败
     */
    fun showFailure(message:String)
}