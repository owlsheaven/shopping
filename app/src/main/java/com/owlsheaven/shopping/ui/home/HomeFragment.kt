package com.owlsheaven.shopping.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.owlsheaven.shopping.R
import com.owlsheaven.shopping.databinding.FragmentHomeBinding
import com.owlsheaven.shopping.ui.common.CommonViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()
    private val commonViewModel: CommonViewModel by viewModels()

    private lateinit var productAdapter: HomeProductAdapter
    private lateinit var bannerAdapter: HomeBannerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        val root: View = binding.root

        initProductAdapter()
        initBannerAdapter()
        initSwipeRefresh()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun initProductAdapter() {

        productAdapter = HomeProductAdapter(commonViewModel)

        binding.rvHomeProduct.layoutManager = GridLayoutManager(this.context, 2)
        binding.rvHomeProduct.adapter = productAdapter

        lifecycleScope.launchWhenCreated {
            productAdapter.loadStateFlow.collect {
                binding.swipeRefresh.isRefreshing = it.mediator?.refresh == LoadState.Loading
            }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.products.collectLatest {
                productAdapter.submitData(it)
            }
        }
    }

    private fun initBannerAdapter() {
        bannerAdapter = HomeBannerAdapter()
        binding.pgHomeBanner.adapter = bannerAdapter

        viewModel.banners.observe(viewLifecycleOwner) {
            bannerAdapter.submit(it)
        }
    }

    private fun initSwipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            productAdapter.refresh()
        }
    }

}