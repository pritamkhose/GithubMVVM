package com.pritam.githubmvvm.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.pritam.githubmvvm.R
import com.pritam.githubmvvm.databinding.FragmentFactListBinding
import com.pritam.githubmvvm.helpers.Injector
import com.pritam.githubmvvm.service.model.Item
import com.pritam.githubmvvm.service.model.UserSerachResponse
import com.pritam.githubmvvm.view.adapter.UserSerachAdapter
import com.pritam.githubmvvm.viewmodel.UserSerachListViewModel
import kotlinx.android.synthetic.main.fragment_fact_list.view.*


class UserSerachListFragment : Fragment() {

    private var aAdapter: UserSerachAdapter? = null
    private var binding: FragmentFactListBinding? = null
    private var aList = mutableListOf<Item>()
    private var rootView: View? = null
    private lateinit var viewModel: UserSerachListViewModel

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater, @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_fact_list, container, false)

        // Recycler Adapter
        aAdapter = UserSerachAdapter(aList)
        binding?.factList?.adapter = aAdapter
        showLoadingStatus()

        // Swipe refresh listener
        binding?.root?.simpleSwipeRefreshLayout?.setOnRefreshListener {
            // observe data from viewModel
            showLoadingStatus()
            binding?.isLoading = true
            observeViewModel(viewModel)
            rootView
        }

        return binding?.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UserSerachListViewModel::class.java)
        observeViewModel(viewModel)

    }

    fun observeViewModel(viewModel: UserSerachListViewModel) {
        // Update the list when the data changes
        viewModel.getFactListObservable(activity!!, Injector.provideCache(activity!!)).observe(this, Observer<UserSerachResponse> { fact ->
            hideLoading()
            if (fact != null) {
                //get title & rows from factResponse
                val title = fact.total_count
                (activity as AppCompatActivity).supportActionBar!!.title = title.toString()
                binding?.factList?.setItemViewCacheSize(fact.items.size)
                binding?.isLoading = false
                binding?.isRecyclerShowing = true

                val mutableList = fact.items.toMutableList()
                // clear list, add new items in list and refresh it using notifyDataSetChanged
                aList.clear()
                aList.addAll(mutableList)
                aAdapter?.notifyDataSetChanged()
            }
        })
    }



    /**
     * Hide SwipeRefreshLayout loader
     */
    private fun hideLoading() {
        rootView?.simpleSwipeRefreshLayout?.isRefreshing = false
    }

    /**
     * show loading fact status before network or database call
     */
    private fun showLoadingStatus() {
        binding?.loadingStatus = getString(R.string.loading_facts)
        binding?.isLoading = true
        binding?.isRecyclerShowing = false
    }

    /**
     * show network status is network is not available
     */
    private fun showNetworkError() {
        binding?.loadingStatus = getString(R.string.network_error)
    }

}
