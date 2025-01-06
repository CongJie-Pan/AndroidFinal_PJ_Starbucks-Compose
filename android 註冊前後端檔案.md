根據工作區內容，以下是有關註冊流程的主要前端檔案:

主要註冊畫面控制器:
SignUpScreen.kt
控制註冊流程的主要頁面
管理註冊步驟狀態與轉換
包含表單數據狀態管理
---
註冊相關組件:

CredentialsForm.kt
處理用戶註冊時的憑證輸入表單
包含電子郵件、密碼、確認密碼等欄位
密碼格式驗證邏輯

OtpVerification.kt
OTP驗證碼輸入介面
處理驗證碼確認

PersonalDetailsForm.kt
個人資料輸入表單
包含姓名、生日、推薦碼等欄位
聯絡偏好設定

RegistrationSuccess.kt
註冊成功動畫與提示
成功畫面的視覺效果

ProfileDetails.kt
個人資訊頁面

這些檔案組成了完整的註冊流程UI，字串資源定義在 strings.xml 中。註冊階段定義在 RegistrationStage.kt。