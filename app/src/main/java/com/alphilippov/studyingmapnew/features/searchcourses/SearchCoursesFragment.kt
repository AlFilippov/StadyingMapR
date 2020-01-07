package com.alphilippov.studyingmapnew.features.searchcourses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alphilippov.studyingmapnew.R
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class SearchCoursesFragment : MvpAppCompatFragment(), SearchFragmentView {

    companion object {
        const val TAG = "SearchCoursesFragment"
        fun newInstance() = SearchCoursesFragment()
    }

    @InjectPresenter
    lateinit var presenter: SearchFragmentPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.search_courses_fragment, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}