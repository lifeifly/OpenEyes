package com.example.module_main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 *description : <p>
 *ViewpAger适配器
 *</p>
 *
 *@author : flyli
 *@since :2021/5/20 15
 */
class MainPagerAdapter : FragmentPagerAdapter {
    private var mFragments: MutableList<Fragment> = mutableListOf()

    constructor(fm: FragmentManager) : this(fm, 0)
    constructor(fm: FragmentManager, behavior: Int) : super(fm, behavior)

    fun setFragment(data: List<Fragment>) {

        mFragments.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Fragment {
        return mFragments[position]
    }

    override fun getCount(): Int {
        return mFragments.size
    }
}