package com.example.demo.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.demo.Apiclient.ApiResponse.NotifyResponse
import com.example.demo.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tg.intaonline.intaonline.Adaptater.NotifyAdapter
import tg.intaonline.intaonline.ApiClient.ApiResponse.NotifyList
import tg.intaonline.intaonline.ApiClient.service.ApiClient
import tg.intaonline.intaonline.ApiClient.service.ApiInterface

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Notification.newInstance] factory method to
 * create an instance of this fragment.
 */
class Notification : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private val dataList = ArrayList<NotifyList>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NotifyAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.Notification)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter = NotifyAdapter(dataList)
        recyclerView.adapter = adapter

        swipeRefresh = view.findViewById(R.id.swipeRefresh)
        if (dataList.isEmpty()) {
                getNotificationList()
        }
        swipeRefresh.setOnRefreshListener {
            getNotificationList()
        }

    }

    private fun getNotificationList() {
        val api = ApiClient().getRetrofit().create(ApiInterface::class.java)

        api.getNotify()?.enqueue(object : Callback<NotifyResponse> {
            override fun onResponse(
                call: Call<NotifyResponse>,
                response: Response<NotifyResponse>
            ) {

                val success = response.body()?.success
                if(swipeRefresh.isRefreshing){
                    swipeRefresh.isRefreshing = false
                }
                if (response.isSuccessful) {
                    dataList.clear() // clear the existing data
                    dataList.addAll(response.body()!!.liste)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<NotifyResponse>, t: Throwable) {
                val message = t.localizedMessage
                swipeRefresh.isRefreshing = false
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show()

            }

        })

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Notification.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Notification().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}