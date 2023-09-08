package com.angelika.lesson_53.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.angelika.lesson_53.R
import com.angelika.lesson_53.databinding.FragmentUesrBinding
import com.angelika.lesson_53.ui.adapters.UserAdapter

class UserFragment : Fragment() {

    private var _binding: FragmentUesrBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<UserViewModel>()
    private val userAdapter = UserAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUesrBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setUpListener()
        acceptData()
    }

    private fun initialize() {
        binding.rvUsers.adapter = userAdapter
    }

    private fun setUpListener() = with(binding) {
        btnTransition.setOnClickListener {
            findNavController().navigate(R.id.action_rainbowFragment_to_addFragment)
        }
    }

    private fun acceptData() {
        viewModel.userAddLiveData.observe(viewLifecycleOwner) {
            it.success?.let { user ->
                userAdapter.addUser(user)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}