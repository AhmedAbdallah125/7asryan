package com.example.a7asryan.login.view

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.a7asryan.R
import com.example.a7asryan.databinding.FragmentLoginScreenBinding
import com.example.a7asryan.local.ConcreteLocal
import com.example.a7asryan.login.view.viewmodel.FactoryLoginViewModel
import com.example.a7asryan.login.view.viewmodel.LoginViewModel
import com.example.a7asryan.remote.RetrofitHelper
import com.example.a7asryan.repository.Repository


class LoginScreen : Fragment() {
    private var _binding: FragmentLoginScreenBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels {
        FactoryLoginViewModel(Repository(ConcreteLocal(requireContext()), RetrofitHelper))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener {
            binding.txterror.visibility = View.INVISIBLE
            val email = binding.textNameEdit.editableText.toString().trim()
            val password = binding.textPassEdit.editableText.toString().trim()
            if (checkEmail(email) && checkPassword(password)) {
                // request from and check DB
                viewModel.checkUser(email, password)
                viewModel.checkResponse.observe(viewLifecycleOwner) {
                    it.let {
                        if (it) {
                            //Go to Home
                            // init SharedPreferences
                            Toast.makeText(requireContext(), "hello Boys", Toast.LENGTH_SHORT).show()
                            findNavController().navigate(R.id.action_loginScreen_to_navigation_home)
                        } else {
                            binding.txterror.visibility = View.VISIBLE
                            Toast.makeText(
                                requireContext(),
                                "You should ensure password and email",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
        binding.btnRegisterText.setOnClickListener {
            findNavController().navigate(R.id.action_loginScreen_to_register)
        }
    }

    private fun checkEmail(email: String): Boolean {
        val check: Boolean
        when {
            email.isEmpty() -> {
                binding.textNameEdit.error = "You must Enter Email"
                binding.textNameEdit.requestFocus()
                check = false
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                binding.textNameEdit.error = "You must Enter Valid Email"
                binding.textNameEdit.requestFocus()
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
                binding.textPassEdit.error = "Password is Required"
                binding.textPassEdit.requestFocus()
                check = false
            }
            password.length < 7 -> {
                binding.textPassEdit.error = "Password must be at least 8 character"
                binding.textPassEdit.requestFocus()
                check = false
            }
            else -> {
                check = true
            }
        }
        return check
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}