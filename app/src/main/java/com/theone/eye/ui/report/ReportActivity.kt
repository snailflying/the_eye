package com.theone.eye.ui.report

import android.os.Bundle
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.RecyclerView
import com.chenenyu.router.annotation.Route
import com.drakeet.multitype.MultiTypeAdapter
import com.theone.eye.R
import com.theone.eye.ui.report.adapter.ContentOneBinder
import com.theone.eye.ui.report.adapter.ImageOneBinder
import com.theone.eye.ui.report.adapter.TitleBinder
import com.theone.eye.ui.report.vm.AppointmentListViewModel
import com.theone.framework.base.BaseMvvmActivity
import com.theone.framework.router.AppRouteUrl
import java.util.*

/**
 * @Author ZhiQiang
 * @Date 4/14/21
 * @Description
 */
@Route(value = [AppRouteUrl.REPORT_URL])
class ReportActivity : BaseMvvmActivity<AppointmentListViewModel>() {

    @VisibleForTesting
    internal lateinit var items: MutableList<Any>

    @VisibleForTesting
    internal lateinit var adapter: MultiTypeAdapter

    private class JsonData {

        private val post00 = ImageOneBinder.Data(R.drawable.img_00)
        private val post01 = ImageOneBinder.Data(R.drawable.img_01)
        private val post10 = ImageOneBinder.Data(R.drawable.img_10)
        private val post11 = ImageOneBinder.Data(R.drawable.img_11)

        var category0 = TitleBinder.Data("左眼")
        var category1 = TitleBinder.Data("右眼")
        var category2 = TitleBinder.Data("诊断结果")
        var content = ContentOneBinder.Data(
            "1、右眼未出现不明症状；\n" +
                    "2、左眼视力低下；\n" +
                    "3、诊断证明软组织受伤。\n" +
                    "\n"
        )
        var postArray = arrayOf(post00, post01, post10, post11)

        var postList: MutableList<ImageOneBinder.Data> = ArrayList()

        init {
            postList.add(post00)
            postList.add(post00)
            postList.add(post00)
            postList.add(post00)
            postList.add(post00)
            postList.add(post00)
        }

        companion object {
            private const val PREFIX = "这是一条长长的达到两行的标题文字"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        adapter = MultiTypeAdapter()
        adapter.register(TitleBinder())
        adapter.register(ContentOneBinder())

        adapter.register(ImageOneBinder())

        val recyclerView = findViewById<RecyclerView>(R.id.list)

        val layoutManager = GridLayoutManager(this, SPAN_COUNT)
        val spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val item = items[position]
                return if (item is ImageOneBinder.Data) 1 else SPAN_COUNT
            }
        }
        layoutManager.spanSizeLookup = spanSizeLookup
        recyclerView.layoutManager = layoutManager
        val space = resources.getDimensionPixelSize(R.dimen.margin_horizon_fixed_1)
        recyclerView.addItemDecoration(PostItemDecoration(space, spanSizeLookup))

        recyclerView.adapter = adapter

        val data = JsonData()
        items = ArrayList()
        /* You also could use Category as your CategoryItemContent directly */
        items.add(data.category0)
        items.add(data.postArray[0])
        items.add(data.postArray[1])
        items.add(data.postArray[2])
        items.add(data.postArray[3])
        items.add(data.postArray[0])
        items.add(data.postArray[1])
        items.add(data.category1)
        items.add(data.postArray[0])
        items.add(data.postArray[1])
        items.add(data.postArray[2])
        items.add(data.postArray[3])
        items.add(data.postArray[0])
        items.add(data.postArray[1])
        items.add(data.category2)
        items.add(data.content)
        adapter.items = items
        adapter.notifyDataSetChanged()
    }

    companion object {
        private const val SPAN_COUNT = 3
    }

    override fun onCreateViewModel(): AppointmentListViewModel {
        return ViewModelProvider(this).get(AppointmentListViewModel::class.java)
    }
}
