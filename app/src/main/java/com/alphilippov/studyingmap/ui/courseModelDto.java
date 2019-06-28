package com.alphilippov.studyingmap.ui;

import android.widget.RatingBar;
import android.widget.TextView;

public class courseModelDto {
   private String mNameCourse;
    private  String mAuthorCourse;
    private String mRatingCourse;
    private String mAverageRating;
    private String mQuantityFeedBack;
    private  String mPriceCourse;
    private String mPriceWithoutDiscount;

    public courseModelDto(String nameCourse, String authorCourse, String ratingCourse, String averageRating, String quantityFeedBack, String priceCourse, String priceWithoutDiscount) {
        mNameCourse = nameCourse;
        mAuthorCourse = authorCourse;
        mRatingCourse = ratingCourse;
        mAverageRating = averageRating;
        mQuantityFeedBack = quantityFeedBack;
        mPriceCourse = priceCourse;
        mPriceWithoutDiscount = priceWithoutDiscount;
    }

    public String getNameCourse() {
        return mNameCourse;
    }

    public void setNameCourse(String nameCourse) {
        mNameCourse = nameCourse;
    }

    public String getAuthorCourse() {
        return mAuthorCourse;
    }

    public void setAuthorCourse(String authorCourse) {
        mAuthorCourse = authorCourse;
    }

    public String getRatingCourse() {
        return mRatingCourse;
    }

    public void setRatingCourse(String ratingCourse) {
        mRatingCourse = ratingCourse;
    }

    public String getAverageRating() {
        return mAverageRating;
    }

    public void setAverageRating(String averageRating) {
        mAverageRating = averageRating;
    }

    public String getQuantityFeedBack() {
        return mQuantityFeedBack;
    }

    public void setQuantityFeedBack(String quantityFeedBack) {
        mQuantityFeedBack = quantityFeedBack;
    }

    public String getPriceCourse() {
        return mPriceCourse;
    }

    public void setPriceCourse(String priceCourse) {
        mPriceCourse = priceCourse;
    }

    public String getPriceWithoutDiscount() {
        return mPriceWithoutDiscount;
    }

    public void setPriceWithoutDiscount(String priceWithoutDiscount) {
        mPriceWithoutDiscount = priceWithoutDiscount;
    }
}
