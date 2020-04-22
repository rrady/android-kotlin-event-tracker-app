package com.eventtracker.app.ui.profile

import javax.inject.Inject
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.AndroidSupportInjection
import com.google.firebase.auth.FirebaseAuth
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

import com.eventtracker.app.R
import com.eventtracker.app.databinding.FragmentProfileBinding
import com.eventtracker.app.ui.host.EventItem
import com.eventtracker.app.ui.hostlist.HostItem

class ProfileFragment: Fragment() {
    @Inject
    lateinit var firebaseAuth: FirebaseAuth
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentProfileBinding
    private var hostsAdapter = GroupAdapter<GroupieViewHolder>()
    private var eventsAdapter = GroupAdapter<GroupieViewHolder>()
    private val viewModel: ProfileViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[ProfileViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.recyclerViewMyHosts.adapter = hostsAdapter
        binding.recyclerViewFollowingEvents.adapter = eventsAdapter

//        viewModel.hosts.observe(this.viewLifecycleOwner, Observer { list ->
//            hostsAdapter.update(list.map { HostItem(it) })
//        })
//
//        viewModel.events.observe(this.viewLifecycleOwner, Observer { list ->
//            hostsAdapter.update(list.map { EventItem(it) })
//        })

        viewModel.initAccount()
        viewModel.initHosts()
        viewModel.initEvents()
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.nav_profile_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_sign_out -> {
                firebaseAuth.signOut()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    companion object {
        @JvmStatic
        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }
}