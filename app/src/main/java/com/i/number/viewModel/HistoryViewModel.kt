package com.i.number.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.i.db.HistoryRepositoryDB
import com.i.db.NumberEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val repositoryDB: HistoryRepositoryDB
) : ViewModel() {
    private val _historyList = MutableLiveData<List<NumberEntity>>()
    val historyList: LiveData<List<NumberEntity>> = _historyList

    private val observer = Observer<List<NumberEntity>> { listNumberEntity ->
        _historyList.postValue(listNumberEntity)
    }

    init {
        repositoryDB.getAll().observeForever(observer)
    }

    fun getAll() {
        repositoryDB.getAll()
    }

    fun addModelNumberInfo(numberEntity: NumberEntity) {
        viewModelScope.launch {
            repositoryDB.add(numberEntity = numberEntity)
        }
    }

    override fun onCleared() {
        repositoryDB.getAll().removeObserver(observer)
        super.onCleared()
    }

}