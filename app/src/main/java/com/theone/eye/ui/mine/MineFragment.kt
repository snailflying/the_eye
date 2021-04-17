package com.theone.eye.ui.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.theone.eye.R
import com.theone.eye.base.user.User
import com.theone.eye.databinding.FragmentMineBinding
import com.theone.eye.ui.home.vm.MineViewModel
import com.theone.framework.base.BaseMvvmFragment
import com.theone.framework.ext.clickWithTrigger
import com.theone.framework.router.AppRouteUrl
import com.theone.framework.router.AppRouter

/**
 * @Author ZhiQiang
 * @Date 2020/12/27
 * @Description
 */
class MineFragment : BaseMvvmFragment<MineViewModel>() {
    lateinit var binding: FragmentMineBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMineBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onFragmentResume() {
        super.onFragmentResume()
        refreshUi()
    }

    private fun refreshUi() {
        if (User.currentUser.isLogin()) {
            binding.loginTv.text = User.currentUser.name
        } else {
            binding.loginTv.text = getString(R.string.text_login)
            binding.loginTv.clickWithTrigger {
                AppRouter.build(AppRouteUrl.LOGIN_URL).go(mContext)
            }
        }
    }

    private fun initView() {
        binding.logoutTv.clickWithTrigger {
            User.currentUser.logout()
            refreshUi()
        }
    }

    override fun onCreateViewModel(): MineViewModel {
        return ViewModelProvider(this).get(MineViewModel::class.java)
    }

    companion object {
        val instance: MineFragment
            get() = MineFragment()
    }
}