package com.twtstudio.tjwhm.imarket.customer


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
import com.twtstudio.tjwhm.imarket.FoodBean
import com.twtstudio.tjwhm.imarket.R

class CustomerFoodFragment : Fragment(), BaseFragment {

    var baseBean: BaseBean<List<FoodBean>> = BaseBean(-1, "", mutableListOf())
    lateinit var rvCustomer: RecyclerView
    lateinit var adapter: CustomerFoodAdapter
    val customerPresenter = CustomerPresenter(this)

    companion object {
        lateinit var type: String

        fun newInstance(type: String): CustomerFoodFragment {
            this.type = type
            val args = Bundle()
            args.putString("type", type)
            val fragment = CustomerFoodFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_customer, container, false)
        rvCustomer = view.findViewById(R.id.rv_customer)
        val layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        rvCustomer.layoutManager = layoutManager
        adapter = CustomerFoodAdapter(baseBean, context)
        rvCustomer.adapter = adapter
        return view
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        if (isVisibleToUser) {
            customerPresenter.getFoodList()
        }
    }

    fun setFoodData(baseBean: BaseBean<List<FoodBean>>) {
        this.baseBean.message = baseBean.message
        this.baseBean.error_code = baseBean.error_code
        this.baseBean.data = baseBean.data
        adapter.notifyDataSetChanged()
    }
}