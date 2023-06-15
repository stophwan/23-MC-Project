package com.example.mc_project

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mc_project.databinding.ActivityMainBinding
import com.example.mc_project.db.FoodieDataBase
import com.example.mc_project.db.table.TastePlace
import com.example.mc_project.db.table.User
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.*

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var db = FoodieDataBase.getInstance(applicationContext)

        var userArr = mutableListOf(
            User(id = 1, authId = "jiyoung@gmail.com", password = "a", name = "신지영", tasteCount = 2, friendCount = 0),
            User(id = 2, authId = "hanapark@gmail.com", password = "a", name = "박하나", tasteCount = 3, friendCount = 0),
        )


        var tastePlaceArr = mutableListOf(
            TastePlace(id=1, userId = 1, type = "식당", longitude = 126.92555227861433, latitude = 37.580495976590896,
                name = "순이네 고릴라 떡볶이 ", rate = 3.5, content = "적당히 맛있었던 것 같아. 한번 쯤은 추천"),
            TastePlace(id=2, userId = 1, type = "식당", latitude = 37.579909, longitude= 126.924733,
                name = "맥도날드 명지대점 ", rate = 3.5, content = "적당히 맛있었던 것 같아. 한번 쯤은 추천"),
            TastePlace(id=3, userId = 2, type = "식당", latitude = 37.580556, longitude = 126.922833,
                name = "모래내 곱창 ", rate = 3.5, content = "적당히 맛있었던 것 같아. 한번 쯤은 추천"),
            TastePlace(id=4,userId = 2, type = "식당", latitude = 37.580545, longitude =126.9251566,
                name = "김밥천국 명지대점 ", rate = 3.5, content = "적당히 맛있었던 것 같아. 한번 쯤은 추천"),
            TastePlace(id=5,userId = 2, type = "식당", latitude = 37.576510, longitude = 126.919716,
                name = "나의 행복한 돈까스 ", rate = 3.5, content = "적당히 맛있었던 것 같아. 한번 쯤은 추천")
        )

        val scope = CoroutineScope(Dispatchers.Main)

        scope.launch {
            for (user in userArr) {
                withContext(Dispatchers.IO) {
                    db!!.userDao().insert(user)
                }
            }

            for (tablePlace in tastePlaceArr) {
                withContext(Dispatchers.IO) {
                    db!!.tastePlaceDao().insert(tablePlace)
                }
            }
        }
        KakaoSdk.init(this, "854a3cb7f6c6e19da914dd436b1b7627")
        binding.login.setOnClickListener{
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                // 카카오톡 로그인
                UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                    // 로그인 실패 부분
                    if (error != null) {
                        Log.e("LOGIN", "로그인 실패 $error")
                        // 사용자가 취소
                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled ) {
                            return@loginWithKakaoTalk
                        }
                        // 다른 오류
                        else {
                            UserApiClient.instance.loginWithKakaoAccount(this, callback = mCallback) // 카카오 이메일 로그인
                        }
                    }
                    // 로그인 성공 부분
                    else if (token != null) {
                        UserApiClient.instance.me { user, error ->
                            val email = user!!.kakaoAccount!!.email
                            val nickname = user!!.kakaoAccount!!.name

                            val job = CoroutineScope(Dispatchers.IO).launch {
                                var loginId = 0
                                if (email != null && nickname != null) {
                                    Log.e("LOGIN", email)
                                    val ituser = db!!.userDao().getUserByAuthId(email)
                                    if (ituser != null) {
                                        loginId = ituser.id
                                    } else {
                                        Log.e("LOGIN", ituser.name)
                                        db.userDao().insert(User(authId = email, password = "a", name = nickname, tasteCount = 0, friendCount = 0))
                                        loginId = db.userDao().getUserByAuthId(email).id
                                    }
                                }
                                withContext(Dispatchers.Main) {
                                    Log.e("LOGIN", "ssssss")
                                    Auth.setLoginId(loginId)
                                }
                            }
                            CoroutineScope(Dispatchers.Main).launch {
                                job.join()

                                Log.e("LOGIN", "로그인 성공 ${token.accessToken}")
                                Toast.makeText(this@LoginActivity, "로그인 성공!", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                startActivity(intent)
                            }

                        }

                    }
                }
            } else {
                UserApiClient.instance.loginWithKakaoAccount(this, callback = mCallback) // 카카오 이메일 로그인
            }
        }

    }

    private val mCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Log.e("LOGIN", "로그인 실패 $error")
        } else if (token != null) {
            Log.e("LOGIN", "로그인 성공 ${token.accessToken}")
        }
    }
}
