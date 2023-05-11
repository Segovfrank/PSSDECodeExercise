package com.segov.pssdecodeexercise.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.segov.pssdecodeexercise.databinding.FragmentDriverDetailBinding

class DriverDetailFragment : Fragment() {

    private var _binding: FragmentDriverDetailBinding? = null
    private val binding get() = _binding!!

    private val args: DriverDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDriverDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        with (binding) {
            backArrow.setOnClickListener { findNavController().navigateUp() }
            driverName.text = args.driverAndShipment.name
            shipmentAddress.text = args.driverAndShipment.shipment
            suitability.text = args.driverAndShipment.suitability.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}