package com.soict.hoangviet.firebase.ui.view.impl

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.soict.hoangviet.firebase.R
import com.soict.hoangviet.firebase.adapter.HomeUserChatsAdapter
import com.soict.hoangviet.firebase.adapter.RecyclerViewAdapter
import com.soict.hoangviet.firebase.data.network.response.HomeResponse
import com.soict.hoangviet.firebase.ui.presenter.HomePresenter
import com.soict.hoangviet.firebase.ui.view.HomeView
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : BaseFragment(), HomeView, RecyclerViewAdapter.OnItemClickListener {
    override val mLayoutRes: Int
        get() = R.layout.fragment_home

    @Inject
    lateinit var mPresenter: HomePresenter

    companion object {
        fun getInstance(): HomeFragment {
            val homeFragment = HomeFragment()
            val bundle = Bundle()
            homeFragment.arguments = bundle
            return homeFragment
        }
    }

    private var mHomeUserChatsAdapter: HomeUserChatsAdapter? = null

    override fun initView() {
        mPresenter.onAttach(this)
        getAllChatUsers()
    }

    private fun getAllChatUsers() {
        mPresenter.getAllChatUsers()
    }

    override fun initListener() {

    }

    override fun showAllChatUsers(mListUserChat: ArrayList<HomeResponse>) {
        context?.let {
            mHomeUserChatsAdapter = HomeUserChatsAdapter(it)
            recycler_view_user.setAdapter(mHomeUserChatsAdapter!!)
            recycler_view_user.setLoadingMoreListener {

            }
            recycler_view_user.setOnRefreshListener {

            }
            recycler_view_user.setOnItemClickListener(this)
            mHomeUserChatsAdapter?.addModels(mListUserChat, false)
            recycler_view_user.setLinearLayoutManager()
            recycler_view_user.disableRefreshing()
        }
    }

    override fun notifyChange(position: Int) {
        mHomeUserChatsAdapter?.notifyItemChanged(position)
    }

    override fun onItemClick(parent: ViewGroup, viewType: Int, view: View, position: Int?) {

    }
}