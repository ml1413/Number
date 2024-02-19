package com.i.number.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.i.number.model.ModelNumberInfo
import com.i.number.reqest.MyResource
import com.i.number.reqest.requestInfo.RepositoryNumInfo
import com.i.number.reqest.request_random_info.RepositoryRandomInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NumberInfoViewModel @Inject constructor(
    private val repositoryNumInfo: RepositoryNumInfo,
    private val repositoryRandomInfo: RepositoryRandomInfo
) : ViewModel() {
    private val _info = MutableLiveData<MyResource<ModelNumberInfo>>()
    val info: LiveData<MyResource<ModelNumberInfo>> = _info
    fun setInfoFromRecycler(modelNumberInfo: ModelNumberInfo) {
        _info.value = MyResource.Success(data = modelNumberInfo)
    }

    fun getInfoNumber(number: String, loading: (Boolean) -> Unit) {
        loading(true)
        viewModelScope.launch {
            _info.value = repositoryNumInfo.getNumberInfo(number = number)
            loading(false)
        }
    }

    fun getRandomInfo(loading: (Boolean) -> Unit) {
        loading(true)
        viewModelScope.launch {
            _info.value = repositoryRandomInfo.getRandomInfo()
            loading(false)
        }
    }
}