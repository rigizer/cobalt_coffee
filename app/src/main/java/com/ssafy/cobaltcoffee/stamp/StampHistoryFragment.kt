package com.ssafy.cobaltcoffee.stamp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ssafy.cobaltcoffee.R
import com.ssafy.cobaltcoffee.adapter.CurrentOrderAdapter
import com.ssafy.cobaltcoffee.adapter.OrderDetailAdapter
import com.ssafy.cobaltcoffee.adapter.StampCardAdapter
import com.ssafy.cobaltcoffee.adapter.StampHistoryAdapter
import com.ssafy.cobaltcoffee.databinding.FragmentStampCardBinding
import com.ssafy.cobaltcoffee.databinding.FragmentStampHistoryBinding
import com.ssafy.cobaltcoffee.dto.LatestOrder
import com.ssafy.cobaltcoffee.dto.Order
import com.ssafy.cobaltcoffee.dto.User
import com.ssafy.cobaltcoffee.dto.UserLevel
import com.ssafy.cobaltcoffee.home.OrderFragment
import com.ssafy.cobaltcoffee.repository.OrderRepository
import com.ssafy.cobaltcoffee.repository.UserRepository
import com.ssafy.cobaltcoffee.response.OrderDetailResponse
import com.ssafy.cobaltcoffee.util.CommonUtils
import com.ssafy.cobaltcoffee.util.RetrofitCallback
import com.ssafy.cobaltcoffee.viewmodel.UserViewModel
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

private const val TAG = "StampHistoryFragment_코발트"
class StampHistoryFragment : Fragment() {
    private lateinit var binding: FragmentStampHistoryBinding
    private lateinit var stampActivity: StampActivity

    private lateinit var stampHistoryAdapter: StampHistoryAdapter
    private var orderList: MutableList<Pair<LatestOrder,Int>> = mutableListOf()
    private var stampList: MutableList<Int> = mutableListOf()

    private val userViewModel: UserViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        stampActivity = context as StampActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStampHistoryBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){

        getAllOrder()

        binding.apply {
            stampHistoryAdapter = StampHistoryAdapter(orderList,requireContext())

            stampHistoryRv.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                adapter = stampHistoryAdapter
                //원래의 목록위치로 돌아오게함
                adapter!!.stateRestorationPolicy =
                    RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

                //구분선 추가
                val decoration = DividerItemDecoration(requireContext(), VERTICAL)
                addItemDecoration(decoration)
            }
        }


    }

    //전체 주문내역 가져오기 : Retrofit
    private fun getAllOrder() {
        OrderRepository.get()
            .getAllOrder(userViewModel.currentUser.id, AllOrderListCallback())
    }

    inner class AllOrderListCallback : RetrofitCallback<List<LatestOrder>> {
        override fun onSuccess(code: Int, result: List<LatestOrder>) {
            val latestOrderList = result as MutableList<LatestOrder>
            for (order in latestOrderList){
                var count = 0
                val orderDetails = OrderRepository.get().getOrderDetails(order.orderId)
                orderDetails.observe(viewLifecycleOwner) { orderDetails ->
                    orderDetails.let {
                        count = orderDetails[0].stampCount
                    }
                    orderList.add(Pair(order,count))
                    orderList.sortWith(compareByDescending { it.first.orderDate })
                    stampHistoryAdapter.orderList = orderList
                    stampHistoryAdapter.notifyDataSetChanged()
                }
            }
        }

        override fun onError(t: Throwable) {
            Log.d(TAG, t.message ?: "전체 주문 내역 불러오는 중 통신오류")
        }

        override fun onFailure(code: Int) {
            Log.d(TAG, "onResponse: Error Code $code")
        }
    }

}