package com.example.to_dolist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.to_dolist.databinding.FragmentAllBinding
import com.example.to_dolist.ui.common.RecyclerViewIns
import com.example.to_dolist.viewModel.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class All() : Fragment() {

    private var _binding: FragmentAllBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TaskViewModel by viewModels()

    private lateinit var recyclerViewIns: RecyclerViewIns

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllBinding.inflate(inflater, container, false)

        viewModelInstances()

        return binding.root
    }

    private fun viewModelInstances(){
        viewModel.allTasks.observe(viewLifecycleOwner) { task ->
            recyclerViewIns = context?.let {
                RecyclerViewIns(
                    binding.recyclerAll,
                    task,
                    it,
                    viewModel
                )
            }!!
            recyclerViewIns.initRecyclerView()
        }
    }

}