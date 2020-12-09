package com.theone.eye.user.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.theone.eye.databinding.FragmentLoginBinding
import com.theone.eye.databinding.FragmentRegisterBinding
import com.theone.framework.base.BaseMvvmFragment

/**
 * @Author ZhiQiang
 * @Date 2020/12/8
 * @Description
 */
class RegisterFragment : BaseMvvmFragment<RegisterViewModel>() {
    lateinit var binding:FragmentRegisterBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }



    override fun onCreateViewModel(): RegisterViewModel {
        return ViewModelProvider(this).get(RegisterViewModel::class.java)
    }
    companion object {

        fun getInstance(): RegisterFragment {
            return RegisterFragment()
        }
    }

}