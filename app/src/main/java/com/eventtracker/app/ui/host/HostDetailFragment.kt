package com.eventtracker.app.ui.host

import android.content.Intent
import javax.inject.Inject
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.AndroidSupportInjection

import com.eventtracker.app.R
import com.eventtracker.app.databinding.FragmentHostDetailBinding
import com.eventtracker.app.ui.MainActivity
import com.eventtracker.app.wrappers.HostWrapper

class HostDetailFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding: FragmentHostDetailBinding
    private val viewModel: HostDetailViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[HostDetailViewModel::class.java]
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_host_detail, container, false)

        var hostWrapper = arguments?.getParcelable<HostWrapper>(HOST_KEY)
        viewModel.host = hostWrapper?.host!!
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.nav_host_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_delete_host -> {
                viewModel.delete()
                val intent = Intent(this.context, MainActivity::class.java)
                startActivity(intent)
                activity?.supportFragmentManager?.popBackStack()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val HOST_KEY = "HOST_KEY"

        @JvmStatic
        fun newInstance(hostWrapper: HostWrapper): HostDetailFragment {
            val instance = HostDetailFragment()

            val args = Bundle()
            args.putParcelable(HOST_KEY, hostWrapper)
            instance.arguments = args

            return instance
        }
    }
}