package com.example.openweathermap2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.openweathermap2.model.City

import com.example.openweathermap2.listener.OnItemClickListener
import com.example.openweathermap2.R
import com.simplecityapps.recyclerview_fastscroll.views.FastScrollRecyclerView.SectionedAdapter
import java.util.*

class CityAdapter(
        private var mItems: ArrayList<City?>,
        onItemClickListener: OnItemClickListener?
) : RecyclerView.Adapter<CityAdapter.ViewHolder?>(), Filterable, SectionedAdapter {
    private var mFilteredItems: ArrayList<City?>
    private val mOnItemClickListener: OnItemClickListener?
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //인플레이션을 통해 뷰 객체 만들기
        val view =
                LayoutInflater.from(parent.context).inflate(R.layout.list_item_city, parent, false)
        return ViewHolder(view, mOnItemClickListener) //뷰홀더 객체를 생성
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = mFilteredItems!!.get(position)

        //뷰 홀더에 아이템 전달
        viewHolder.setItem(item)
    }

    override fun getItemCount(): Int {
        return mFilteredItems!!.size
    }

    override fun getFilter(): Filter? {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults? {
                val charString = charSequence.toString()
                mFilteredItems = if (charString.isEmpty()) {
                    mItems
                } else {
                    val filteringList = ArrayList<City?>()
                    for (city in mItems!!) {
                        if (city!!.name!!.toLowerCase().contains(charString.toLowerCase())) {
                            filteringList.add(city)
                        }
                    }
                    filteringList
                }
                val filterResults = FilterResults()
                filterResults.values = mFilteredItems
                return filterResults
            }

            override fun publishResults(
                    charSequence: CharSequence?,
                    filterResults: FilterResults?
            ) {
                mFilteredItems = filterResults!!.values as ArrayList<City?>
                notifyDataSetChanged()
            }
        }
    }

    override fun getSectionName(position: Int): String {
        return if (mFilteredItems!!.get(position)!!.name!!.isEmpty()) {
            mFilteredItems!!.get(position)!!.name.toString()
        } else {
            mFilteredItems!!.get(position)!!.name?.get(0).toString()
        }
    }

    inner class ViewHolder(itemView: View, onItemClickListener: OnItemClickListener?) :
            RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val tvCountryCode: TextView? = itemView.findViewById(R.id.tv_country_code)
        private val tvCityName: TextView? = itemView.findViewById(R.id.tv_city_name)
        private val onItemClickListener: OnItemClickListener?
        private var mPosition = 0
        private var mCity: City? = null
        fun setItem(item: City?) {
            mPosition = adapterPosition
            mCity = mFilteredItems!![mPosition]
            tvCountryCode!!.setText(item!!.getCountryName(item!!.country))
            tvCityName!!.setText(item.name)
        }

        override fun onClick(view: View?) {
            mCity!!.id?.let { onItemClickListener!!.onItemClick(it) }
        }

        init {
            this.onItemClickListener = onItemClickListener
            itemView.setOnClickListener(this)
        }
    }

    init {
        mFilteredItems = mItems
        mOnItemClickListener = onItemClickListener
    }
}