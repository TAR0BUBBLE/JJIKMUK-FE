package com.example.beforemerge

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 여기서 fragment_home.xml 안의 버튼 클릭 이벤트 연결
        // 예: 메뉴 버튼, 프로필 버튼, 채팅 전송 버튼
    }
}