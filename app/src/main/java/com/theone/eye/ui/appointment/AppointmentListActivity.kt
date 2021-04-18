package com.theone.eye.ui.appointment

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.chenenyu.router.annotation.Route
import com.theone.eye.databinding.ActivityApponitmentListBinding
import com.theone.eye.ui.appointment.adapter.AppointmentListAdapter
import com.theone.eye.ui.home.vm.AppointmentListViewModel
import com.theone.framework.base.BaseMvvmActivity
import com.theone.framework.ext.clickWithTrigger
import com.theone.framework.router.AppRouteUrl
import com.theone.framework.router.AppRouter
import com.theone.framework.widget.MultiStateView

@Route(value = [AppRouteUrl.APPOINTMENT_LIST_URL])
class AppointmentListActivity : BaseMvvmActivity<AppointmentListViewModel>() {

    lateinit var binding: ActivityApponitmentListBinding
    var adapter: AppointmentListAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApponitmentListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initData()
        addObserver()
    }

    private fun initView() {
        binding.titleBar.leftView.clickWithTrigger {
            onBackPressed()
        }
        binding.titleBar.rightView.clickWithTrigger {
            AppRouter.build(AppRouteUrl.APPOINTMENT_ADD_URL).go(this)
        }
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        binding.rvList.layoutManager = layoutManager
        (binding.rvList.itemAnimator as SimpleItemAnimator?)!!.supportsChangeAnimations = false
        adapter = AppointmentListAdapter() {
            AppRouter.build(AppRouteUrl.REPORT_URL).with(AppRouteUrl.REPORT_EXTRA, it?.id).go(this)
        }
        binding.rvList.adapter = adapter
    }

    private fun initData() {
        viewModel.getAppointment()
    }

    private fun addObserver() {
        viewModel.appointmentListLive.observe(this, {
            binding.multiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
            adapter?.setPosts(it)
        })
        viewModel.httpErrorLive.observe(this, {
            binding.multiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY

        })
    }

    override fun onCreateViewModel(): AppointmentListViewModel {
        return ViewModelProvider(this).get(AppointmentListViewModel::class.java)
    }
}