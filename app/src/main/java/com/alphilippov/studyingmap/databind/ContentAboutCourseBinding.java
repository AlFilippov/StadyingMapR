package com.alphilippov.studyingmap.databind;

import androidx.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class ContentAboutCourseBinding {
    private String mTitleCourse;
    private int mRatingBarCourse;
    private int mNumReviewsCourse;
    private String mNameReviewsCourse;
    private int mNumSubscribersCourse;
    private String mNameSubscribersCourse;
    private int mPriceNumCourse;
    private String mPriceSymbolCourse;
    private int mDiscountNumCourse;
    private String mDiscountCourse;
    private String mButtonSite;
    private String mAuthorCourse;
    private String mNamePercentDiscount;

    public ContentAboutCourseBinding(String titleCourse, int ratingBarCourse, int numReviewsCourse, String nameReviewsCourse,
                                     int numSubscribersCourse, String nameSubscribersCourse,
                                     int priceNumCourse, String priceSymbolCourse, int discountNumCourse,
                                     String discountCourse, String buttonSite, String authorCourse, String namePercentDiscount) {
        mTitleCourse = titleCourse;
        mRatingBarCourse = ratingBarCourse;
        mNumReviewsCourse = numReviewsCourse;
        mNameReviewsCourse = nameReviewsCourse;
        mNumSubscribersCourse = numSubscribersCourse;
        mNameSubscribersCourse = nameSubscribersCourse;
        mPriceNumCourse = priceNumCourse;
        mPriceSymbolCourse = priceSymbolCourse;
        mDiscountNumCourse = discountNumCourse;
        mDiscountCourse = discountCourse;
        mButtonSite = buttonSite;
        mAuthorCourse = authorCourse;
        mNamePercentDiscount = namePercentDiscount;
    }

    @BindingAdapter("CourseImage")
    public static void loadImage(ImageView view, String ImageUrl) {
        Glide.with(view.getContext()).load(ImageUrl).
                apply(new RequestOptions().
                        fitCenter()).into(view);
    }

    public String getNamePercentDiscount() {
        return mNamePercentDiscount;
    }

    public void setNamePercentDiscount(String namePercentDiscount) {
        mNamePercentDiscount = namePercentDiscount;
    }

    public String getAuthorCourse() {
        return mAuthorCourse;
    }

    public void setAuthorCourse(String authorCourse) {
        mAuthorCourse = authorCourse;
    }

    public String getNameReviewsCourse() {
        return mNameReviewsCourse;
    }

    public void setNameReviewsCourse(String nameReviewsCourse) {
        mNameReviewsCourse = nameReviewsCourse;
    }

    public String getNameSubscribersCourse() {
        return mNameSubscribersCourse;
    }

    public void setNameSubscribersCourse(String nameSubscribersCourse) {
        mNameSubscribersCourse = nameSubscribersCourse;
    }


    public String getTitleCourse() {
        return mTitleCourse;
    }

    public void setTitleCourse(String titleCourse) {
        mTitleCourse = titleCourse;
    }

    public int getRatingBarCourse() {
        return mRatingBarCourse;
    }

    public void setRatingBarCourse(int ratingBarCourse) {
        mRatingBarCourse = ratingBarCourse;
    }

    public int getNumReviewsCourse() {
        return mNumReviewsCourse;
    }

    public void setNumReviewsCourse(int numReviewsCourse) {
        mNumReviewsCourse = numReviewsCourse;
    }

    public int getNumSubscribersCourse() {
        return mNumSubscribersCourse;
    }

    public void setNumSubscribersCourse(int numSubscribersCourse) {
        mNumSubscribersCourse = numSubscribersCourse;
    }

    public int getPriceNumCourse() {
        return mPriceNumCourse;
    }

    public void setPriceNumCourse(int priceNumCourse) {
        mPriceNumCourse = priceNumCourse;
    }

    public String getPriceSymbolCourse() {
        return mPriceSymbolCourse;
    }

    public void setPriceSymbolCourse(String priceSymbolCourse) {
        mPriceSymbolCourse = priceSymbolCourse;
    }

    public int getDiscountNumCourse() {
        return mDiscountNumCourse;
    }

    public void setDiscountNumCourse(int discountNumCourse) {
        mDiscountNumCourse = discountNumCourse;
    }

    public String getDiscountCourse() {
        return mDiscountCourse;
    }

    public void setDiscountCourse(String discountCourse) {
        mDiscountCourse = discountCourse;
    }

    public String getButtonSite() {
        return mButtonSite;
    }

    public void setButtonSite(String buttonSite) {
        mButtonSite = buttonSite;
    }
}
