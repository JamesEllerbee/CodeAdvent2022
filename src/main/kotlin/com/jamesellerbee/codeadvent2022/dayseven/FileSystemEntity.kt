package com.jamesellerbee.codeadvent2022.dayseven

abstract class FileSystemEntity(val name: String) {
    var parent: Directory? = null

    abstract fun getSize(): Int

    fun getDepth(): Int {
        var depth = 0
        var currentEntity = parent
        while(currentEntity != null) {
            currentEntity = currentEntity.parent
            depth++
        }

        return depth
    }
}