package com.glsservice.tg.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.GestureDetector
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.glsservice.tg.Adapter.AnswerListAdapter
import com.glsservice.tg.AdminConvActivity
import com.glsservice.tg.Apiclient.ApiResponse.QuestionListe
import com.glsservice.tg.Apiclient.ApiResponse.QuestionListeResponse
import com.glsservice.tg.ClientAskActivity
import com.glsservice.tg.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.glsservice.tg.Apiclient.Service.ApiClient
import com.glsservice.tg.ConversationActivity
import com.glsservice.tg.LoginActivity
import com.glsservice.tg.NotifyActivity
import tg.intaonline.intaonline.ApiClient.service.ApiInterface
import tg.intaonline.intaonline.Model.GlobalVariables

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Discussion.newInstance] factory method to
 * create an instance of this fragment.
 */
class Discussion : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private val dataList = ArrayList<QuestionListe>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AnswerListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.DisRecycler)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter = AnswerListAdapter(dataList)
        recyclerView.adapter = adapter

        swipeRefresh = view.findViewById(R.id.swipeRefresh2)
        if (dataList.isEmpty()) {
            getAnswerList()
        }
        swipeRefresh.setOnRefreshListener {
            getAnswerList()
        }
        recyclerView.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            private val gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapUp(e: MotionEvent): Boolean {
                    val view = recyclerView.findChildViewUnder(e.x, e.y)
                    if (view != null) {
                        val position = recyclerView.getChildAdapterPosition(view)
                        val selectedItem = dataList[position]
                        GlobalVariables.ticketGlobal = selectedItem.Ticket.toString().trim()
                        GlobalVariables.telClientAskGlobal = selectedItem.TelClient.toString().trim()
                        val intent = Intent(activity, ConversationActivity::class.java)
                        activity?.startActivity(intent) // Utiliser activity?.startActivity() pour éviter les nullPointerException
                       // Toast.makeText(getActivity(), GlobalVariables.telClientAskGlobal, Toast.LENGTH_SHORT).show()
                        return true
                    }
                    return false
                }
            })

            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                val childView = rv.findChildViewUnder(e.x, e.y)
                if (childView != null && gestureDetector.onTouchEvent(e)) {
                    return true
                }
                return false
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
        })

    }

    private fun getAnswerList() {
        val valeur = GlobalVariables.telGlobal.toString().trim()
        val api = ApiClient().getRetrofit().create(ApiInterface::class.java)

        api.getHistory(valeur)?.enqueue(object : Callback<QuestionListeResponse>{
            override fun onResponse(
                call: Call<QuestionListeResponse>,
                response: Response<QuestionListeResponse>
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

            override fun onFailure(call: Call<QuestionListeResponse>, t: Throwable) {
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
        setHasOptionsMenu(true)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.nav_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_add_disc -> {
                val intent = Intent(activity, ClientAskActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discussion, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Discussion.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Discussion().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}