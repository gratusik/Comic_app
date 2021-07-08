package com.gratus.browse.presentation

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class BrowseAdapter(activity: BrowseFragment) :
    FragmentStateAdapter(activity) {
    private var comicCount: Int = 0

    // create a new instance of fragment on increase in comic count
    override fun createFragment(position: Int): Fragment {
        return BrowseCardFragment.newInstance(position + 1)
    }

    override fun getItemCount(): Int {
        return comicCount
    }

    fun update(count: Int) {
        comicCount = count
        notifyDataSetChanged()
    }
}
