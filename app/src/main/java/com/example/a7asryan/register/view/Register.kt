package com.example.a7asryan.register.view

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.a7asryan.R
import com.example.a7asryan.databinding.FragmentRegisterBinding
import com.example.a7asryan.local.ConcreteLocal
import com.example.a7asryan.model.User
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
        var userName: String = ""
        var email: String = ""
        var password: String = ""
        var phoneNumber: String = ""
        binding.btnRegister.setOnClickListener {
            userName = binding.userName.editableText.toString().trim()
            email = binding.email.editableText.toString().trim()
            password = binding.password.editableText.toString().trim()
            phoneNumber = binding.phoneNumber.editableText.toString().trim()
            if (checkEmail(email) && checkPassword(password) &&
                checkPhoneNumber(phoneNumber) && checkUserName(userName)
            ) {
                binding.textErrorMessage.visibility = View.INVISIBLE
                registerViewModel.checkUserExistence(email, password)
            }
        }
        registerViewModel.isExistence.observe(viewLifecycleOwner) {
            it.let {
                if (!it) {
                    registerViewModel.insertUser(
                        setUserPOJO(
                            userName,
                            email,
                            password,
                            phoneNumber
                        )
                    )
                } else {
                    binding.textErrorMessage.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setUserPOJO(
        userName: String,
        email: String,
        password: String,
        phoneNumber: String
    ): User {
        return User(userName, email, password, phoneNumber)
    }

    private fun checkEmail(email: String): Boolean {
        val check: Boolean
        when {
            email.isEmpty() -> {
                binding.email.error = "You must Enter Email"
                binding.email.requestFocus()
                check = false
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                binding.email.error = "You must Enter Valid Email"
                binding.email.requestFocus()
                check = false
            }
            else -> {
                check = true
            }
        }
        return check
    }

    private fun checkPassword(password: String): Boolean {
        val check: Boolean
        when {
            password.isEmpty() -> {
                binding.password.error = "Password is Required"
                binding.password.requestFocus()
                check = false
            }
            password.length < 7 -> {
                binding.password.error = "Password must be at least 8 character"
                binding.password.requestFocus()
                check = false
            }
            else -> {
                check = true
            }
        }
        return check
    }

    private fun checkPhoneNumber(phoneNumber: String): Boolean {
        val check: Boolean
        when {
            phoneNumber.isEmpty() -> {
                binding.phoneNumber.error = "Phone number is Required"
                binding.phoneNumber.requestFocus()
                check = false
            }
            phoneNumber.matches(Regex("(201)[0-9]{9}")) -> {
                binding.phoneNumber.error = "Invalid phone number"
                binding.phoneNumber.requestFocus()
                check = false
            }
            else -> {
                check = true
            }
        }
        return check
    }

    private fun checkUserName(userName: String): Boolean {
        val check: Boolean
        when {
            userName.isEmpty() -> {
                binding.userName.error = "Username is Required"
                binding.userName.requestFocus()
                check = false
            }
            else -> {
                check = true
            }
        }
        return check
    }

}