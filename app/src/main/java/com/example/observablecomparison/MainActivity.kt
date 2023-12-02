package com.example.observablecomparison

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.observablecomparison.databinding.ActivityMainBinding
import com.example.observablecomparison.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    val viewModel by viewModels<MainViewModel>()

    //lateinit var viewModel:MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        //viewModel= ViewModelProvider(this)[MainViewModel::class.java]
        initializeListeners()
        initializeObservables()
    }

    fun initializeListeners()
    {
        binding.liveDataBtn.setOnClickListener {
            viewModel.triggerLiveData()
        }
        binding.stateFlowBtn.setOnClickListener {
            viewModel.triggerStateFlow()
        }

        binding.flowBtn.setOnClickListener {
            lifecycleScope.launch {
                viewModel.triggerFlow().collect {
                    binding.flowTv.text = it
                    Toast.makeText(this@MainActivity, "Flow", Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.sharedFlowBtn.setOnClickListener {
            viewModel.triggerSharedFlow()
        }
    }
    fun initializeObservables()
    {
        viewModel.liveDlataText.observe(this){
            binding.liveDataTv.text=it
            Toast.makeText(this, "Live Data", Toast.LENGTH_SHORT).show()
        }

        lifecycleScope.launch {
            viewModel.stateFlowText.collect {
                binding.stateFlowTv.text = it
                Toast.makeText(this@MainActivity, "State Flow", Toast.LENGTH_SHORT).show()

            }
        }
        lifecycleScope.launch {
            viewModel.sharedFlowText.collect {
                binding.sharedFlowTv.text = it
                Toast.makeText(this@MainActivity, "Shared Flow", Toast.LENGTH_SHORT).show()

            }
        }

    }
}