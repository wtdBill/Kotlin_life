package com.example.ypp.life.network

/**
 * Created by 88426 on 2017/3/15.
 */

class RecommendListEntity {

    var data: DataBean? = null
    var reCode: String? = null
    var reMsg: String? = null

    class DataBean {

        var busCode: String? = null
        var busMsg: String? = null
        var recomList: List<RecomListBean>? = null

        class RecomListBean {
            var cate: String? = null//是否视频笔记
            var title: String? = null
            var pic: String? = null
            var picHeight: Long = 0
            var picWidth: Long = 0
            var icon: String? = null
            var praiseNum: Int = 0
            var isPraise: Boolean = false
            var jumpId: String? = null
            var showType: String? = null
            var showCate: String? = null
            var userId: String? = null
            var userName: String? = null
            var userImg: String? = null
            var honorList: List<UserHor>? = null
            var dynamicEffect: Effect? = null
            var effectCode: String? = null

            class UserHor {
                var horid: String? = null
                var img: String? = null
            }

            class Effect {
                var bgImg: String? = null
                var effectCode: String? = null
                var effectName: String? = null
                var element: String? = null
                var floatType: String? = null
                var isSpin: Int = 0
                var speed: String? = null
                var tips: String? = null
            }
        }
    }
}
