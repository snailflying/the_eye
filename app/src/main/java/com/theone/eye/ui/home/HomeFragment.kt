package com.theone.eye.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.theone.eye.databinding.FragmentHomeMainBinding
import com.theone.eye.ui.home.vm.HomeViewModel
import com.theone.framework.base.BaseMvvmFragment

class HomeFragment : BaseMvvmFragment<HomeViewModel>() {

    lateinit var binding: FragmentHomeMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    private fun initData() {
        initRecyclerView()
        viewModel.getData()
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        binding.homeRv.layoutManager = layoutManager
        (binding.homeRv.itemAnimator as SimpleItemAnimator?)!!.supportsChangeAnimations = false
        viewModel.initRecyclerView(binding.homeRv)
    }

    override fun onCreateViewModel(): HomeViewModel {
        return ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    companion object {
        val instance: HomeFragment
            get() = HomeFragment()
    }
}
