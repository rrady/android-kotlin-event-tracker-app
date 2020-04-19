package com.eventtracker.app.ui.hostlist

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.eventtracker.app.R
import com.eventtracker.app.databinding.FragmentHostListBinding
import com.eventtracker.app.ui.host.HostActivity
import com.eventtracker.app.ui.newhost.NewHostActivity
import com.eventtracker.app.wrappers.HostWrapper
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient

import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class HostListFragment: Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth


    private lateinit var binding: FragmentHostListBinding
    private val viewModel: HostListViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[HostListViewModel::class.java]
    }
    private var adapter = GroupAdapter<GroupieViewHolder>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)

        firebaseAuth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this.context!!, gso);

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
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_host_list, container, false)
        binding.recyclerViewHost.adapter = adapter
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.hosts.observe(this.viewLifecycleOwner, Observer { list ->
            adapter.update(list.map { HostItem(it) })
        })

        binding.searchViewHost.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val hosts = binding.viewModel!!.searchHosts(newText)
                adapter.update(hosts.map { HostItem(it) })
                return false
            }
        })

        initHosts()
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.nav_hostlist_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_new_host -> {
                val intent = Intent(this.context, NewHostActivity::class.java)
                startActivity(intent)
            }
            R.id.menu_sign_in -> {
                signIn()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun signIn() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {

                Log.w(TAG, "signInWithCredential:success")
            } else {
                Log.w(TAG, "signInWithCredential:failure")
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
                Log.w(TAG,"Google sign in failed:(")
            }
        }
    }

    private fun initHosts() {
        try {
            viewModel.initHosts()
        } catch (e: Exception) {
            Toast.makeText(binding.root.context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val HOST_KEY = "HOST_KEY"
        private const val RC_SIGN_IN = 9001
    }
}