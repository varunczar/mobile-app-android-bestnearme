package com.varunczar.countries

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import au.com.bestnearme.data.categories.Category
import au.com.bestnearme.di.network.INetworkApi
import au.com.bestnearme.views.categories.CategoriesViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

class CategoriesViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var networkApi: INetworkApi

    @InjectMocks
    var categoriesViewModel = CategoriesViewModel()

    private var testSingle : Single<List<Category>> ? = null

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getCategoriesSuccess() {
        val category = Category("code", "displayName",0,"heroImageUrl")
        val categoryList = arrayListOf(category)

        testSingle = Single.just(categoryList)

        `when`(networkApi.getCategories()).thenReturn(testSingle)

        categoriesViewModel.refresh()

        assertEquals(1, categoriesViewModel.categories.value?.size)
        assertEquals(false, categoriesViewModel.categoriesLoadError.value)
        assertEquals(false, categoriesViewModel.loading.value)
    }

    @Test
    fun getCategoriesFailure() {

        testSingle = Single.error(Throwable())

        `when`(networkApi.getCategories()).thenReturn(testSingle)

        categoriesViewModel.refresh()

        assertEquals(null, categoriesViewModel.categories.value?.size)
        assertEquals(true, categoriesViewModel.categoriesLoadError.value)
        assertEquals(false, categoriesViewModel.loading.value)
    }

    @Before
    fun setUpRxSchedulers(){
        val immediate = object : Scheduler(){

            override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit): Disposable {
                return super.scheduleDirect(run, 0, unit)
            }

            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() },true)
            }
        }

        RxJavaPlugins.setInitIoSchedulerHandler { scheduler -> immediate }
        RxJavaPlugins.setInitComputationSchedulerHandler { scheduler -> immediate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler -> immediate }
        RxJavaPlugins.setInitSingleSchedulerHandler { scheduler -> immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> immediate }
    }
}