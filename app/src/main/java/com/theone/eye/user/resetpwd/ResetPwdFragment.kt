package com.theone.eye.user.login

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.hjq.bar.OnTitleBarListener
import com.theone.eye.base.entity.VerifyCodeReq
import com.theone.eye.base.entity.VerifyCodeType
import com.theone.eye.databinding.FragmentResetPasswordBinding
import com.theone.eye.user.resetpwd.entity.ResetPwdReq
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
class ResetPwdFragment : BaseMvvmFragment<ResetPwdViewModel>() {
    lateinit var binding: FragmentResetPasswordBinding

    private var mCountDownHelper: CountDownButtonHelper? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentResetPasswordBinding.inflate(inflater, container, false)
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
        mCountDownHelper = CountDownButtonHelper(binding.getVerifyCodeBtn, 60)
    }

    private fun initObserver() {
        viewModel.resetPwdLive.observe(viewLifecycleOwner, { registerRes ->
            if (!registerRes.errorMsg.isNullOrEmpty()) {
                ToastUtil.show(registerRes.errorMsg)
            } else {
                AppRouter.build(AppRouteUrl.LOGIN_URL).go(mContext)
            }
        })
    }

    private fun initListener() {
        binding.getVerifyCodeBtn.clickWithTrigger {
            mCountDownHelper?.start()
            viewModel.getVerifyCode(VerifyCodeReq().apply {
                phoneNumber = binding.phoneNumberEt.getTextString()
                fieldType = VerifyCodeType.RESET_PWD
            })
        }
        binding.resetPasswordBtn.clickWithTrigger {
            viewModel.resetPassword(ResetPwdReq().apply {
                phoneNumber = binding.phoneNumberEt.getTextString()
                smsVerifyCode = binding.verifyCodeEt.getTextString()
                pwd = binding.passwordEt.getTextString()
            })

        }
    }

    private fun initEdit() {
        binding.phoneNumberEt.doOnTextChanged { text, start, before, count ->
            verifyInputFields()
        }
        binding.verifyCodeEt.doOnTextChanged { text, start, before, count ->
            verifyInputFields()
        }
        binding.confirmPasswordEt.doOnTextChanged { text, start, before, count ->
            verifyInputFields()
        }

    }

    private fun verifyInputFields() {
        val phoneNumber: String? = binding.phoneNumberEt.getTextString()
        val verifyCode: String? = binding.verifyCodeEt.getTextString()
        val password: String? = binding.passwordEt.getTextString()
        val confirmPassword: String? = binding.confirmPasswordEt.getTextString()
        binding.resetPasswordBtn.isEnabled = !TextUtils.isEmpty(phoneNumber)
                && !TextUtils.isEmpty(password)
                && !TextUtils.isEmpty(verifyCode)
                && !TextUtils.isEmpty(confirmPassword)
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

    override fun onCreateViewModel(): ResetPwdViewModel {
        return ViewModelProvider(this).get(ResetPwdViewModel::class.java)
    }

    companion object {

        fun getInstance(): RegisterFragment {
            return RegisterFragment()
        }
    }

}