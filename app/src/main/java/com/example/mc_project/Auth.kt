package com.example.mc_project

class Auth{
    companion object {
        private var loginId = 0

        fun getLoginId() : Int {
            return loginId
        }

        fun setLoginId(id:Int) {
            loginId = id
        }
    }
}