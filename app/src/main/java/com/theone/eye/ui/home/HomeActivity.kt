package com.theone.eye.ui.home

import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.chenenyu.router.annotation.Route
import com.themone.core.base.impl.CoreFragment
import com.themone.core.util.StatusBarUtil
import com.theone.eye.R
import com.theone.eye.databinding.ActivityHomeBinding
import com.theone.eye.ui.home.adapter.BottomSmartTabAdapter
import com.theone.eye.ui.home.entity.BottomBarEn
import com.theone.eye.ui.mine.MineFragment
import com.theone.eye.ui.msg.MsgFragment
import com.theone.eye.ui.shop.ShopFragment
import com.theone.framework.base.BaseActivity
import com.theone.framework.router.AppRouteUrl
import com.theone.framework.widget.smarttablayout.SmartTabLayout

@Route(value = [AppRouteUrl.ROUTE_HOME_URL])
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

    override fun isFitsSystemWindows(): Boolean {
        return false
    }

    private fun setStatusBar() {
        StatusBarUtil.compat(this, false)
    }

    private fun setBottomBar() {
        val result = mutableListOf<BottomBarEn>()
        val tabs = resources.getStringArray(R.array.home_tab)
        tabs.forEachIndexed { index, title ->
            when (index) {
                TAB_HOME -> {
                    val item = BottomBarEn(title, R.drawable.tab_home)
                    result.add(item)
                }
                TAB_MSG -> {
                    val item = BottomBarEn(title, R.drawable.tab_msg)
                    result.add(item)
                }
                TAB_SHOP -> {
                    val item = BottomBarEn(title, R.drawable.tab_shop)
                    result.add(item)
                }
                TAB_MINE -> {
                    val item = BottomBarEn(title, R.drawable.tab_mine)
                    result.add(item)
                }
            }
        }

        val smartTabAdapter = BottomSmartTabAdapter(R.layout.item_bottombar_text_layout)
        smartTabAdapter.setData(result)

        binding.smartTabSTL.setOnTabClickListener(object : SmartTabLayout.OnTabClickListener {
            override fun onTabClicked(position: Int) {
                binding.viewpager.setCurrentItem(position, true)
            }
        })
        binding.smartTabSTL.setTabAdapter(smartTabAdapter, true)
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
                TAB_HOME -> HomeFragment.instance
                TAB_MSG -> MsgFragment.newInstance()
                TAB_SHOP -> ShopFragment.newInstance()
                TAB_MINE -> MineFragment.instance
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
        private const val TAB_MSG = TAB_HOME + 1
        private const val TAB_SHOP = TAB_MSG + 1
        private const val TAB_MINE = TAB_SHOP + 1
        private const val TAB_SIZE = TAB_MINE + 1
    }
}
