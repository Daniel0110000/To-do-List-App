package com.example.to_dolist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.to_dolist.databinding.FragmentImportantBinding
import com.example.to_dolist.ui.common.RecyclerViewIns
import com.example.to_dolist.viewModel.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Important : Fragment() {

    private var _binding: FragmentImportantBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TaskViewModel by viewModels()

    private lateinit var recyclerViewIns: RecyclerViewIns

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImportantBinding.inflate(inflater, container, false)

        viewModelInstances()

        return binding.root
    }

    private fun viewModelInstances(){
        viewModel.tasksByCategory("Importantes").observe(viewLifecycleOwner) { task ->
            recyclerViewIns = context?.let {
                RecyclerViewIns(
                    binding.recyclerImportant,
                    task,
                    it,
                    viewModel
                )
            }!!
            recyclerViewIns.initRecyclerView()
        }
    }

}