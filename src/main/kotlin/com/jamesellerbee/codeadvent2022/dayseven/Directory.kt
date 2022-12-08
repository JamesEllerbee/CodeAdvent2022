package com.jamesellerbee.codeadvent2022.dayseven

class Directory(name: String) : FileSystemEntity(name) {

    val children
        get() = _children.toList()

    private val _children = mutableListOf<FileSystemEntity>()

    fun addChild(entity: FileSystemEntity) {
        _children.add(entity)
    }

    fun getChild(name: String): FileSystemEntity? {
        return _children.firstOrNull { it.name == name }
    }

    override fun getSize(): Int {
        // a directories size is the sum of its children
        var size = 0
        _children.forEach {
            size += it.getSize()
        }

        return size
    }

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        val depth = getDepth()
        _children.forEachIndexed { index, child ->
            repeat(depth) {
                stringBuilder.append("\t")
            }

            stringBuilder.append(child.toString())

            if (index < _children.size - 1) {
                stringBuilder.append("\n")
            }
        }

        return "$name (dir)${if (stringBuilder.isNotEmpty()) "\n$stringBuilder" else ""}"
    }
}