package com.ruthvikbr.data.repo

import com.ruthvikbr.domain.models.DMCarouselItem
import com.ruthvikbr.domain.models.DMPopularMenuItem
import com.ruthvikbr.domain.models.DMSocialNewsItem
import com.ruthvikbr.domain.repo.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeRepoImpl : HomeRepository {
    override fun fetchCarouselItems(): Flow<List<DMCarouselItem>> {
        val dmCarouselItemList = listOf(
            DMCarouselItem(
                1,
                "不可抗拒的水果美味",
                "https://iili.io/UHRSfV.jpg",
                "為您帶來一系列美味可口的水果飲料！"
            ),
            DMCarouselItem(
                2,
                "Starbucks 登陸 Tata Cliq",
                "https://iili.io/UH7hZb.jpg",
                "來自 Starbucks* 的精選必需品系列，任您挑選！"
            ),
            DMCarouselItem(
                3,
                "最葡萄的飲品就在您附近的 Starbucks",
                "https://iili.io/UH7wnj.jpg",
                "為您帶來一系列美味可口的水果飲料！"
            ),
            DMCarouselItem(
                4,
                "慶祝您的特別日子",
                "https://iili.io/UH7NMx.jpg",
                "使用 Starbucks-India 行動應用程式，您可以在生日月份於最近的 Starbucks 免費享用一杯中杯飲料。"
            ),
            DMCarouselItem(
                5,
                "咖啡天堂",
                "https://iili.io/UH7O6Q.jpg",
                "通過 Zomato 和 Swiggy 將 Mocha Cookie Crumble Frappuccino 送到您家門口。"
            )


        )
        return flow {
            emit(dmCarouselItemList)
        }
    }

    override fun fetchPopularMenuItems(): Flow<List<DMPopularMenuItem>> {
        val popularMenuItemsList = listOf(
            DMPopularMenuItem(
                Constants.HOT_COFFEE,
                "https://iili.io/ZNfI3X.png"
            ),
            DMPopularMenuItem(
                Constants.HOT_TEA,
                "https://iili.io/ZNCcnR.png"
            ),
            DMPopularMenuItem(
                Constants.HOT_BEVERAGES,
                "https://iili.io/ZNoVhG.png"
            ),
            DMPopularMenuItem(
                Constants.COLD_BEVERAGES,
                "https://iili.io/ZNx3vt.png"
            ),
            DMPopularMenuItem(
                Constants.FRAPPUCCINO,
                "https://iili.io/ZNxu87.png"
            ),
            DMPopularMenuItem(
                Constants.BOTTLED_DRINKS,
                "https://iili.io/ZNxXAF.png"
            ),
            DMPopularMenuItem(
                Constants.BAKERY,
                "https://iili.io/ZNxLP4.png"
            ),
            DMPopularMenuItem(
                Constants.DESERTS,
                "https://iili.io/ZNzIWJ.png"
            ),
            DMPopularMenuItem(
                Constants.SALADS,
                "https://iili.io/ZNzaRt.png"
            ),
            DMPopularMenuItem(
                Constants.PACKED_FOODS,
                "https://iili.io/ZNzGsf.png"
            ),
            DMPopularMenuItem(
                Constants.CROISSANT,
                "https://iili.io/ZNz6dB.png"
            )
        )
        return flow {
            emit(popularMenuItemsList)
        }
    }

    override fun fetchStarbucksNews(): Flow<List<DMSocialNewsItem>> {
        val starbucksNewsList = listOf(
            DMSocialNewsItem(
                "https://iili.io/t8XPbn.png",
                "星巴克",
                "塔塔星巴克私人有限公司宣布於 2022 年 9 月在北阿坎德邦首府德拉敦開設新店。這次進軍新州彰顯了公司致力於在印度戰略性擴展其門店的承諾，目前星巴克在印度 35 個城市擁有 300 家門店。新店位於 Rajpur Road，為城市居民提供一個新的聚會場所，在享受標誌性的星巴克體驗的同時，共享他們最喜愛的一杯咖啡。\n" +
                        "\n" +
                        "“塔塔星巴克在印度有著令人難以置信的旅程，我們很榮幸在慶祝我們 10 週年之際，為德拉敦的挑剔顧客和充滿活力的社區創造一個溫馨的第三空間體驗。隨著我們擴展到新市場，我們的目標是為每位顧客提供獨特的星巴克體驗。” 塔塔星巴克私人有限公司執行長 Sushant Dash 表示。\n" +
                        "\n" +
                        "受德拉敦的遺產和當地建築啟發，兩家門店的整體氛圍既鄉村又現代。獨特的高天花板和拱形窗戶給空間帶來小木屋的感覺。溫暖的紅陶地板磚和牆面板與周圍環境投射的光影有趣地互動。充滿活力的定制掛毯慶祝自然，並融入了咖啡世界的植物和動物元素。\n" +
                        "\n" +
                        "新店將提供星巴克標誌性飲品和食品選擇，包括 Java Chip Frappuccino®、咖啡摩卡、招牌熱巧克力和焦糖瑪奇朵等經典之選。顧客還可以享用限時秋季特選，如南瓜香料拿鐵、南瓜香料星冰樂®、榛果摩卡起司蛋糕星冰樂®、榛果摩卡起司蛋糕拿鐵等。作為多種咖啡選項的搭配，還有一系列美味食品可供顧客享用，如全麥雞肉蛋白可頌、荷蘭松露蛋糕、紅絲絨與橙味蛋糕、辣起司吐司、羅勒番茄馬蘇里拉起司三明治、奶油可頌、卡克里烤肉捲等。\n" +
                        "\n" +
                        "門店還提供專屬星巴克商品和免費 Wi-Fi。此外，公司還將在城市推出“我的星巴克獎勵™”忠誠計劃，該計劃為會員提供獎勵和個性化福利，讓星巴克成為他們日常生活的一部分。"
            ),
            DMSocialNewsItem(
                "https://iili.io/t8h0mX.png",
                "10 週年紀念",
                "塔塔星巴克正在慶祝其成立 10 週年。\n" +
                        "\n" +
                        "經過 10 年的精心沖泡、服務和創造最佳咖啡體驗，星巴克印度慶祝其具有歷史意義的 10 週年。過去十年，星巴克印度已建立了強大的顧客和合作夥伴社群，並且確實正致力於觀察更多里程碑時刻的增長和擴展使命。\n" +
                        "\n" +
                        "為紀念這一特殊時刻，星巴克印度邀請其顧客參與#BrewYourOwnStarbucks 活動。作為其社區文化的延伸，他們旨在通過這項倡議加深與顧客的聯繫，將焦點放在他們的顧客身上。\n" +
                        "\n" +
                        "無論他們是否為咖啡品鑑家感到自豪，喜歡與咖啡飲品做實驗，還是單純喜愛一杯好咖啡，每位顧客都被邀請打造他們夢想中的飲品。通過創建自己的食譜，他們被鼓勵與品牌分享，有些甚至被列入星巴克印度的菜單，並可以在部分門店中看到。"
            )
        )

        return flow {
            emit(starbucksNewsList)
        }
    }

}
