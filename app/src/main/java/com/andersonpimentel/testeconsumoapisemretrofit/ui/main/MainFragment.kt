package com.andersonpimentel.testeconsumoapisemretrofit.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.andersonpimentel.testeconsumoapisemretrofit.R
import com.andersonpimentel.testeconsumoapisemretrofit.data.api.GetApiData
import com.andersonpimentel.testeconsumoapisemretrofit.data.model.ListCurrencies
import com.andersonpimentel.testeconsumoapisemretrofit.data.model.ListQuotes
import com.andersonpimentel.testeconsumoapisemretrofit.data.repository.Repository


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private var getApiData = GetApiData.getInstance()
    private var repository = Repository(getApiData)
    private lateinit var spinner: Spinner
    private lateinit var spinner2: Spinner
    private lateinit var editText: EditText
    private lateinit var button: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        initializeViewModel()

        spinner = view.findViewById(R.id.spinner)
        spinner2 = view.findViewById(R.id.spinner2)
        editText = view.findViewById(R.id.editText)
        button = view.findViewById(R.id.button)

        setupObservers()
        setupListeners()

        return view
    }

    private fun setupListeners() {
        button.setOnClickListener {
            viewModel.getQuotesList()
        }
    }

    private fun callConvertCurrency(listQuotes: ListQuotes){
        val value = editText.text.toString().toDouble()
        val convertFrom = spinner.selectedItem.toString()
        Log.d("Spinner", convertFrom)
        val convertTo = spinner2.selectedItem.toString()
        val convertedValue = viewModel.convertCurrency(convertFrom, convertTo, value, listQuotes)
        Log.d("Converted", convertedValue.toString())
    }

    private fun initializeViewModel(){
        viewModel = ViewModelProvider(
            this,
            MainViewModel.MainViewModelFactory(repository)
        ).get(MainViewModel::class.java)
    }

    private fun populateSpinners(list: ListCurrencies){
        val arrayList = arrayListOf<String>()
        list.currencies.keys.forEach { key ->
            arrayList.add(key)
        }
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, arrayList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner2.adapter = adapter
    }

    private fun setupObservers(){

        viewModel.listQuotesLiveData.observe(viewLifecycleOwner, Observer{
            callConvertCurrency(it)
        })

        viewModel.listCurrenciesLiveData.observe(viewLifecycleOwner, Observer {
            populateSpinners(it)
        })
    }
}