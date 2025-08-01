package com.example.myapplication

import com.example.myapplication.repository.AuthRepositoryImpl
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import junit.framework.TestCase.*
import org.junit.Before
import org.junit.Test
import org.mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.*

class RegisterUnitTest {

    @Mock
    private lateinit var mockAuth: FirebaseAuth

    @Mock
    private lateinit var mockTask: Task<AuthResult>

    @Mock
    private lateinit var mockFirebaseUser: FirebaseUser

    private lateinit var authRepository: AuthRepositoryImpl

    @Captor
    private lateinit var captor: ArgumentCaptor<OnCompleteListener<AuthResult>>

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        authRepository = AuthRepositoryImpl(mockAuth)
    }

    @Test
    fun testRegister_Successful() {
        val email = "ram@gmail.com"
        val password = "password"
        var actualMessage = ""
        var actualUid = ""
        var actualSuccess = false

        // Mock Firebase current user
        whenever(mockAuth.currentUser).thenReturn(mockFirebaseUser)
        whenever(mockFirebaseUser.uid).thenReturn("mockedUserId")

        // Simulate successful task
        whenever(mockTask.isSuccessful).thenReturn(true)
        whenever(mockAuth.createUserWithEmailAndPassword(any(), any())).thenReturn(mockTask)

        // ✅ Important: mock addOnCompleteListener chaining
        whenever(mockTask.addOnCompleteListener(any())).thenReturn(mockTask)

        val callback = { success: Boolean, message: String, uid: String ->
            actualSuccess = success
            actualMessage = message
            actualUid = uid
        }

        authRepository.register(email, password, callback)

        verify(mockTask).addOnCompleteListener(captor.capture())
        captor.value.onComplete(mockTask)

        assertTrue(actualSuccess)
        assertEquals("Registered successfully", actualMessage)
        assertEquals("mockedUserId", actualUid)
    }

    @Test
    fun testRegister_Failure() {
        val email = "ram@gmail.com"
        val password = "password"
        var actualMessage = ""
        var actualUid = ""
        var actualSuccess = true

        val mockErrorMessage = "Email already in use"
        val mockException = mock<Exception> {
            on { message } doReturn mockErrorMessage
        }

        // Simulate failed task
        whenever(mockTask.isSuccessful).thenReturn(false)
        whenever(mockTask.exception).thenReturn(mockException)
        whenever(mockAuth.createUserWithEmailAndPassword(any(), any())).thenReturn(mockTask)

        // ✅ Important: mock addOnCompleteListener chaining
        whenever(mockTask.addOnCompleteListener(any())).thenReturn(mockTask)

        val callback = { success: Boolean, message: String, uid: String ->
            actualSuccess = success
            actualMessage = message
            actualUid = uid
        }

        authRepository.register(email, password, callback)

        verify(mockTask).addOnCompleteListener(captor.capture())
        captor.value.onComplete(mockTask)

        assertFalse(actualSuccess)
        assertEquals(mockErrorMessage, actualMessage)
        assertEquals("", actualUid)
    }
}
