package com.eventtracker.app.ui.hostdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HostDetailViewModel(): ViewModel() {
    private val _name = MutableLiveData<String>()
    private val _description = MutableLiveData<String>()
    private val _info = MutableLiveData<String>()
    private val _site = MutableLiveData<String>()
    private val _phone = MutableLiveData<String>()
    private val _email = MutableLiveData<String>()
    private val _avatarUri = MutableLiveData<String>()

    val name: LiveData<String>
        get() = _name

    val description: LiveData<String>
        get() = _description

    val info: LiveData<String>
        get() = _info

    val site: LiveData<String>
        get() = _site

    val phone: LiveData<String>
        get() = _phone

    val email: LiveData<String>
        get() = _email

    val avatarUri: LiveData<String>
        get() = _avatarUri

    init {
        _name.value = "Control club"
        _description.value = "Control club description"
        _info.value = "Control club info"
        _site.value = "www.control.ro"
        _phone.value = "0722222222"
        _email.value = "control@control.ro"
        _avatarUri.value = "https://www.gstatic.com/webp/gallery/4.sm.jpg"
    }
}