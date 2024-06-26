package com.hfad.engineeringcalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hfad.engineeringcalculator.databinding.FragmentCalculatorBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope

class CalculatorFragment : Fragment() {
    private var _binding: FragmentCalculatorBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CalculatorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCalculatorBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val dao = ECDatabase.getInstance(application).ecDao
        val factory = CalculatorViewModelFactory(dao)
        viewModel = ViewModelProvider(this, factory)[CalculatorViewModel::class.java]

        try {
            viewModel.addAll(CalculatorFragmentArgs.fromBundle(requireArguments()).number.toString())
        } catch (_: Exception) {}

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}