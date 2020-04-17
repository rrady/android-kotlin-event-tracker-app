package com.eventtracker.app.ui.host

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

import com.eventtracker.app.R
import com.eventtracker.app.databinding.FragmentHostDetailBinding
import com.eventtracker.app.ui.hostlist.HostListFragment
import com.eventtracker.app.wrappers.HostWrapper

class HostDetailFragment : Fragment() {
    private lateinit var binding: FragmentHostDetailBinding
    private lateinit var viewModel: HostDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_host_detail, container, false)
        var hostWrapper = arguments?.getParcelable<HostWrapper>(HOST_KEY)

        viewModel = ViewModelProvider(this).get(HostDetailViewModel::class.java)
        viewModel.host = hostWrapper?.host!!
        binding.viewModel = viewModel

        return binding.root
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