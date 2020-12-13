package com.theone.eye.user.login

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import cn.magicwindow.core.ext.finishWithAnim
import com.hjq.bar.OnTitleBarListener
import com.theone.eye.base.user.User
import com.theone.eye.databinding.FragmentLoginBinding
import com.theone.framework.base.BaseMvvmFragment
import com.theone.framework.ext.clickWithTrigger
import com.theone.framework.ext.getTextString
import com.theone.framework.router.AppRouteUrl
import com.theone.framework.router.AppRouter

/**
 * @Author ZhiQiang
 * @Date 2020/12/8
 * @Description
 */
class LoginFragment : BaseMvvmFragment<LoginViewModel>() {
    lateinit var binding: FragmentLoginBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        initTitleBar()
        initEdit()
        initListener()
        initObserver()
    }

    private fun initObserver() {
        viewModel.loginLive.observe(viewLifecycleOwner, {
            User.currentUser.login(it)
            activity?.finishWithAnim()
        })
    }

    private fun initListener() {
        binding.loginBtn.clickWithTrigger {
            val phoneNumber: String? = binding.phoneNumberEt.getTextString()
            val password: String? = binding.verifyCodeEt.getTextString()
            viewModel.loginByVerifyCode(phoneNumber, password)
        }
        binding.forgetPasswordTv.clickWithTrigger {
            AppRouter.build(AppRouteUrl.RESET_PWD_URL).go(this)
        }
        binding.registerTv.clickWithTrigger {
            AppRouter.build(AppRouteUrl.REGISTER_URL).go(this)
        }
    }

    private fun initTitleBar() {
        binding.titleBar.setOnTitleBarListener(object : OnTitleBarListener {
            override fun onLeftClick(v: View) {
                onBackPressed()
            }

            override fun onTitleClick(v: View) {}
            override fun onRightClick(v: View) {}
        })
    }

    private fun initEdit() {
        binding.phoneNumberEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                verifyPhoneAndCode()
            }

            override fun afterTextChanged(s: Editable) {}
        })
        binding.verifyCodeEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                verifyPhoneAndCode()
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

    private fun verifyPhoneAndCode() {
        val phoneNumber: String? = binding.phoneNumberEt.getTextString()
        val password: String? = binding.verifyCodeEt.getTextString()
        binding.loginBtn.isEnabled = !TextUtils.isEmpty(phoneNumber) && !TextUtils.isEmpty(password)
    }

    override fun onCreateViewModel(): LoginViewModel {
        return ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    companion object {

        fun getInstance(): RegisterFragment {
            return RegisterFragment()
        }
    }

}