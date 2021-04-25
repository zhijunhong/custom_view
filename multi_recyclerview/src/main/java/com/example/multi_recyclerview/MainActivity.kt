package com.example.multi_recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    var mRv :RecyclerView? = null
    var moudles: ArrayList<Vistable> = ArrayList()
    var mAdapter: MultiTypeAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        bindEvent()
        initData()
    }

    private fun initData() {
        moudles!!.add(AndroidBean())
        moudles!!.add(AndroidBean())
        moudles!!.add(AndroidBean())

        moudles!!.add(IOSBean())
        moudles!!.add(IOSBean())

        moudles!!.add(WinPhoneBean())
        moudles!!.add(WinPhoneBean())
        moudles!!.add(WinPhoneBean())
        moudles!!.add(WinPhoneBean())

        mAdapter!!.notifyDataSetChanged()
    }

        private fun bindEvent() {
        mRv!!.layoutManager = GridLayoutManager(this,2)
        mRv!!.isNestedScrollingEnabled = true
        mRv!!.setHasFixedSize(true)
        mRv!!.itemAnimator = DefaultItemAnimator()
        mAdapter = MultiTypeAdapter(moudles, this)
        mRv!!.adapter = MultiTypeAdapter(moudles, this)
    }

    private fun initView() {
        mRv = findViewById(R.id.rv)
    }

}