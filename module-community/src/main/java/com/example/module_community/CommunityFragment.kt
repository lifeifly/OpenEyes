package com.example.module_community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.library_common.router.RouterFragmentPath

/**
 *description : <p>
 *应用描述
 *</p>
 *
 *@author : flyli
 *@since :2021/5/21 00
 */
@Route(path = RouterFragmentPath.Community.PAGER_COMMUNITY)
class CommunityFragment:Fragment() {
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view=
                LayoutInflater.from(context).inflate(R.layout.fragment_community_layout,container,false)
            return view
        }

}