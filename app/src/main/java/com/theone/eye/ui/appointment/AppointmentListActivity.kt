package com.theone.eye.ui.appointment

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.chenenyu.router.annotation.Route
import com.theone.eye.databinding.ActivityApponitmentListBinding
import com.theone.eye.ui.appointment.adapter.AppointmentListAdapter
import com.theone.eye.ui.appointment.entity.AppointmentListRes
import com.theone.eye.ui.home.vm.AppointmentListViewModel
import com.theone.framework.base.BaseMvvmActivity
import com.theone.framework.ext.clickWithTrigger
import com.theone.framework.router.AppRouteUrl
import com.theone.framework.router.AppRouter
import com.theone.framework.widget.MultiStateView

@Route(value = [AppRouteUrl.APPOINTMENT_LIST_URL], interceptors = [AppRouteUrl.LOGIN_ROUTE_INTERCEPTOR])
class AppointmentListActivity : BaseMvvmActivity<AppointmentListViewModel>() {

    lateinit var binding: ActivityApponitmentListBinding
    var adapter: AppointmentListAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApponitmentListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
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
        adapter = AppointmentListAdapter(object : AppointmentListAdapter.OnClickCallback {
            override fun onClick(data: AppointmentListRes?) {
                if (data?.isStatusDone() == true) {
                    AppRouter.build(AppRouteUrl.REPORT_URL).with(AppRouteUrl.REPORT_EXTRA, data.id)
                        .go(this@AppointmentListActivity)
                } else {
                    AppRouter.build(AppRouteUrl.APPOINTMENT_DETAIL_URL).with(AppRouteUrl.APPOINTMENT_DETAIL_EXTRA, data)
                        .go(this@AppointmentListActivity)
                }

            }

        })
        binding.rvList.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
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

    override fun isFitsSystemWindows(): Boolean {
        return false
    }

    override fun onCreateViewModel(): AppointmentListViewModel {
        return ViewModelProvider(this).get(AppointmentListViewModel::class.java)
    }
}