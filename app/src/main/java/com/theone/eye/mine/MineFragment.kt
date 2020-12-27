package com.theone.eye.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.theone.eye.databinding.FragmentMineBinding
import com.theone.eye.home.vm.MineViewModel
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

    private fun initView() {
        binding.loginTv.clickWithTrigger {
            AppRouter.build(AppRouteUrl.LOGIN_URL).go(mContext)
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