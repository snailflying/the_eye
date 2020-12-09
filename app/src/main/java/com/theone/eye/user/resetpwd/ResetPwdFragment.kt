package com.theone.eye.user.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.theone.eye.databinding.FragmentResetPasswordBinding
import com.theone.framework.base.BaseMvvmFragment

/**
 * @Author ZhiQiang
 * @Date 2020/12/8
 * @Description
 */
class ResetPwdFragment : BaseMvvmFragment<ResetPwdViewModel>() {
    lateinit var binding: FragmentResetPasswordBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentResetPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onCreateViewModel(): ResetPwdViewModel {
        return ViewModelProvider(this).get(ResetPwdViewModel::class.java)
    }

    companion object {

        fun getInstance(): RegisterFragment {
            return RegisterFragment()
        }
    }

}