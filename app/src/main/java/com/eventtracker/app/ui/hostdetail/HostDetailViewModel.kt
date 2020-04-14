package com.eventtracker.app.ui.hostdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HostDetailViewModel(): ViewModel() {
    private val _name = MutableLiveData<String>()
    private val _description = MutableLiveData<String>()
    private val _avatarUri = MutableLiveData<String>()

    val name: LiveData<String>
        get() = _name

    val description: LiveData<String>
        get() = _description

    val avatarUri: LiveData<String>
        get() = _avatarUri

    init {
        _name.value = "Control club"
        _description.value = "Control club description"
        _avatarUri.value = "https://www.gstatic.com/webp/gallery/4.sm.jpg"
    }
}