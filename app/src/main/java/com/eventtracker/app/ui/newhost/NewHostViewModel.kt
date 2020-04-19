package com.eventtracker.app.ui.newhost

import android.graphics.Bitmap
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import javax.inject.Inject

import com.eventtracker.domain.models.Host
import com.eventtracker.domain.usecases.CreateHostUseCase
import java.util.*


class NewHostViewModel @Inject constructor(private val useCase: CreateHostUseCase): ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main

    val name = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    val info = MutableLiveData<String>()
    val site = MutableLiveData<String>()
    val phone = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    var avatarUri: String? = null

    private var avatar: Bitmap? = null

    fun onSubmit() = launch {
        validateInput()

        val host = Host(UUID.randomUUID().toString(), name.value!!, description.value!!, info.value!!, site.value!!, phone.value!!, email.value!!, avatarUri!!)

        useCase.execute(host)
    }

    fun validateInput() {
        if (name.value == null || name.value.isNullOrBlank()) {
            throw Exception("Name cannot be empty.")
        }

        if (description.value == null || description.value.isNullOrBlank()) {
            throw Exception("Description cannot be empty.")
        }

        if (info.value == null || info.value.isNullOrBlank()) {
            throw Exception("Info cannot be empty.")
        }

        if (site.value == null || site.value.isNullOrBlank()) {
            throw Exception("Site cannot be empty.")
        }

        if (phone.value == null || phone.value.isNullOrBlank()) {
            throw Exception("Phone number cannot be empty.")
        }

        if (email.value == null || email.value.isNullOrBlank() || !Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
            throw Exception("E-mail address cannot be empty or invalid.")
        }
    }
}