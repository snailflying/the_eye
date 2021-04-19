package com.theone.eye.ui.appointment

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.chenenyu.router.annotation.Route
import com.themone.core.util.StatusBarUtil
import com.theone.eye.R
import com.theone.eye.databinding.ActivityApponitmentAddBinding
import com.theone.eye.ui.appointment.entity.AppointmentAddReq
import com.theone.eye.ui.home.vm.AppointmentAddViewModel
import com.theone.framework.base.BaseMvvmActivity
import com.theone.framework.ext.clickWithTrigger
import com.theone.framework.ext.getTextString
import com.theone.framework.ext.hideKeyboard
import com.theone.framework.router.AppRouteUrl
import com.theone.framework.widget.toast.ToastUtil
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


@Route(value = [AppRouteUrl.APPOINTMENT_ADD_URL],interceptors = [AppRouteUrl.LOGIN_ROUTE_INTERCEPTOR])
class AppointmentAddActivity : BaseMvvmActivity<AppointmentAddViewModel>() {

    lateinit var binding: ActivityApponitmentAddBinding

    private val appointmentReq: AppointmentAddReq = AppointmentAddReq()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApponitmentAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        addObserver()
    }

    private fun initView() {
        binding.titleBar.leftView.clickWithTrigger { onBackPressed() }
        setTimePicker()
        binding.submitBtnTv.clickWithTrigger {
            appointmentReq.setName(binding.nameEt.getTextString())
            appointmentReq.setAge(binding.ageEt.getTextString()?.toIntOrNull())
            appointmentReq.setPhone(binding.phoneEt.getTextString())
            appointmentReq.setAddresss(binding.addressEt.getTextString())
            appointmentReq.setTime(binding.appointmentTimeTv.text?.toString())
            viewModel.submitAppointment(appointmentReq)
        }
    }

    private fun setTimePicker() {
        fun getTime(date: Date): String {
            val fmt: DateFormat = SimpleDateFormat("yyyy/MM/dd hh:mm:ss")
            return fmt.format(date)
        }

        val pvTime = TimePickerBuilder(
            this
        ) { date: Date, view ->
            binding.appointmentTimeTv.text = getTime(date)
        }.build()
        binding.appointmentTimeTv.clickWithTrigger {
            it.hideKeyboard()
            pvTime.show()
        }
    }

    private fun addObserver() {
        viewModel.resultData.observe(this, { result ->
            if (result) {
                onBackPressed()
            } else {
                ToastUtil.show(getString(R.string.submit_error))
            }
        })
    }
    override fun isFitsSystemWindows(): Boolean {
        return false
    }
    override fun onCreateViewModel(): AppointmentAddViewModel {
        return ViewModelProvider(this).get(AppointmentAddViewModel::class.java)
    }
}