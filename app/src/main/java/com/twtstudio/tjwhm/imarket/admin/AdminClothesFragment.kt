package com.twtstudio.tjwhm.imarket.admin

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.twtstudio.tjwhm.imarket.BaseBean
import com.twtstudio.tjwhm.imarket.BaseFragment
import com.twtstudio.tjwhm.imarket.ClothesBean
import com.twtstudio.tjwhm.imarket.R

class AdminClothesFragment : Fragment(), BaseFragment {
    var baseBean: BaseBean<List<ClothesBean>> = BaseBean(-1, "", mutableListOf())
    lateinit var rvAdmin: RecyclerView
    lateinit var adapter: AdminClothesAdapter
    val adminPresenter = AdminPresenter(this)

    companion object {
        lateinit var type: String
        fun newInstance(type: String): AdminClothesFragment {
            this.type = type
            val args = Bundle()
            args.putString("type", type)
            val fragment = AdminClothesFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_customer, container, false)
        rvAdmin = view.findViewById(R.id.rv_customer)
        val layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        rvAdmin.layoutManager = layoutManager
        adapter = AdminClothesAdapter(baseBean, context)
        rvAdmin.adapter = adapter
        return view
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        if (isVisibleToUser) {
            adminPresenter.getClothesList()
        }
    }

    fun setClothesData(baseBean: BaseBean<List<ClothesBean>>) {
        this.baseBean.error_code = baseBean.error_code
        this.baseBean.message = baseBean.message
        this.baseBean.data = baseBean.data
        adapter.notifyDataSetChanged()
    }
}