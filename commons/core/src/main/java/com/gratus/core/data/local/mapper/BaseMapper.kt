package com.gratus.core.data.local.mapper

abstract class BaseMapper<in T, out R> {
    abstract fun map(value: T): R
}
