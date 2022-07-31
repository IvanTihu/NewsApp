package com.example.testproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.example.testproject.databinding.FragmentNewsBinding
import com.example.testproject.databinding.FragmentNewsDetailsBinding


class NewsDetailsFragment : Fragment() {
    val args: NewsDetailsFragmentArgs by navArgs()
    lateinit var binding: FragmentNewsDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val view = inflater.inflate(R.layout.fragment_news_details, container, false)
        binding = FragmentNewsDetailsBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val amount = args.url
        binding.webViewDetails.loadUrl(amount)

    }
}