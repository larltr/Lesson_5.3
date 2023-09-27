package com.angelika.lesson_53.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.angelika.lesson_53.databinding.FragmentAddBinding
import com.angelika.lesson_53.models.User

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<UserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListener()
    }

    private fun setUpListener() = with(binding) {
        textReady.setOnClickListener {
            val name = etName.text.toString().trim()
            val age = etAge.text.toString().trim()
            val user = User(name = name, age = age.toInt())

            binding.progress.isVisible = true
            binding.etAge.isVisible = false
            binding.etName.isVisible = false
            binding.textReady.isVisible = false

            viewModel.addUser(user)
            Toast.makeText(requireContext(), "данные успешно переданы", Toast.LENGTH_SHORT).show()

            android.os.Handler().postDelayed(
                {
                    findNavController().navigateUp()
                }, 3000
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}