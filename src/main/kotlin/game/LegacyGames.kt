package game

data class GntLegacyStats(val message: String) {
    val deaths: Int = 0
    val kills: Int = 0
    val place: Int = 0
    val points: Int = 0
    val played: Int = 0
    val victories: Int = 0
    val username: String = "Legacy_Game"
    val UUID: String = "cafebabe";
}

data class CaiLegacyStats(val message: String) {
    val caught: Int = 0
    val captures: Int = 0
    val place: Int = 0
    val points: Int = 0
    val played: Int = 0
    val victories: Int = 0
    val username: String = "Legacy_Game"
    val UUID: String = "cafebabe";
}