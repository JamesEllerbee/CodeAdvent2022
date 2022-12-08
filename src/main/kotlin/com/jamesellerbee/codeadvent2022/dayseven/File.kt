package com.jamesellerbee.codeadvent2022.dayseven

class File(name: String, private val size: Int) : FileSystemEntity(name) {
    override fun getSize(): Int {
        return size
    }

    override fun toString(): String {
        return "$name (file, size=$size)"
    }
}