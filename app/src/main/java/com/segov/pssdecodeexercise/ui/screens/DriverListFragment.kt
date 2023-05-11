package com.segov.pssdecodeexercise.ui.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.segov.pssdecodeexercise.data.DriverAndSuitability
import com.segov.pssdecodeexercise.databinding.FragmentDriverListBinding
import com.segov.pssdecodeexercise.ui.adapter.AdapterString
import com.segov.pssdecodeexercise.utils.getDriverAndShipmentObject
import com.segov.pssdecodeexercise.viewmodel.DriverListViewModel
import kotlinx.coroutines.launch

class DriverListFragment : Fragment() {

    private var _binding: FragmentDriverListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DriverListViewModel by viewModels()
    private val adapter: AdapterString by lazy { AdapterString(::onItemClick) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDriverListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    if (it.isLoading) {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.driverRecyclerview.visibility = View.GONE
                    } else {
                        binding.progressBar.visibility = View.GONE
                        binding.driverRecyclerview.visibility = View.VISIBLE
                    }
                    adapter.submitList(it.drivers)
                }
            }
        }

        loadList()
    }

    private fun initViews() {
        with (binding) {
            driverRecyclerview.adapter = adapter
            val itemDecor = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            driverRecyclerview.addItemDecoration(itemDecor)
        }
    }

    private fun onItemClick(name: String) {
        val maxShipment = viewModel.onDriverClick(name)
        Log.d("DriverListFragment", "OnItemClick: $name -> $maxShipment")
        val action = DriverListFragmentDirections.actionDriverListFragmentToDriverDetailFragment(
            DriverAndSuitability(name, maxShipment.first, maxShipment.second)
        )
        findNavController().navigate(action)
    }

    // Loads data from json and pass the object to viewModel's ui state
    private fun loadList() {
        val input = resources.getDriverAndShipmentObject()
        viewModel.loadList(input)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}