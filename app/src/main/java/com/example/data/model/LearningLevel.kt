package com.example.data.model

data class LearningLevel(
    val levelNumber: Int,
    val title: String,
    val badgeIcon: String,
    val minPoints: Int,
    val maxPoints: Int,
    val totalPoints: Int,
    val pointsInCurrentLevel: Int,
    val pointsNeededForNext: Int,
    val progressPercent: Float,
    val badges: List<MilestoneBadge> = emptyList()
)

data class MilestoneBadge(
    val id: String,
    val title: String,
    val icon: String,
    val targetLevel: Int,
    val requiredPoints: Int,
    val description: String,
    val perkTitle: String,
    val isUnlocked: Boolean,
    val progress: Float
)

object LearningLevelCalculator {
    private data class LevelThreshold(
        val number: Int,
        val title: String,
        val icon: String,
        val min: Int,
        val max: Int
    )

    private val levels = listOf(
        LevelThreshold(1, "Sprout Explorer", "🌱", 0, 99),
        LevelThreshold(2, "Word Discoverer", "🔍", 100, 249),
        LevelThreshold(3, "Grammar Cadet", "📘", 250, 449),
        LevelThreshold(4, "Sentence Builder", "🏗️", 450, 699),
        LevelThreshold(5, "Pronunciation Pro", "🎙️", 700, 999),
        LevelThreshold(6, "History Scholar", "📜", 1000, 1399),
        LevelThreshold(7, "Intellect Champion", "🌟", 1400, 1899),
        LevelThreshold(8, "Grand Master Linguist", "👑", 1900, 3000)
    )

    private val badgeDefinitions = listOf(
        Triple("badge_sprout", "Sprout Pioneer", "🌱") to Triple(1, 0, "Began the Fdego adventure and discovered first English words."),
        Triple("badge_voyager", "Word Voyager", "🔍") to Triple(2, 100, "Reached Level 2! Unlocked 100+ vocabulary experience points."),
        Triple("badge_grammar", "Grammar Knight", "🛡️") to Triple(3, 250, "Mastered parts of speech, verbs, and magic plurals."),
        Triple("badge_architect", "Sentence Architect", "🏛️") to Triple(4, 450, "Constructed rich sentences and expressive descriptions."),
        Triple("badge_voice", "Golden Voice", "🎙️") to Triple(5, 700, "Delivered high-accuracy voice pronunciation in speaking lab."),
        Triple("badge_history", "History Scholar", "📜") to Triple(6, 1000, "Explored the lives of Ibn Sina, Al-Khwarizmi, and historical geniuses."),
        Triple("badge_intellect", "Intellect Crown", "🌟") to Triple(7, 1400, "Challenged the Sony Intellect AI and solved complex questions."),
        Triple("badge_legend", "Fdego Legend", "👑") to Triple(8, 1900, "Reached Grand Master Linguist across the entire 60,000-word universe!")
    )

    fun getMilestones(points: Int): List<MilestoneBadge> {
        val safePoints = points.coerceAtLeast(0)
        return badgeDefinitions.map { (info, req) ->
            val (id, title, icon) = info
            val (targetLevel, requiredPts, desc) = req
            val isUnlocked = safePoints >= requiredPts
            val progress = if (requiredPts == 0) 1f else (safePoints.toFloat() / requiredPts.toFloat()).coerceIn(0f, 1f)
            val perk = when (targetLevel) {
                1 -> "Starter Badge • Level 1"
                2 -> "Explorer Rank • Level 2"
                3 -> "Grammar Ace • Level 3"
                4 -> "Builder Rank • Level 4"
                5 -> "Voice Virtuoso • Level 5"
                6 -> "Sage of History • Level 6"
                7 -> "AI Intellect Star • Level 7"
                else -> "Master of 60,000 Words • Level 8"
            }
            MilestoneBadge(
                id = id,
                title = title,
                icon = icon,
                targetLevel = targetLevel,
                requiredPoints = requiredPts,
                description = desc,
                perkTitle = perk,
                isUnlocked = isUnlocked,
                progress = progress
            )
        }
    }

    fun calculate(points: Int): LearningLevel {
        val safePoints = points.coerceAtLeast(0)
        val currentTier = levels.find { safePoints in it.min..it.max } ?: levels.last()
        val span = (currentTier.max - currentTier.min) + 1
        val pointsIntoTier = safePoints - currentTier.min
        val fraction = (pointsIntoTier.toFloat() / span.toFloat()).coerceIn(0f, 1f)
        val needed = (currentTier.max + 1 - safePoints).coerceAtLeast(0)
        val badges = getMilestones(safePoints)

        return LearningLevel(
            levelNumber = currentTier.number,
            title = currentTier.title,
            badgeIcon = currentTier.icon,
            minPoints = currentTier.min,
            maxPoints = currentTier.max,
            totalPoints = safePoints,
            pointsInCurrentLevel = pointsIntoTier,
            pointsNeededForNext = needed,
            progressPercent = fraction,
            badges = badges
        )
    }
}
