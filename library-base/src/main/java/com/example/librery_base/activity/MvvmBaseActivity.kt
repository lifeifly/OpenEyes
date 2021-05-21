package com.example.librery_base.activity

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.librery_base.loadsir.EmptyCallback
import com.example.librery_base.loadsir.ErrorCallback
import com.example.librery_base.loadsir.LoadingCallback
import com.example.librery_base.utils.ToastUtils
import com.example.librery_base.viewmodel.IMvvmBaseVM
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir

/**
 *description : <p>
 *activity抽象基类
 *</p>
 *
 *@author : flyli
 *@since :2021/5/19 22
 */
abstract class MvvmBaseActivity<V:ViewDataBinding,VM:IMvvmBaseVM<Activity>>:AppCompatActivity(),IBaseView {
    //需要绑定的viewmodel
    protected var vm:VM?=null

    //需要和viewmodel绑定的databinding
    protected lateinit var viewDataBinding:V

    //加载反馈框架
    protected var loadService:LoadService<*>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //初始化viewmodel
        initViewModel()
        //初始化databinding，绑定ui和viewmodel
        performDatading()
    }

    /**
     * 解除绑定
     */
    override fun onDestroy() {
        super.onDestroy()
        if (vm!=null&&vm?.isAttachUi()!!){
            vm?.detachUi()
        }
    }

    /**
     * 创建databinding
     */
    private fun performDatading() {
        viewDataBinding=DataBindingUtil.setContentView(this,getLayoutId())
        //如果vm没有初始化，重新赋值
        this.vm=if (vm==null)getViewModel() else vm
        if (getBindingVariable()>0){
            //为布局设置绑定的viewmodel,将视图和viewmodel绑定
            viewDataBinding.setVariable(getBindingVariable(),vm)
        }
        viewDataBinding.executePendingBindings()

    }

    /**
     * 初始化viewmodel
     */
    private fun initViewModel() {
        vm=getViewModel()
        if (vm!=null){
            //绑定ui
            vm?.attachUi(this)
        }
    }

    /**
     * 注册loadsir
     * @param view View 替换视图
     */
    fun setLoadSir(view:View){
        if (loadService==null){
            //注册重新加载的监听器
            loadService=LoadSir.getDefault().register(view){v: View? -> onRetryClick() }
        }
    }
    //标记是否显示页面
    private var isShowContent=false

    override fun showContent() {
        if (loadService!=null){
            isShowContent=true
            loadService?.showSuccess()
        }
    }

    override fun showLoading() {
        if (loadService!=null){
            loadService?.showCallback(LoadingCallback::class.java)
        }
    }

    override fun showEmpty() {
        if (loadService!=null){
            loadService?.showCallback(EmptyCallback::class.java)
        }
    }

    override fun showFailure(message: String) {
        if (loadService!=null){
            loadService?.showCallback(ErrorCallback::class.java)
        }else{
            ToastUtils.show(this,message)
        }
    }

    /**
     * 子类创建自己的viewmodel
     * @return VM
     */
    protected abstract fun getViewModel():VM?

    /**
     * 子类的布局
     * @return Int
     */
    protected abstract fun getLayoutId():Int

    /**
     * 获取参数variable
     * @return Int
     */
    protected abstract fun getBindingVariable():Int

    /**
     * 失败重试
     */
    protected abstract fun onRetryClick()
}