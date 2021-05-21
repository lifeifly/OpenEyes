package com.example.module_main.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.library_common.router.RouterActivityPath
import com.example.library_common.router.RouterFragmentPath
import com.example.library_common.adapter.ScreenAutoAdapter
import com.example.librery_base.activity.MvvmBaseActivity
import com.example.librery_base.storage.MmkvHelper
import com.example.librery_base.viewmodel.IMvvmBaseVM
import com.example.module_main.R
import com.example.module_main.adapter.MainPagerAdapter
import com.example.module_main.databinding.ActivityMainBinding
import com.example.module_main.utils.ColorUtils
import com.gyf.immersionbar.ImmersionBar
import me.majiajie.pagerbottomtabstrip.NavigationController

/**
 * app 主页面
 */

@Route(path = RouterActivityPath.Main.PAGER_MAIN)
class MainActivity : MvvmBaseActivity<ActivityMainBinding, IMvvmBaseVM<Activity>>() {
    //viewpager的fragment
    private lateinit var fragments: MutableList<Fragment>

    //适配器
    private var adapter: MainPagerAdapter? = null

    //导航控制器
    private var mNavigationController: NavigationController? = null

    companion object {
        /**
         * 启动该Activity
         * @param context Context
         */
        fun start(context: Context) {
            MmkvHelper.getMMKV().encode("first", false)
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        //适配屏幕
        ScreenAutoAdapter.match(this, 375)
        super.onCreate(savedInstanceState)
        //沉浸式
        ImmersionBar.with(this)
            .statusBarColor(R.color.main_color_bar)//设置状态栏的颜色
            .navigationBarColor(R.color.main_color_bar)//设置导航栏颜色
            .fitsSystemWindows(true)//内容不延长到透明的导航栏和状态栏
            .autoDarkModeEnable(true)
            .init()
        initView()
        initFragment()
    }

    private fun initFragment() {
        fragments= mutableListOf()
        //通过ARouter 获取其他组件提供的fragment
        val homeFragment=ARouter.getInstance().build(RouterFragmentPath.Home.PAGER_HOME).navigation()
        val communityFragment=ARouter.getInstance().build(RouterFragmentPath.Community.PAGER_COMMUNITY).navigation()
        val moreFragment=ARouter.getInstance().build(RouterFragmentPath.More.PAGER_MORE).navigation()
        val userFragment=ARouter.getInstance().build(RouterFragmentPath.User.PAGER_USER).navigation()
        if (homeFragment != null) {
            fragments.add(homeFragment as Fragment)
        }
        if (communityFragment != null) {
            fragments.add(communityFragment as Fragment)
        }
        if (moreFragment != null) {
            fragments.add(moreFragment as Fragment)
        }
        if (userFragment != null) {
            fragments.add(userFragment as Fragment)
        }
        adapter?.setFragment(fragments)
    }

    /**
     * 初始化view
     */
    private fun initView() {
        mNavigationController = viewDataBinding.bottomView.material()
            .addItem(
                R.drawable.main_home,
                "首页",
                ColorUtils.getColor(
                    this,
                    R.color.main_bottom_check_color
                )
            ).addItem(
                R.drawable.main_community,
                "社区",
                ColorUtils.getColor(
                    this,
                    R.color.main_bottom_check_color
                )
            ).addItem(
                R.drawable.main_notify,
                "通知",
                ColorUtils.getColor(
                    this,
                    R.color.main_bottom_check_color
                )
            ).addItem(
                R.drawable.main_user,
                "我的",
               ColorUtils.getColor(
                    this,
                    R.color.main_bottom_check_color
                )
            )
            .setDefaultColor(
                ColorUtils.getColor(
                    this,
                    R.color.main_bottom_default_color
                )
            )
            .enableAnimateLayoutChanges()
            .build()
        mNavigationController?.setHasMessage(2, true)
        mNavigationController?.setMessageNumber(3, 6)
        adapter = MainPagerAdapter(
            supportFragmentManager
        )
        //设置预加载
        viewDataBinding.cvContentView.offscreenPageLimit = 1
        viewDataBinding.cvContentView.adapter=adapter
        mNavigationController?.setupWithViewPager(viewDataBinding.cvContentView)
    }

    override fun getViewModel(): IMvvmBaseVM<Activity>? {
        return null
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getBindingVariable(): Int {
        return 0
    }

    override fun onRetryClick() {

    }


}