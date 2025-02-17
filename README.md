# 專案架構目錄與UI設定事項

```
project-root/
├── .github/
│   └── PULL_REQUEST_TEMPLATE.md  # Pull Request 的模板文件
├── .gitignore  # 定義哪些文件和目錄應該被 Git 忽略
├── .gradle/
│   ├── config.properties  # Gradle 配置文件
│   └── file-system.probe  # 用於檢測文件系統的文件
├── .idea/
│   ├── workspace.xml  # 保存工作區設置
│   ├── modules.xml  # 保存模組設置
│   └── misc.xml  # 保存其他設置
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/example/app/
│   │   │   │       ├── MainActivity.java  # 應用程式的主要活動
│   │   │   │       └── App.java  # 應用程式的入口點
│   │   │   ├── kotlin/
│   │   │   │   └── com/example/app/
│   │   │   │       ├── MainActivity.kt  # 應用程式的主要活動 (Kotlin)
│   │   │   │       └── App.kt  # 應用程式的入口點 (Kotlin)
│   │   │   └── res/
│   │   │       ├── layout/
│   │   │       │   └── activity_main.xml  # 主活動的佈局文件
│   │   │       └── values/
│   │   │           ├── strings.xml  # 字串資源文件
│   │   │           └── colors.xml  # 顏色資源文件
│   │   └── test/
│   │       └── java/
│   │           └── com/example/app/
│   │               └── MainActivityTest.java  # 主活動的測試類
│   ├── build.gradle.kts  # 應用程式模組的 Gradle 構建腳本
│   └── proguard-rules.pro  # ProGuard 混淆規則文件
├── build/  # 專案的構建輸出目錄
├── buildSrc/
│   └── src/
│       └── main/
│           └── kotlin/
│               └── Deps.kt  # 定義專案中使用的所有依賴項
├── data/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/example/data/
│   │   │   │       ├── Repository.java  # 數據倉庫類
│   │   │   │       └── DataSource.java  # 數據源類
│   │   │   ├── kotlin/
│   │   │   │   └── com/example/data/
│   │   │   │       ├── Repository.kt  # 數據倉庫類 (Kotlin)
│   │   │   │       └── DataSource.kt  # 數據源類 (Kotlin)
│   │   └── test/
│   │       ├── java/
│   │       │   └── com/example/data/
│   │       │       └── RepositoryTest.java  # 數據倉庫的測試類
│   │       └── kotlin/
│   │           └── com/example/data/
│   │               └── RepositoryTest.kt  # 數據倉庫的測試類 (Kotlin)
│   ├── build.gradle.kts  # 數據層模組的 Gradle 構建腳本
│   └── proguard-rules.pro  # ProGuard 混淆規則文件
├── domain/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/example/domain/
│   │   │   │       ├── UseCase.java  # 用例類
│   │   │   │       └── Model.java  # 模型類
│   │   │   ├── kotlin/
│   │   │   │   └── com/example/domain/
│   │   │   │       ├── UseCase.kt  # 用例類 (Kotlin)
│   │   │   │       └── Model.kt  # 模型類 (Kotlin)
│   │   └── test/
│   │       ├── java/
│   │       │   └── com/example/domain/
│   │       │       └── UseCaseTest.java  # 用例的測試類
│   │       └── kotlin/
│   │           └── com/example/domain/
│   │               └── UseCaseTest.kt  # 用例的測試類 (Kotlin)
│   ├── build.gradle.kts  # 領域層模組的 Gradle 構建腳本
├── gradle/
│   └── wrapper/
│       └── gradle-wrapper.properties  # Gradle Wrapper 的配置文件
├── navigation/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/example/navigation/
│   │   │   │       └── Navigator.java  # 導航類
│   │   │   ├── kotlin/
│   │   │   │   └── com/example/navigation/
│   │   │   │       └── Navigator.kt  # 導航類 (Kotlin)
│   │   └── test/
│   │       ├── java/
│   │       │   └── com/example/navigation/
│   │       │       └── NavigatorTest.java  # 導航類的測試類
│   │       └── kotlin/
│   │           └── com/example/navigation/
│   │               └── NavigatorTest.kt  # 導航類的測試類 (Kotlin)
│   ├── build.gradle.kts  # 導航模組的 Gradle 構建腳本
│   └── proguard-rules.pro  # ProGuard 混淆規則文件
├── README.md  # 專案的說明文件
├── settings.gradle.kts  # 定義專案的模組和包含的構建腳本
├── gradle.properties  # 配置專案的全局屬性，如 JVM 參數和 AndroidX 設置
├── gradlew  # 用於在 Unix 系統上執行 Gradle 構建的腳本
└── gradlew.bat  # 用於在 Windows 系統上執行 Gradle 構建的腳本
```
---
### UI Setting 資料夾(實作的內容主要在這個資料夾)
---
- app/build/generated/source/kapt/debug/com/ruthvikbr/starbucksindiacompose/ui
- navigation/src/main/java
- app/src/main/java/com/ruthvikbr/starbucksindiacompose/ui
---
### UI 顯示主要語言字更改處:
app/src/main/res/values/strings.xml