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
import com.example.a7asryan.login.view.viewmodel.LoginError
import com.example.a7asryan.login.view.viewmodel.LoginResult
import com.example.a7asryan.login.view.viewmodel.LoginViewModel
import com.example.a7asryan.repository.Repository


class LoginScreen : Fragment() {
    private var _binding: FragmentLoginScreenBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels {
        FactoryLoginViewModel(Repository(ConcreteLocal(requireContext())))
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
            viewModel.login(email, password)
            viewModel.loginResponse.observe(viewLifecycleOwner) { resultLogin ->
                when (resultLogin) {
                    is LoginResult.InvalidResult -> {
                        when (resultLogin.loginError) {
                            LoginError.EmailError -> {
                                binding.textNameEdit.error = "Invalid Email"
                                binding.textNameEdit.requestFocus()
                            }
                            LoginError.PasswordError -> {
                                binding.textPassEdit.error = "Invalid Password"
                                binding.textPassEdit.requestFocus()
                            }
                        }
                    }
                    LoginResult.LoginFailure -> {
                        binding.txterror.visibility = View.VISIBLE
                        Toast.makeText(
                            requireContext(),
                            "You should ensure password and email",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    LoginResult.LoginSuccessful -> {
                        //Go to Home
                        // init SharedPreferences
                        findNavController().navigate(R.id.action_loginScreen_to_navigation_home)
                    }
                }

                binding.btnRegisterText.setOnClickListener {
                    findNavController().navigate(R.id.action_loginScreen_to_register)
                }


            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}