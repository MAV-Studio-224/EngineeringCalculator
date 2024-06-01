package com.hfad.engineeringcalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.hfad.engineeringcalculator.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val dao = ECDatabase.getInstance(application).ecDao
        val factory = HistoryViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, factory)[HistoryViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = HistoryAdapter {item: ECEntity ->
            viewModel.deleteOneItem(item)
        }
        binding.expressions.adapter = adapter

        viewModel.allHistory.observe(viewLifecycleOwner) {
            viewModel.list = it
            adapter.submitList(it)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}