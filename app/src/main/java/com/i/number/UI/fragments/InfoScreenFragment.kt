package com.i.number.UI.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.i.Maper.mapModelNumberToNumberEntity
import com.i.number.databinding.FragmentInfoScreenBinding
import com.i.number.model.ModelNumberInfo
import com.i.number.reqest.MyResource
import com.i.number.viewModel.HistoryViewModel
import com.i.number.viewModel.NumberInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class InfoScreenFragment : Fragment() {
    companion object {
        private const val KEY_ADD_HISTORY = "history"
        fun newInstance(addInHistory: Boolean) =
            InfoScreenFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(KEY_ADD_HISTORY, addInHistory)
                }
            }
    }

    private var addInHistory: Boolean = false
    private lateinit var binding: FragmentInfoScreenBinding
    private val numberInfoViewModel by activityViewModels<NumberInfoViewModel>()
    private val viewModelHistory by activityViewModels<HistoryViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("TAG1", "onCreateView: $this")
        arguments?.let { addInHistory = it.getBoolean(KEY_ADD_HISTORY) }
        binding = FragmentInfoScreenBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        numberInfoViewModel.info.observe(viewLifecycleOwner) { myResource ->
            when (myResource) {
                is MyResource.Success -> {
                    val modelNumber = myResource.data
                    if (addInHistory) {
                        arguments = Bundle().apply { putBoolean(KEY_ADD_HISTORY, false) }
                        setValueInViewModelHistory(modelNumber)
                    }
                    setDataInView(modelNumber = modelNumber)
                }

                is MyResource.Error -> myResource.message

            }
        }
    }

    private fun setValueInViewModelHistory(modelNumber: ModelNumberInfo) {
        viewModelHistory.addModelNumberInfo(
            numberEntity = mapModelNumberToNumberEntity(modelNumber = modelNumber)
        )
    }

    private fun setDataInView(modelNumber: ModelNumberInfo) {
        binding.tvNumber.text = modelNumber.number
        getTextDelay(modelNumber.text, 30) { binding.tvInfo.append(it) }
    }

    private fun getTextDelay(string: String, duration: Long, result: (String) -> Unit) {
        lifecycleScope.launch {
            string.forEach {
                delay(duration)
                result(it.toString())
            }
        }
    }

}
