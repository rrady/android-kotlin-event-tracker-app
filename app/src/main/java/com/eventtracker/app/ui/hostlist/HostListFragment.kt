package com.eventtracker.app.ui.hostlist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import dagger.android.support.AndroidSupportInjection
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

import com.eventtracker.app.R
import com.eventtracker.app.databinding.FragmentHostListBinding
import com.eventtracker.app.ui.host.HostActivity
import com.eventtracker.app.ui.newhost.NewHostActivity
import com.eventtracker.app.wrappers.HostWrapper

class HostListFragment: Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentHostListBinding
    private val viewModel: HostListViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[HostListViewModel::class.java]
    }
    private var adapter = GroupAdapter<GroupieViewHolder>()

    override fun onAttach(activity: Activity) {
        AndroidSupportInjection.inject(this);
        super.onAttach(activity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter.setOnItemClickListener { item, view ->
            val hostItem = item as HostItem

            val intent = Intent(view.context, HostActivity::class.java)
            intent.putExtra(HOST_KEY, HostWrapper(hostItem.host))

            startActivity(intent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_host_list, container, false)
        binding.recyclerViewHost.adapter = adapter
        viewModel.initHosts()
        binding.viewModel = viewModel

        binding.searchViewHost.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                var hosts = binding!!.viewModel!!.searchHosts(newText)
                adapter.update(hosts.map { HostItem(it) })
                return false
            }
        })

        populateHosts()
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.nav_hostlist_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_new_host) {
            val intent = Intent(this.context, NewHostActivity::class.java)
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }

    private fun populateHosts() {
        adapter.addAll(viewModel.hosts.map { HostItem(it) })
    }

    companion object {
        const val HOST_KEY = "HOST_KEY"
    }
}