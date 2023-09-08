package com.angelika.lesson_53.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.angelika.lesson_53.models.User

class UserViewModel : ViewModel() {

    private val _userAddLiveData = MutableLiveData(UserUiState<User>())
    val userAddLiveData: LiveData<UserUiState<User>> = _userAddLiveData

    fun addUser(user: User) {
        android.os.Handler().postDelayed(
            {
                val newValue = userAddLiveData.value?.copy(isLoading = false, success = user)
                _userAddLiveData.value = newValue
            },
            2000
        )
    }
}

data class UserUiState<T>(
    val isLoading: Boolean = true,
    val error: String? = null,
    val success: T? = null
)