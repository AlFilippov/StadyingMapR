package com.alphilippov.studyingmap.view;

import com.arellomobile.mvp.MvpView;

public interface FormCoursesKeyWordsView extends MvpView {
    void clickButtonSearch();
    void optionsCourses();
    void ratingCourse();
    void showErrorMessageConnection();
    void showErrorIsEmptyFields();

}
