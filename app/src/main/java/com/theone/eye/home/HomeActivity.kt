package com.theone.eye.home

import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.themone.core.base.impl.CoreFragment
import com.themone.core.util.StatusBarUtil
import com.theone.eye.R
import com.theone.eye.databinding.ActivityHomeBinding
import com.theone.eye.mine.MineFragment
import com.theone.framework.base.BaseActivity
import com.theone.framework.widget.smarttablayout.SimpleSmartTabAdapter
import com.theone.framework.widget.smarttablayout.SmartTabLayout

class HomeActivity : BaseActivity() {

    lateinit var binding: ActivityHomeBinding
    private var viewPagerAdapter: ViewPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewPager()
        setBottomBar()
        setStatusBar()
    }

    private fun setStatusBar() {
        StatusBarUtil.compat(this, true)
    }

    private fun setBottomBar() {
        val tabs = resources.getStringArray(R.array.home_tab)
        val smartTabAdapter = object : SimpleSmartTabAdapter(R.layout.home_tab_smart_tab_text) {
            override fun getCount(): Int {
                return tabs.size
            }

            override fun getTabTitle(position: Int): String? {
                return tabs[position]
            }

            override fun onBindViewHolderExt(textView: TextView, isSelect: Boolean) {
//                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, if (isSelect) getDimension(R.dimen.font_text_0) else getDimension(R.dimen.font_text_1))
                textView.paint.isFakeBoldText = isSelect
            }
        }
        binding.smartTabSTL.setOnTabClickListener(object : SmartTabLayout.OnTabClickListener {
            override fun onTabClicked(position: Int) {
                binding.viewpager.setCurrentItem(position, true)
            }
        })
        binding.smartTabSTL.setTabAdapter(smartTabAdapter)
    }

    private fun initViewPager() {
        if (viewPagerAdapter == null) {
            viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
            binding.viewpager.adapter = viewPagerAdapter
            binding.viewpager.offscreenPageLimit = 3
            binding.viewpager.setCurrentItem(TAB_HOME, false)
        }
    }

    internal class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                TAB_TICKET -> HomeFragment.instance
                TAB_MINE -> MineFragment.instance
                TAB_HOME -> HomeFragment.instance
                TAB_SHOW -> HomeFragment.instance
                else -> HomeFragment.instance
            }
        }


        override fun getCount(): Int {
            return TAB_SIZE
        }

        var mCurrentPrimaryItem: CoreFragment? = null
        fun getCurrentPrimaryItem(): CoreFragment? {
            return mCurrentPrimaryItem
        }

        override fun setPrimaryItem(container: ViewGroup, position: Int, item: Any) {
            super.setPrimaryItem(container, position, item)
            if (item is CoreFragment) {
                mCurrentPrimaryItem = item as CoreFragment
            }
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return position.toString() + ""
        }
    }

    companion object {
        private val TAG: String? = "MainActivity"
        private const val TAB_HOME = 0
        private const val TAB_SHOW = TAB_HOME + 1
        private const val TAB_TICKET = TAB_SHOW + 1
        private const val TAB_MINE = TAB_TICKET + 1
        private const val TAB_SIZE = TAB_MINE + 1
    }
}
