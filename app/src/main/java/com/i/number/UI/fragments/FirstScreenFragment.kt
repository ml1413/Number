package com.i.number.UI.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.i.Maper.mapNumberEntityToModelNumberInfo
import com.i.number.R
import com.i.number.databinding.FragmentFirstScreenBinding
import com.i.number.recycler.RecyclerViewHistory
import com.i.number.viewModel.HistoryViewModel
import com.i.number.viewModel.NumberInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FirstScreenFragment : Fragment() {
    private lateinit var binding: FragmentFirstScreenBinding
    private val numberInfoViewModel by activityViewModels<NumberInfoViewModel>()
    private val viewModelHistory by activityViewModels<HistoryViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("TAG1", "FirstScreenFragment: $this ")
        binding = FragmentFirstScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun clickOnItemRecyclerView() {
        viewModelHistory.historyList.observe(viewLifecycleOwner) { listNumberEntity ->
            val adapter = RecyclerViewHistory(listHistory = listNumberEntity,
                onItemClickListener = { numberEntity ->
                    val modelNumberInfo = mapNumberEntityToModelNumberInfo(numberEntity)
                    numberInfoViewModel.setInfoFromRecycler(modelNumberInfo = modelNumberInfo)
                    openInfoScreenFragment(addElementInHistory = false)
                })
            binding.recyclerView.adapter = adapter

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickOnGetInfoFromNumber()
        clickOnRandomInfoNumber()
        clickOnItemRecyclerView()
    }

    private fun clickOnRandomInfoNumber() {
        binding.btRandom.setOnClickListener {
            numberInfoViewModel.getRandomInfo { isOnProgressBar ->
                binding.progressBar.isInvisible = !isOnProgressBar
                if (!isOnProgressBar) {
                    openInfoScreenFragment(addElementInHistory = true)
                }
            }
        }
    }

    private fun clickOnGetInfoFromNumber() {
        binding.btGet.setOnClickListener {
            val textFromEditText = binding.editText.text.toString()
            if (textFromEditText.isDigitsOnly() && textFromEditText.isNotBlank()) {
                numberInfoViewModel.getInfoNumber(textFromEditText) { isOnProgressBar ->
                    binding.progressBar.isInvisible = !isOnProgressBar
                    if (!isOnProgressBar) {
                        openInfoScreenFragment(addElementInHistory = true)
                    }
                }
            } else {
                Toast.makeText(requireContext(), "text is blank", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun openInfoScreenFragment(addElementInHistory: Boolean) {
        val infoScreenFragment = InfoScreenFragment.newInstance(addInHistory = addElementInHistory)
        parentFragmentManager.beginTransaction()
            .add(R.id.container_main, infoScreenFragment)
            .addToBackStack("")
            .commit()
    }


}