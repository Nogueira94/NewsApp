package com.appnews.presenter.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.appnews.databinding.FragmentHomeBinding
import com.appnews.domain.model.Article
import com.appnews.domain.model.StateView
import com.appnews.presenter.adapter.TopHeadlinesAdapter
import com.appnews.presenter.viewmodels.HomeViewModel
import com.sec.BiometricAuth
import com.sec.BiometricResponse
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel : HomeViewModel by viewModel()

    private val biometricAuth : BiometricAuth by inject()

    private lateinit var binding : FragmentHomeBinding

    private fun authValidate() {
        biometricAuth.biometricAuth(this){ biometricResponse ->
            when(biometricResponse){
                BiometricResponse.SUCCESS -> {
                    setupView()
                }
                BiometricResponse.ERROR -> {
                    requireActivity().finish()
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        authValidate()


        return binding.root
    }

    private fun setupView() {
        binding.rvArticles.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(context,
                    DividerItemDecoration.VERTICAL)
            )

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.headlinesState.observe(viewLifecycleOwner){ stateView ->
            populateView(stateView)
        }

        viewModel.getArticles()

    }

    private fun populateView(stateView: StateView<List<Article>>?) {
        when(stateView){
            is StateView.Loading -> {}
            is StateView.DataLoaded -> {
                binding.rvArticles.adapter = TopHeadlinesAdapter(stateView.data,this)

            }
            is StateView.Error -> {}
            else -> {}
        }
    }

}