package com.ike.resto.ui.base

import android.content.Context
import com.ike.resto.ui.data.DataSource
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResourcesProvider @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun getResource(): DataSource {
        return DataSource.getDataSource(context.resources)
    }
}
