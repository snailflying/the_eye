package com.theone.eye.ui.appointment

import android.os.Bundle
import android.view.View
import com.chenenyu.router.annotation.Route
import com.theone.eye.databinding.ActivityApponitmentDetailBinding
import com.theone.eye.ui.appointment.entity.AppointmentListRes
import com.theone.framework.base.BaseActivity
import com.theone.framework.ext.clickWithTrigger
import com.theone.framework.router.AppRouteUrl

@Route(value = [AppRouteUrl.APPOINTMENT_DETAIL_URL], interceptors = [AppRouteUrl.LOGIN_ROUTE_INTERCEPTOR])
class AppointmentDetailActivity : BaseActivity() {

    lateinit var binding: ActivityApponitmentDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApponitmentDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initData()
    }

    private fun initView() {
        binding.titleBar.leftView.clickWithTrigger { onBackPressed() }
    }

    private fun initData() {
        val data: AppointmentListRes? = intent.getParcelableExtra(AppRouteUrl.APPOINTMENT_DETAIL_EXTRA)
        if (data != null) {
            binding.nameTv.text = data.appointName
            if (data.sex.isNullOrEmpty()) {
                binding.sexLl.visibility = View.GONE
            }
            binding.sexTv.text = data.sex
            binding.ageTv.text = data.userAge
            binding.phoneTv.text = data.appointPhone
            binding.addressTv.text = data.appointAddr
            binding.appointmentTimeTv.text = data.appointmentTime
        }
    }


}