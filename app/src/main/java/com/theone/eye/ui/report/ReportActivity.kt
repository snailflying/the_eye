package com.theone.eye.ui.report

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.chenenyu.router.annotation.Route
import com.shownow.shownow.base.constant.HttpStatusCode
import com.theone.eye.R
import com.theone.eye.databinding.ActivityReportBinding
import com.theone.eye.ui.report.adapter.ImageOneBinder
import com.theone.eye.ui.report.vm.ReportViewModel
import com.theone.framework.base.BaseMvvmActivity
import com.theone.framework.ext.clickWithTrigger
import com.theone.framework.router.AppRouteUrl
import com.theone.framework.widget.MultiStateView

/**
 * @Author ZhiQiang
 * @Date 4/14/21
 * @Description
 */
@Route(value = [AppRouteUrl.REPORT_URL], interceptors = [AppRouteUrl.LOGIN_ROUTE_INTERCEPTOR])
class ReportActivity : BaseMvvmActivity<ReportViewModel>() {
    private lateinit var binding: ActivityReportBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initData()
        addObserver()
    }

    private fun initView() {
        binding.titleBar.leftView.clickWithTrigger {
            onBackPressed()
        }
    }

    private fun addObserver() {
        viewModel.httpResultLive.observe(this) {
            if (it.code == HttpStatusCode.SUCCESS) {
                binding.multiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
                initRecycleView()
            } else {
                binding.multiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY

            }
        }
    }

    private fun initData() {
        val appointId = intent.getStringExtra(AppRouteUrl.REPORT_EXTRA)
        viewModel.getReportById(appointId)
    }

    private fun initRecycleView() {
        val layoutManager = GridLayoutManager(this, SPAN_COUNT)
        val spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val item = viewModel.itemsFloor[position]
                return if (item is ImageOneBinder.Data) 1 else SPAN_COUNT
            }
        }
        layoutManager.spanSizeLookup = spanSizeLookup
        binding.list.layoutManager = layoutManager
        val space = resources.getDimensionPixelSize(R.dimen.margin_horizon_fixed_1)
        binding.list.addItemDecoration(PostItemDecoration(space, spanSizeLookup))

        viewModel.initRecyclerView(binding.list)
    }

    companion object {
        private const val SPAN_COUNT = 3
    }

    override fun onCreateViewModel(): ReportViewModel {
        return ViewModelProvider(this).get(ReportViewModel::class.java)
    }
}
