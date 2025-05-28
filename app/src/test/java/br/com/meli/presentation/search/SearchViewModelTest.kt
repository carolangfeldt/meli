package br.com.meli.presentation.search

import app.cash.turbine.test
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: SearchViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = SearchViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should disable search when query is empty`() = runTest {
        viewModel.onQueryChanged("")
        viewModel.isSearchEnabled.test {
            assertFalse(awaitItem())
        }
    }

    @Test
    fun `should enable search when query is not blank`() = runTest {
        viewModel.onQueryChanged("iphone")
        viewModel.isSearchEnabled.test {
            assertTrue(awaitItem())
        }
    }
}