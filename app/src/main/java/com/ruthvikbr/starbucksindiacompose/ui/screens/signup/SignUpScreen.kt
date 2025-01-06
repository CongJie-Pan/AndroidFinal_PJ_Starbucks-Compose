package com.ruthvikbr.starbucksindiacompose.ui.screens.signup

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.ruthvikbr.starbucksindiacompose.R
import com.ruthvikbr.starbucksindiacompose.ui.screens.signup.components.*
import com.ruthvikbr.starbucksindiacompose.ui.theme.HouseGreen
import com.ruthvikbr.starbucksindiacompose.ui.theme.PrimaryWhite
import com.starbuckscompose.navigation.ComposeNavigator
import com.starbuckscompose.navigation.StarbucksScreen
import com.ruthvikbr.starbucksindiacompose.ui.screens.signup.SignupViewModel
import com.ruthvikbr.starbucksindiacompose.ui.components.AppBar


// 註冊階段的枚舉
enum class RegistrationStage {
    CredentialsStage,
    OtpVerificationStage,
    PersonalDetailsStage,
    RegistrationSuccessStage
}

// 動畫持續時間常量
const val REGISTRATION_SCREEN_SLIDE_OUT_ANIMATION = 300
const val REGISTRATION_SCREEN_SLIDE_IN_ANIMATION = 300

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SignupScreen(
    composeNavigator: ComposeNavigator,
    viewModel: SignupViewModel = hiltViewModel()
) {
    // 使用 remember 和 mutableStateOf 來創建可變狀態
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var mobileNumber by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var birthday by remember { mutableStateOf("") }
    var referralCode by remember { mutableStateOf("") }
    var isSmsEnabled by remember { mutableStateOf(false) }
    var isEmailEnabled by remember { mutableStateOf(false) }

    // 使用 rememberCoroutineScope 來創建協程作用域
    val coroutineScope = rememberCoroutineScope()

    // 當前註冊步驟
    var currentRegistrationStep by remember {
        mutableStateOf(RegistrationStage.CredentialsStage)
    }

    // 獲取本地化字符串
    val initialAppBarTitle = LocalContext.current.resources.getString(R.string.create_an_account)
    val personalDetailsAppBarTitle = LocalContext.current.resources.getString(R.string.personal_details)
    val otpVerificationAppBarTitle = LocalContext.current.resources.getString(R.string.verification)
    val registrationSuccessAppBarTitle = LocalContext.current.resources.getString(R.string.registration_success)

    // AppBar 標題
    var appBarTitle by remember { mutableStateOf(initialAppBarTitle) }

    // 註冊階段
    var registrationStage by remember { mutableStateOf(1) }

    // 使用 LaunchedEffect 來處理註冊步驟的變化
    LaunchedEffect(key1 = currentRegistrationStep) {
        when (currentRegistrationStep) {
            RegistrationStage.CredentialsStage -> {
                appBarTitle = initialAppBarTitle
                registrationStage = 1
            }
            RegistrationStage.OtpVerificationStage -> {
                appBarTitle = otpVerificationAppBarTitle
                registrationStage = 2
            }
            RegistrationStage.PersonalDetailsStage -> {
                appBarTitle = personalDetailsAppBarTitle
                registrationStage = 3
            }
            RegistrationStage.RegistrationSuccessStage -> {
                appBarTitle = registrationSuccessAppBarTitle
                registrationStage = 4
            }
        }
    }

    // 使用 Scaffold 來構建基本的頁面結構
    Scaffold(
        topBar = {
            AppBar(
                title = appBarTitle,
                onBackClicked = { composeNavigator.navigateUp() },
                backgroundColor = HouseGreen,
                backButtonVisible = currentRegistrationStep != RegistrationStage.RegistrationSuccessStage,
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(PrimaryWhite),
        ) {
            // 顯示註冊進度
            StepProgress(steps = 3, currentStep = registrationStage)

            // 使用 AnimatedVisibility 來實現不同註冊階段的動畫切換
            AnimatedVisibility(
                visible = currentRegistrationStep == RegistrationStage.CredentialsStage,
                enter = slideInHorizontally(
                    initialOffsetX = { -300 },
                    animationSpec = tween(
                        durationMillis = REGISTRATION_SCREEN_SLIDE_OUT_ANIMATION,
                        easing = LinearEasing,
                    ),
                ),
                exit = slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(
                        durationMillis = REGISTRATION_SCREEN_SLIDE_OUT_ANIMATION,
                        easing = LinearEasing,
                    ),
                ),
            ) {
                // 顯示憑證表單
                CredentialsForm(
                    onSubmitClicked = {
                        currentRegistrationStep = RegistrationStage.OtpVerificationStage
                    },
                    email = email,
                    onEmailChanged = { email = it },
                    mobile = mobileNumber,
                    onMobileChanged = { mobileNumber = it },
                    password = password,
                    onPasswordChanged = { password = it },
                    confirmPassword = confirmPassword,
                    onConfirmPasswordChanged = { confirmPassword = it },
                )
            }

            AnimatedVisibility(
                visible = currentRegistrationStep == RegistrationStage.OtpVerificationStage,
                enter = slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = tween(
                        durationMillis = REGISTRATION_SCREEN_SLIDE_OUT_ANIMATION,
                        easing = LinearEasing,
                        delayMillis = REGISTRATION_SCREEN_SLIDE_IN_ANIMATION,
                    ),
                ),
                exit = slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(
                        durationMillis = REGISTRATION_SCREEN_SLIDE_OUT_ANIMATION,
                        easing = LinearEasing,
                    ),
                ),
            ) {
                // 顯示 OTP 驗證
                OtpVerification(mobileNumber, onConfirmClicked = {
                    currentRegistrationStep = RegistrationStage.PersonalDetailsStage
                })
            }

            AnimatedVisibility(
                visible = currentRegistrationStep == RegistrationStage.PersonalDetailsStage,
                enter = slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = tween(
                        durationMillis = REGISTRATION_SCREEN_SLIDE_OUT_ANIMATION,
                        easing = LinearEasing,
                        delayMillis = REGISTRATION_SCREEN_SLIDE_IN_ANIMATION,
                    ),
                ),
                exit = slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(
                        durationMillis = REGISTRATION_SCREEN_SLIDE_OUT_ANIMATION,
                        easing = LinearEasing,
                    ),
                ),
            ) {
                // 顯示個人詳細資訊表單
                PersonalDetailsForm(
                    firstName = firstName,
                    lastName = lastName,
                    birthday = birthday,
                    referralCode = referralCode,
                    isSmsEnabled = isSmsEnabled,
                    isEmailEnabled = isEmailEnabled,
                    onFirstNameChanged = { firstName = it },
                    onLastNameChanged = { lastName = it },
                    onBirthdayChanged = { birthday = it },
                    onReferralCodeChanged = { referralCode = it },
                    onReferralCodeSubmitted = {},
                    onSmsPreferenceChanged = { isSmsEnabled = it },
                    onEmailPreferenceChanged = { isEmailEnabled = it },
                    onSubmitClicked = {
                        // 在這裡調用 ViewModel 的方法來保存用戶資料
                        viewModel.saveUser(
                            email = email,
                            password = password,
                            mobileNumber = mobileNumber,
                            firstName = firstName,
                            lastName = lastName,
                            birthday = birthday,
                            referralCode = referralCode,
                            isSmsEnabled = isSmsEnabled,
                            isEmailEnabled = isEmailEnabled
                        )
                        currentRegistrationStep = RegistrationStage.RegistrationSuccessStage
                    },
                )
            }

            AnimatedVisibility(
                visible = currentRegistrationStep == RegistrationStage.RegistrationSuccessStage,
                enter = slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = tween(
                        durationMillis = REGISTRATION_SCREEN_SLIDE_OUT_ANIMATION,
                        easing = LinearEasing,
                        delayMillis = REGISTRATION_SCREEN_SLIDE_IN_ANIMATION,
                    ),
                ),
                exit = slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(
                        durationMillis = REGISTRATION_SCREEN_SLIDE_OUT_ANIMATION,
                        easing = LinearEasing,
                    ),
                ),
            ) {
                // 顯示註冊成功畫面
                RegistrationSuccess(
                    onSuccess = {
                        composeNavigator.navigate(StarbucksScreen.Dashboard.name)
                    },
                )
            }
        }
    }
}