package com.ike.resto.ui.foodList

import androidx.lifecycle.ViewModel
import com.ike.resto.ui.base.ResourcesProvider
import com.ike.resto.ui.data.Food
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class FoodsListViewModel @Inject constructor(
    resourcesProvider: ResourcesProvider
) : ViewModel() {

    private val _data = MutableStateFlow<List<Food>?>(null)
    val data: StateFlow<List<Food>?> = _data
    private val foodsLiveData = resourcesProvider.getResource().getFoodList()

    fun getFoodList() {
        _data.value = foodsLiveData.value
    }

}
