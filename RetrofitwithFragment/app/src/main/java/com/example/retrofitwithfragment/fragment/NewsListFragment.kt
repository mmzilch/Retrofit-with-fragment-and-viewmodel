package com.example.retrofitwithfragment.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.retrofitwithfragment.R
import com.example.retrofitwithfragment.adapter.NewsAdapter
import com.example.retrofitwithfragment.api.NewsApi
import com.example.retrofitwithfragment.model.News
import com.example.retrofitwithfragment.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_news_list.*

/**
 * A simple [Fragment] subclass.
 */
class NewsListFragment : Fragment() {

    private lateinit var newsAdapter: NewsAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var newsViewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewManager = LinearLayoutManager(activity)
        newsAdapter = NewsAdapter()//no need to add constructor cause of we defined array list
        rcyNews.apply {
            adapter= newsAdapter
            layoutManager = viewManager
            observeViewModel()
        }

    }

    fun observeViewModel(){//to get data from VM
        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)//create VM object

        newsViewModel.getResults().observe(
            viewLifecycleOwner, Observer { result ->
                newsAdapter.updateList(result.articles)//to add data //result is News()
                //result == newsViewModel.getResults() data
            }
        )
    }

    override fun onResume() {//to get result
        super.onResume()
        newsViewModel.loadResult("technology")//data loading and get data

    }

}
