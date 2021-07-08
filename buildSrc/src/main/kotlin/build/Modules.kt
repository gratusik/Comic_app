package build

object Modules {
    const val APP = ":app"

    object Features {
        const val MAIN = ":features:main"
        const val COMIC_CARD = ":features:browse"
        const val COMIC_LIST = ":features:list"
        const val COMIC_MORE = ":features:more"
    }

    object Common {
        const val UI = ":commons:ui"
        const val CORE = ":commons:core"
//        const val TEST_LIBS = ":common:testLibs"
    }
}