package com.theone.eye.ui.appointment

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.chenenyu.router.annotation.Route
import com.theone.eye.databinding.ActivityApponitmentListBinding
import com.theone.eye.ui.report.vm.AppointmentListViewModel
import com.theone.framework.base.BaseMvvmActivity
import com.theone.framework.router.AppRouteUrl

@Route(value = [AppRouteUrl.APPOINTMENT_URL])
class AppointmentListActivity : BaseMvvmActivity<AppointmentListViewModel>() {

    lateinit var binding: ActivityApponitmentListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApponitmentListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateViewModel(): AppointmentListViewModel {
        return ViewModelProvider(this).get(AppointmentListViewModel::class.java)
    }
}