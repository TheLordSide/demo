package com.glsservice.tg.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.glsservice.tg.Apiclient.ApiResponse.CompteList
import com.glsservice.tg.CompteListeActivity
import com.glsservice.tg.R
import kotlinx.coroutines.*

class CompteListeAdapter(private var dataList : ArrayList<CompteList>, private val context: Context): RecyclerView.Adapter<CompteListeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.items3,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val CompteList = dataList[position]
        holder.tel.text= CompteList.Telcompte
        holder.role.text = CompteList.role
        holder.date.text = CompteList.created_on


    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolder(itemLayoutView: View): RecyclerView.ViewHolder(itemLayoutView) {
        var role : TextView
        var tel : TextView
        var date : TextView
        var mMenus: ImageView = itemLayoutView.findViewById(R.id.mMenus)

        init {
            role=itemLayoutView.findViewById(R.id.RoleHolder)
            tel=itemLayoutView.findViewById(R.id.NumHolder)
            date=itemLayoutView.findViewById(R.id.DateCHolder)
            mMenus.setOnClickListener{popupMenus(it)}
        }

        private fun popupMenus(v:View) {
            val popupMenus = PopupMenu(v.context, v)
            popupMenus.inflate(R.menu.show_menu)
            popupMenus.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.Changetouser -> {

                        true
                    }
                    R.id.ChangetoAdmin -> {
                        // Action when "ChangetoAdmin" is clicked
                        true
                    }
                    else -> false
                }
            }
            popupMenus.show()


            try {
                val popup = popupMenus::class.java.getDeclaredField("mPopup")
                popup.isAccessible = true
                val menu = popup.get(popupMenus)
                val menuHelperClass = Class.forName(menu.javaClass.name)
                val setForceShowIcon = menuHelperClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                setForceShowIcon.invoke(menu, true)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        private fun changeUserAPI() {

        }

        private fun changeToAdminAPI() {

        }

    }
}