package com.hfad.engineeringcalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.hfad.engineeringcalculator.databinding.FragmentEquationsBinding

class EquationsFragment : Fragment() {
    var _binding: FragmentEquationsBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentEquationsBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val dao = ECDatabase.getInstance(application).ecDao
        val factory = EquationsViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, factory)[EquationsFragmentViewModel::class.java]

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
            } else {
                viewModel.type = "linear"
                binding.a.visibility = View.GONE
                binding.xSquarePlus.visibility = View.GONE
                binding.b.text.clear()
                binding.c.text.clear()
                binding.a.text.clear()
            }
        }

        viewModel.x.observe(viewLifecycleOwner) { newValue ->
            binding.xEquals.text = newValue.toString()
        }
        viewModel.roots.observe(viewLifecycleOwner) { newValue ->
            when (newValue) {
                2 -> {
                    binding.paste0.visibility = View.GONE
                    binding.paste1.visibility = View.VISIBLE
                    binding.paste2.visibility = View.VISIBLE
                }
                1 -> {
                    binding.paste0.visibility = View.VISIBLE
                    binding.paste1.visibility = View.GONE
                    binding.paste2.visibility = View.GONE
                }
                else -> {
                    binding.paste0.visibility = View.GONE
                    binding.paste1.visibility = View.GONE
                    binding.paste2.visibility = View.GONE
                }
            }
        }

        binding.paste0.setOnClickListener {
            val action = EquationsFragmentDirections.actionEquationsFragmentToCalculatorFragment(
                viewModel.x0.toFloat()
            )
            view.findNavController().navigate(action)
        }
        binding.paste1.setOnClickListener {
            val action = EquationsFragmentDirections.actionEquationsFragmentToCalculatorFragment(
                viewModel.x1.toFloat()
            )
            view.findNavController().navigate(action)
        }
        binding.paste2.setOnClickListener {
            val action = EquationsFragmentDirections.actionEquationsFragmentToCalculatorFragment(
                viewModel.x2.toFloat()
            )
            view.findNavController().navigate(action)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}