package com.example.firstand

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class FirstActivity : AppCompatActivity() {

    // 声明控件（Kotlin无需findViewById，直接用ID引用，需启用viewBinding或插件）
    // 这里用简化方式：直接通过ID获取（需在模块build.gradle中启用viewBinding，或使用插件）
    private lateinit var tvResult: TextView
    private lateinit var btnGoSecond: Button

    // 注册返回结果回调（Kotlin用lambda简化）
    private val launcher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val resultData = data?.getStringExtra("result")
            tvResult.text = "从第二个页面返回：$resultData"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        // 绑定控件（Kotlin可直接用ID引用，需确保布局中ID正确）
        tvResult = findViewById(R.id.tvResult)
        btnGoSecond = findViewById(R.id.btnGoSecond)

        // 按钮点击事件（lambda表达式简化）
        btnGoSecond.setOnClickListener {
            // 跳转并传递数据
            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra("username", "张三")
                putExtra("age", 20)
                putExtra("isStudent", true)
            }
            launcher.launch(intent) // 启动并等待返回结果
        }
    }
}