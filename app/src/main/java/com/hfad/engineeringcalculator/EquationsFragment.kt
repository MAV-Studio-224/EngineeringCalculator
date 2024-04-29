package com.hfad.engineeringcalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.hfad.engineeringcalculator.databinding.FragmentEquationsBinding

class EquationsFragment : Fragment() {
    var _binding: FragmentEquationsBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEquationsBinding.inflate(inflater, container, false)
        val view = binding.root
        val viewModel = ViewModelProvider(this)[EquationsFragmentViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        if (binding.switch1.isChecked) {
            viewModel.type = "square"
            binding.a.visibility = View.VISIBLE
            binding.xSquarePlus.visibility = View.VISIBLE
        } else {
            viewModel.type = "linear"
            binding.a.visibility = View.GONE
            binding.xSquarePlus.visibility = View.GONE
        }

        binding.switch1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                viewModel.type = "square"
                binding.a.visibility = View.VISIBLE
                binding.xSquarePlus.visibility = View.VISIBLE
                binding.b.text.clear()
                binding.c.text.clear()
                binding.a.text.clear()
                viewModel.getX()
            } else {
                viewModel.type = "linear"
                binding.a.visibility = View.GONE
                binding.xSquarePlus.visibility = View.GONE
                binding.b.text.clear()
                binding.c.text.clear()
                binding.a.text.clear()
                viewModel.getX()
            }
        }

        viewModel.a.observe(viewLifecycleOwner) { viewModel.getX() }
        viewModel.b.observe(viewLifecycleOwner) { viewModel.getX() }
        viewModel.c.observe(viewLifecycleOwner) { viewModel.getX() }
        viewModel.x.observe(viewLifecycleOwner) { newValue ->
            binding.xEquals.text = newValue.toString()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}