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

// 定義註冊流程的四個主要階段
enum class RegistrationStage {
    CredentialsStage,        // 輸入帳號密碼階段
    OtpVerificationStage,    // OTP驗證階段
    PersonalDetailsStage,    // 個人資料填寫階段
    RegistrationSuccessStage // 註冊成功階段
}

// 定義動畫時間常數（單位：毫秒）
const val REGISTRATION_SCREEN_SLIDE_OUT_ANIMATION = 300
const val REGISTRATION_SCREEN_SLIDE_IN_ANIMATION = 300

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SignupScreen(
    composeNavigator: ComposeNavigator,    // 負責處理畫面導航
    viewModel: SignupViewModel = hiltViewModel()  // 使用 Hilt 注入 ViewModel
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

    // LaunchedEffect 用於處理註冊階段改變時的副作用
    LaunchedEffect(key1 = currentRegistrationStep) {
        // 根據不同註冊階段更新 AppBar 標題和進度
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

    // Scaffold 提供基本的 Material Design 佈局結構
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
            // 主要內容區域，包含註冊進度和表單
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
                        // 在這裡調用 ViewModel 的方法來保存使用者資料
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