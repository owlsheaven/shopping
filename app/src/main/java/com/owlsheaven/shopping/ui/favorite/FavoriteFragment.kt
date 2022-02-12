package com.owlsheaven.shopping.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.owlsheaven.shopping.R
import com.owlsheaven.shopping.databinding.FragmentFavoriteBinding
import com.owlsheaven.shopping.ui.common.CommonViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding

    private val viewModel: FavoriteViewModel by viewModels()
    private val commonViewModel: CommonViewModel by viewModels()

    private lateinit var adapter: FavoriteProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container,false)
        val root: View = binding.root

        initAdapter()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun initAdapter() {
        adapter = FavoriteProductAdapter(commonViewModel)

        binding.rvFavoriteProduct.layoutManager = GridLayoutManager(this.context, 2)
        binding.rvFavoriteProduct.adapter = adapter

        lifecycleScope.launchWhenCreated {
            viewModel.products.collectLatest {
                adapter.submitData(it)
            }
        }
    }
}