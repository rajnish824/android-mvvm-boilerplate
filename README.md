# android-mvvm-boilerplate
Project Structure
<pre>
.
└── baseApplication/
    ├── base/
    │   ├── BaseActivity.kt
    │   ├── BaseAdapter.kt
    │   ├── BaseBottomSheet.kt
    │   ├── BaseDialog.kt
    │   └── BaseErrorInterface.kt
    │   
    ├── model/
    │   ├── AppConfig.kt
    │   ├── DataWrapper.kt
    │   ├── ErrorResponse.kt
    │   └── ExampleResponse.kt
    │
    ├── module/
    │   ├── AppModule.kt
    │   ├── NetworkModule.kt
    │   ├── RepositoryModule.kt
    │   └── ViewModelModule.kt
    │
    ├── network/
    │   ├── ApiClient.kt
    │   └── ApiServices.kt
    │ 
    ├── onboarding/
    │   └── OnBoardingActivity.kt/
    │ 
    ├── repository/
    │   └── DashboardRepository.kt/
    │ 
    ├── ui/
    │   └── dashboard/
    │       ├── view/
    │       │   └── DashboardActivity.kt
    │       └── viewmodel/
    │           └── DashboardViewModel.kt
    │       
    ├── utils/
    │   ├── extensions/
    │   │   ├── CallExtensions.kt
    │   │   ├── ClickExtensions.kt
    │   │   ├── DeviceExtensions.kt
    │   │   ├── AppExtensions.kt
    │   │   ├── ImageExtensions.kt
    │   │   └── ViewExtensions.kt
    │   │
    │   ├── AppConst.kt
    │   ├── AppUtils.kt
    │   ├── DateTimeUtils.kt
    │   ├── PrefUtils.kt
    │   └── Validators.kt
    │
    └── BaseApplication.kt
</pre>
