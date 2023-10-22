package org.sopt.kotlinsemina.domain.member.model

enum class Part(
    val partName: String,
) {
    SERVER("서버"),
    WEB("웹"),
    ANDROID("안드로이드"),
    IOS("iOS"),
    PLAN("기획"),
    DESIGN("디자인")
}