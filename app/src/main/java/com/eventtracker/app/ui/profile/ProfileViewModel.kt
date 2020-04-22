package com.eventtracker.app.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import com.google.firebase.auth.FirebaseAuth

import com.eventtracker.domain.models.Event
import com.eventtracker.domain.models.Host
import com.eventtracker.domain.usecases.GetEventsUseCase
import com.eventtracker.domain.usecases.GetFollowedHostsUseCase

class ProfileViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val hostsUseCase: GetFollowedHostsUseCase,
    private val eventsUseCase: GetEventsUseCase): ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main

    private val _profilePictureUri = MutableLiveData<String>()
    private val _displayName = MutableLiveData<String>()
    private val _givenName = MutableLiveData<String>()
    private val _familyName = MutableLiveData<String>()
    private var _hosts = MutableLiveData<List<Host>>()
    private var _events = MutableLiveData<List<Event>>()

    val profilePictureUri: LiveData<String>
        get() = _profilePictureUri

    val displayName: LiveData<String>
        get() = _displayName

    val givenName: LiveData<String>
        get() = _givenName

    val familyName: LiveData<String>
        get() = _familyName

    val hosts: LiveData<List<Host>>
        get() = _hosts

    val events: LiveData<List<Event>>
        get() = _events

    fun initAccount() {
        _profilePictureUri.value = firebaseAuth.currentUser!!.photoUrl.toString()
        _displayName.value = firebaseAuth.currentUser!!.displayName
        _givenName.value = "given"
        _familyName.value = "family"
    }

    fun initHosts() = launch {
        var newHosts = hostsUseCase.execute()
        _hosts.value = newHosts
    }

    fun initEvents() = launch {
//        var newEvents = eventsUseCase.execute()
//        _hosts.value = newEvents
    }
}