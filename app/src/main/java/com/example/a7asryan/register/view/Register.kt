package com.example.a7asryan.register.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.a7asryan.databinding.FragmentRegisterBinding
import com.example.a7asryan.local.ConcreteLocal
import com.example.a7asryan.model.User
import com.example.a7asryan.register.viewmodel.ErrorType
import com.example.a7asryan.register.viewmodel.RegisterResult
import com.example.a7asryan.register.viewmodel.RegisterViewModel
import com.example.a7asryan.register.viewmodel.RegisterViewModelFactory
import com.example.a7asryan.repository.Repository

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
            val userName = binding.userName.editableText.toString().trim()
            val email = binding.email.editableText.toString().trim()
            val password = binding.password.editableText.toString().trim()
            val phoneNumber = binding.phoneNumber.editableText.toString().trim()
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
    }
}