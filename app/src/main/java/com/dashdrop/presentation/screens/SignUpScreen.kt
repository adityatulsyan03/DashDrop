package com.dashdrop.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dashdrop.R
import com.dashdrop.navigation.Screen
import com.dashdrop.presentation.viewmodels.SignUpUIEvent
import com.dashdrop.presentation.viewmodels.SignUpViewModel
import com.dashdrop.presentation.viewmodels.checkAndStoreUser
import com.dashdrop.ui.components.ClickableLoginTextComponent
import com.dashdrop.ui.components.CustomInputField
import com.dashdrop.ui.components.DividerTextComponent
import com.dashdrop.ui.components.HeadingText
import com.dashdrop.ui.components.LoginButton
import com.dashdrop.ui.components.PasswordTextField
import com.dashdrop.ui.components.SmallCircularImageButton
import com.dashdrop.ui.components.TextField_Text
import com.dashdrop.ui.theme.bg
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


//TODO: Color Scheme bacha hai UI mein bss

@Composable
fun SignUpScreen(
    signUpViewModel: SignUpViewModel = viewModel(),
    navController: NavController
) {
    val context = LocalContext.current
    var user by remember { mutableStateOf(Firebase.auth.currentUser) }
    val launcher = rememberFirebaseAuthLauncher(onAuthComplete = { result ->
        user = result.user
        checkAndStoreUser(user)
        Toast.makeText(context,"Sign Up Successful", Toast.LENGTH_SHORT).show()
    },
        onAuthError = {
            user = null
            Toast.makeText(context,"Sign Up Failed",Toast.LENGTH_SHORT).show()

        }
    )
    val token = stringResource(id = R.string.web_client_id)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = bg)
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Column(
                    modifier = Modifier.padding(20.dp, 0.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(35.dp))
                    HeadingText(
                        value = stringResource(id = R.string.Sign_Up),
                        size = 30.sp,
                        weight = FontWeight.ExtraBold,
                        color = Color.White,
                        modifier = Modifier
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    HeadingText(
                        value = stringResource(id = R.string.Sign_Up_Info),
                        size = 16.sp,
                        weight = FontWeight.Normal,
                        color = Color.White,
                        modifier = Modifier
                    )
                    Spacer(modifier = Modifier.height(50.dp))
                }

                Card(
                    modifier = Modifier
                        .padding(5.dp),
                    elevation = CardDefaults.elevatedCardElevation(10.dp)
                ) {
                    Surface {
                        Column(
                            modifier = Modifier
                                .padding(
                                    top = 10.dp, bottom = 10.dp,
                                    start = 5.dp, end = 5.dp
                                )
                                .fillMaxSize()
                                .padding(12.dp, 0.dp),
                            verticalArrangement = Arrangement.Top,

                            ) {
                            Spacer(modifier = Modifier.height(10.dp))

                            TextField_Text(
                                modifier = Modifier,
                                labelValue = stringResource(id = R.string.Name)
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            CustomInputField(
                                onTextSelected = {
                                    signUpViewModel.onEvent(
                                        SignUpUIEvent.NameChanged(it),
                                        navController
                                    )
                                },
                                errorStatus = signUpViewModel.registrationUIState.value.nameError
                            )

                            Spacer(modifier = Modifier.height(15.dp))

                            TextField_Text(
                                modifier = Modifier,
                                labelValue = stringResource(id = R.string.Email_Address)
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            CustomInputField(onTextSelected = {
                                signUpViewModel.onEvent(
                                    SignUpUIEvent.EmailChanged(it),
                                    navController
                                )
                            }, errorStatus = signUpViewModel.registrationUIState.value.emailError)

                            Spacer(modifier = Modifier.height(15.dp))

                            TextField_Text(
                                modifier = Modifier,
                                labelValue = stringResource(id = R.string.Password)
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            PasswordTextField(
                                onTextSelected = {
                                    signUpViewModel.onEvent(
                                        SignUpUIEvent.PasswordChanged(it),
                                        navController
                                    )
                                },
                                errorStatus = signUpViewModel.registrationUIState.value.passwordError
                            )
                            Spacer(modifier = Modifier.height(20.dp))

                            LoginButton(
                                value = stringResource(id = R.string.Sign_Up),
                                onClick = {
                                    signUpViewModel.onEvent(
                                        SignUpUIEvent.RegisterButtonClicked,
                                        navController
                                    )
                                },
                                isEnabled = signUpViewModel.allValidationsPassed.value
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Divider(thickness = 1.dp, color = Color.Black)

                            Spacer(modifier = Modifier.height(30.dp))
                            ClickableLoginTextComponent(
                                tryingToLogin = true,
                                onTextSelected = {
                                    navController.navigate(Screen.SignInScreen.route)
                                }
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            DividerTextComponent(value = stringResource(id = R.string.Other_Sign_Up))

                            Spacer(modifier = Modifier.height(8.dp))
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                SmallCircularImageButton(
                                    onClick = {
                                        val gso = GoogleSignInOptions.Builder(
                                            GoogleSignInOptions.DEFAULT_SIGN_IN
                                        )
                                            .requestIdToken(token)
                                            .requestEmail()
                                            .build()

                                        val googleSignInClient =
                                            GoogleSignIn.getClient(context, gso)
                                        launcher.launch(googleSignInClient.signInIntent)
                                    },
                                    image = painterResource(id = R.drawable.google),
                                    desc = stringResource(id = R.string.Google)
                                )
                            }
                        }
                    }


                }
            }

        }

        if (signUpViewModel.signUpInProgress.value) {
            CircularProgressIndicator(color = bg)
        }
    }

}


@Composable
@Preview(showSystemUi = true)
fun SignUpScreen_Preview() {
    SignUpScreen(
        navController = rememberNavController()
    )
}

