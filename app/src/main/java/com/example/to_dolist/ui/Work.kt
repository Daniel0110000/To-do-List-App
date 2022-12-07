package com.example.to_dolist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.to_dolist.databinding.FragmentWorkBinding
import com.example.to_dolist.ui.common.RecyclerViewIns
import com.example.to_dolist.viewModel.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Work : Fragment() {

    private var _binding: FragmentWorkBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TaskViewModel by viewModels()

    private lateinit var recyclerViewIns: RecyclerViewIns

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWorkBinding.inflate(inflater, container, false)

        viewModelInstances()

        return binding.root
    }

    private fun viewModelInstances(){
        viewModel.tasksByCategory("Trabajo").observe(viewLifecycleOwner) { task ->
            recyclerViewIns = context?.let {
                RecyclerViewIns(
                    binding.recyclerWork,
                    task,
                    it,
                    viewModel,
                    binding.noTasksToPerformLayout
                )
            }!!
            recyclerViewIns.initRecyclerView()
        }
    }

}