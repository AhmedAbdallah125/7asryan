package com.example.a7asryan.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.a7asryan.R
import com.example.a7asryan.model.isLogin
import kotlinx.coroutines.*

class SplashFragment : Fragment() {
    private val parentJob = Job()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val coroutineScope = CoroutineScope(Dispatchers.Main + parentJob)
        coroutineScope.launch {
            delay(4000)
            if (isLogin(requireContext())) {
                findNavController().navigate(R.id.action_splashFragment_to_navigation_home)
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_loginScreen)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        parentJob.cancel()
    }

}