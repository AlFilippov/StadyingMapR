package com.alphilippov.studyingmapnew.features.searchcourses.filter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.alphilippov.studyingmapnew.R
import moxy.MvpAppCompatDialogFragment
import moxy.presenter.InjectPresenter

class FilterCoursesFragment : MvpAppCompatDialogFragment(), FilterCoursesView {
    interface Callback {

        fun onSaveLinkClick(name: String)
    }

    companion object {
        const val TAG = "FilterCoursesFragment"
        fun newInstance() = FilterCoursesFragment()
    }

    @InjectPresenter
    lateinit var presenter: FilterCoursesPresenter
    private var callback: Callback? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = fragmentManager?.findFragmentByTag(TAG) as Callback

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.filter_courses_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun show(fragmentManager: FragmentManager) = show(
            fragmentManager,
            TAG
    )

}