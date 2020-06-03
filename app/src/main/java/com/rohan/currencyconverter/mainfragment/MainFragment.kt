package com.rohan.currencyconverter.mainfragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProviders
import com.rohan.currencyconverter.R
import com.rohan.currencyconverter.databinding.FragmentMainfragmentBinding


class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentMainfragmentBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = this.viewLifecycleOwner
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.viewModel = this.viewModel

        val spinner = binding.currencySpinner1
        spinner.onItemSelectedListener = SpinnerTools(viewModel.curr1)
        val spinner2 = binding.currencySpinner2
        spinner2.onItemSelectedListener = SpinnerTools(viewModel.curr2)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.currency,
            android.R.layout.simple_spinner_item
        )
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
                spinner2.adapter = adapter
            }

        binding.convertButton.setOnClickListener {
            Log.i("MainFragment",binding.editTextNumberDecimal.text.toString())
            binding.editTextNumberDecimal.text.toString().let { it ->
                viewModel.input.value = it.toDouble()
                Log.i("MainFragment","Input number is ${viewModel.input}")
                Log.i("MainFragment","curr1 : ${viewModel.curr1.value} , ${viewModel.curr2.value} , ${viewModel.result.value.toString()}")
                val curr1 = viewModel.curr1.value
                val curr2 = viewModel.curr2.value
                val factor:Double? = viewModel.result.value.let { currency ->
                    val f1: String? = currency?.rates?.get(curr1.toString())
                    val f2: String? = currency?.rates?.get(curr2.toString())
                    f2?.toDouble()?.div(f1?.toDouble()!!)
                }
                val result = it.toDouble() * factor!!

                binding.convertedView.text = String.format("%.2f",result)

            }

        }

        return binding.root
    }
}

class SpinnerTools(val ldata: MutableLiveData<String>) : AdapterView.OnItemSelectedListener {
    override fun onNothingSelected(parent: AdapterView<*>?) {
        ldata.value = parent!!.getItemAtPosition(0).toString()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        ldata.value = parent!!.getItemAtPosition(position).toString()
    }
}
