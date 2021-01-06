package com.theone.eye.user.login

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import cn.magicwindow.core.ext.finishWithAnim
import com.hjq.bar.OnTitleBarListener
import com.theone.eye.databinding.FragmentRegisterBinding
import com.theone.eye.user.register.entity.RegisterReq
import com.theone.eye.utils.CountDownButtonHelper
import com.theone.framework.base.BaseMvvmFragment
import com.theone.framework.ext.clickWithTrigger
import com.theone.framework.ext.getTextString
import com.theone.framework.router.AppRouteUrl
import com.theone.framework.router.AppRouter
import com.theone.framework.widget.toast.ToastUtil

/**
 * @Author ZhiQiang
 * @Date 2020/12/8
 * @Description
 */
class RegisterFragment : BaseMvvmFragment<RegisterViewModel>() {
    lateinit var binding: FragmentRegisterBinding
    private var mCountDownHelper: CountDownButtonHelper? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
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
        mCountDownHelper = CountDownButtonHelper(binding.getVerifyCodeTv, 60)
    }

    private fun initObserver() {
        viewModel.registerLive.observe(viewLifecycleOwner, { registerRes ->
            if (!registerRes.errorMsg.isNullOrEmpty()) {
                ToastUtil.show(registerRes.errorMsg)
            } else {
                AppRouter.build(AppRouteUrl.LOGIN_URL).go(mContext)
                activity?.finishWithAnim()
            }
        })
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

    private fun initListener() {
        binding.otherLoginTv.clickWithTrigger {
            AppRouter.build(AppRouteUrl.LOGIN_URL).go(this)
        }
        binding.getVerifyCodeTv.clickWithTrigger {
            mCountDownHelper?.start()
            viewModel.getVerifyCode(binding.phoneNumberEt.getTextString() ?: "")
        }
        binding.registerBtn.clickWithTrigger {
            val registerReq = RegisterReq().apply {
                phoneNumber = binding.phoneNumberEt.getTextString()
                pwd = binding.passwordEt.getTextString()
                nickName = binding.nickNameEt.getTextString()
                smsVerifyCode = binding.verifyCodeEt.getTextString()
            }
            viewModel.register(registerReq)
        }
    }

    private fun initEdit() {
        binding.phoneNumberEt.doOnTextChanged { text, start, before, count ->
            verify()
        }
        binding.verifyCodeEt.doOnTextChanged { text, start, before, count ->
            verify()
        }
        binding.nickNameEt.doOnTextChanged { text, start, before, count ->
            verify()
        }
        binding.passwordEt.doOnTextChanged { text, start, before, count ->
            verify()
        }
        binding.confirmPasswordEt.doOnTextChanged { text, start, before, count ->
            verify()
        }
    }

    private fun verify() {
        val phoneNumber: String? = binding.phoneNumberEt.getTextString()
        val verifyCode: String? = binding.verifyCodeEt.getTextString()
        val nickName: String? = binding.nickNameEt.getTextString()
        val password: String? = binding.passwordEt.getTextString()
        val confirmPassword: String? = binding.confirmPasswordEt.getTextString()
        binding.registerBtn.isEnabled = (!TextUtils.isEmpty(phoneNumber)
                && !TextUtils.isEmpty(verifyCode)
                && !TextUtils.isEmpty(nickName)
                && !TextUtils.isEmpty(password)
                && !TextUtils.isEmpty(confirmPassword))
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