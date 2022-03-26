package com.example.a7asryan.register.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.a7asryan.R
import com.example.a7asryan.databinding.FragmentRegisterBinding
import com.example.a7asryan.local.ConcreteLocal
import com.example.a7asryan.model.User
import com.example.a7asryan.register.viewmodel.ErrorType
import com.example.a7asryan.register.viewmodel.RegisterResult
import com.example.a7asryan.register.viewmodel.RegisterViewModel
import com.example.a7asryan.register.viewmodel.RegisterViewModelFactory
import com.example.a7asryan.repository.Repository
import com.google.android.material.textfield.TextInputEditText

class Register : Fragment() {

    private val registerViewModel: RegisterViewModel by viewModels {
        RegisterViewModelFactory(Repository(ConcreteLocal(requireContext())))
    }
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnRegister.setOnClickListener {
            binding.textErrorMessage.visibility = View.INVISIBLE
            val userName = binding.userName.getTrimmedText()
            val email = binding.email.getTrimmedText()
            val password = binding.password.getTrimmedText()
            val phoneNumber = binding.phoneNumber.getTrimmedText()
            registerViewModel.registerUser(email, password, userName, phoneNumber)
            registerViewModel.registerResult.observe(viewLifecycleOwner) { result ->
                when (result) {
                    is RegisterResult.InvalidData -> {
                        when (result.error) {
                            ErrorType.EmailError -> {
                                binding.email.error = "Invalid Email"
                                binding.email.requestFocus()
                            }
                            ErrorType.PasswordError -> {
                                binding.password.error = "Invalid password"
                                binding.password.requestFocus()
                            }
                            ErrorType.PhoneNumberError -> {
                                binding.phoneNumber.error = "Invalid password"
                                binding.phoneNumber.requestFocus()
                            }
                            ErrorType.UserNameError -> {
                                binding.userName.error = "Enter user name"
                                binding.userName.requestFocus()
                            }
                        }
                    }
                    RegisterResult.RegisterError -> binding.textErrorMessage.visibility =
                        View.VISIBLE
                    RegisterResult.RegisterSuccessful -> binding.textErrorMessage.visibility =
                        View.INVISIBLE
                }
            }
        }

        binding.imageBack.setOnClickListener {
            findNavController().navigate(R.id.action_register_to_loginScreen)
        }
        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_register_to_navigation_home)
        }
    }
}

fun TextInputEditText.getTrimmedText(): String {
    return this.editableText.toString().trim()
}