package com.jamesellerbee.codeadvent2022.utility

object DependencyInjector {
    private val dependencyMap = mutableMapOf<Any, Any>()

    fun registerDependency(instanceClass: Class<*>, instance: Any) {
        dependencyMap[instanceClass] = instance
    }

    fun <T> resolve(instanceClass: Class<*>): T? {
        return dependencyMap[instanceClass] as T?
    }
}