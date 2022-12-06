package com.jamesellerbee.codeadvent2022.dayfour

enum class OverlapMode {
    /**
     * Overlap mode to check if one of the given ranges is a sub set of the other.
     */
    SUB_SET,

    /**
     * Overlap mode to check if two ranges intersect at all.
     */
    INTERSECTION,
}