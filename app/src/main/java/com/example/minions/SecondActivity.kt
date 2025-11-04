package com.example.minions

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // 绑定控件
        val tvInfo: TextView = findViewById(R.id.tvInfo)
        val btnReturn: Button = findViewById(R.id.btnReturn)

        // 接收FirstActivity传递的数据（Kotlin用安全调用符?简化）
        val intent = intent
        val username = intent.getStringExtra("username") ?: "未知"
        val age = intent.getIntExtra("age", 0)
        val isStudent = intent.getBooleanExtra("isStudent", false)

        // 显示数据（字符串模板简化）
        tvInfo.text = "姓名：$username\n年龄：$age\n是否学生：$isStudent"

        // 返回数据到FirstActivity
        btnReturn.setOnClickListener {
            val returnIntent = Intent().apply {
                putExtra("result", "操作完成！已收到你的数据~")
            }
            setResult(RESULT_OK, returnIntent)
            finish() // 关闭当前页面
        }
    }
}