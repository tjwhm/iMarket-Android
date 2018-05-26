package com.twtstudio.tjwhm.imarket.customer

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class CustomerPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    var list: MutableList<Pair<Fragment, String>> = mutableListOf()

    override fun getItem(position: Int): Fragment {
        return list[position].first
    }

    fun add(pair: Pair<Fragment, String>) {
        list.add(pair)
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return list[position].second
    }
}