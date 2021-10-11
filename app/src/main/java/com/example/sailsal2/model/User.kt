package com.example.sailsal2.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User (
    val baseYear_month: Int,
    val baseYear_year: Int,
    val promotion_month: Int,
    val promotion_year: Int,
    val increment_month: Int,
    val Increment_year: Int,
    val three_year_promo_month: Int,
    val three_year_promo_year: Int,
    val arrearsF_month: Int,
    val arrearsF_year: Int,
    val arrearsT_month: Int,
    val arrears_T_month: Int,
    val basicay: Int,
    val grade: Int,
    val Da: Double,
    val mgb: Double,
    val perk: Double
): Parcelable

