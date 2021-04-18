package com.theone.eye.ui.report

import android.os.Bundle
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.chenenyu.router.annotation.Route
import com.theone.eye.R
import com.theone.eye.databinding.ActivityReportBinding
import com.theone.eye.ui.report.adapter.ImageOneBinder
import com.theone.eye.ui.report.vm.ReportViewModel
import com.theone.framework.base.BaseMvvmActivity
import com.theone.framework.router.AppRouteUrl

/**
 * @Author ZhiQiang
 * @Date 4/14/21
 * @Description
 */
@Route(value = [AppRouteUrl.REPORT_URL])
class ReportActivity : BaseMvvmActivity<ReportViewModel>() {

    @VisibleForTesting
    internal lateinit var items: MutableList<Any>

    private lateinit var binding: ActivityReportBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportBinding.inflate(layoutInflater)
        setContentView(binding.root)



        initRecycleView()
        initData()

    }

    private fun initData() {
        val appointId = intent.getStringExtra(AppRouteUrl.REPORT_EXTRA)
        viewModel.getReportById(appointId)
    }

    private fun initRecycleView() {
        val layoutManager = GridLayoutManager(this, SPAN_COUNT)
        val spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val item = items[position]
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
