package com.example.myapplication

import com.example.myapplication.repository.AuthRepositoryImpl
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class AuthUnitTest {

    @Mock
    private lateinit var mockAuth: FirebaseAuth

    @Mock
    private lateinit var mockTask: Task<AuthResult>

    private lateinit var authRepository: AuthRepositoryImpl

    private lateinit var captor: ArgumentCaptor<OnCompleteListener<AuthResult>>

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        authRepository = AuthRepositoryImpl(mockAuth)
        captor = ArgumentCaptor.forClass(OnCompleteListener::class.java) as ArgumentCaptor<OnCompleteListener<AuthResult>>
    }

    @Test
    fun testLogin_Successful() {
        val email = "ram@gmail.com"
        val password = "password"
        var actualMessage = ""
        var actualSuccess = false

        // Mock successful task
        whenever(mockTask.isSuccessful).thenReturn(true)
        whenever(mockAuth.signInWithEmailAndPassword(any(), any())).thenReturn(mockTask)
        whenever(mockTask.addOnCompleteListener(any())).thenReturn(mockTask)

        val callback = { success: Boolean, message: String? ->
            actualSuccess = success
            actualMessage = message ?: ""
        }

        authRepository.login(email, password, callback)

        verify(mockTask).addOnCompleteListener(captor.capture())
        captor.value.onComplete(mockTask)

        assertTrue(actualSuccess)
        assertEquals("Login successfully", actualMessage)
    }

    @Test
    fun testLogin_Failure() {
        val email = "ram@gmail.com"
        val password = "password"
        var actualMessage = ""
        var actualSuccess = true

        val mockErrorMessage = "Invalid credentials"
        val mockException = mock<Exception> {
            on { message } doReturn mockErrorMessage
        }

        whenever(mockTask.isSuccessful).thenReturn(false)
        whenever(mockTask.exception).thenReturn(mockException)
        whenever(mockAuth.signInWithEmailAndPassword(any(), any())).thenReturn(mockTask)
        whenever(mockTask.addOnCompleteListener(any())).thenReturn(mockTask)

        val callback = { success: Boolean, message: String? ->
            actualSuccess = success
            actualMessage = message ?: ""
        }

        authRepository.login(email, password, callback)

        verify(mockTask).addOnCompleteListener(captor.capture())
        captor.value.onComplete(mockTask)

        assertTrue(!actualSuccess)
        assertEquals(mockErrorMessage, actualMessage)
    }
}
