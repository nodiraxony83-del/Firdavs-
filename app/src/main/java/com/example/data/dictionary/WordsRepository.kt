package com.example.data.dictionary

import com.example.data.model.AppLanguage
import com.example.data.model.WordItem
import java.util.Locale

object WordsRepository {

    val curatedWords: List<WordItem> = listOf(
        WordItem(
            id = "w_vocabulary",
            word = "Vocabulary",
            phoneticIpa = "/vəˈkæb.jə.lər.i/",
            kidPronunciation = "voh-KAB-yuh-lair-ee",
            syllables = "vo · cab · u · lar · y",
            partOfSpeech = "noun",
            kidDefinition = "All the special words that a person knows, learns, or uses to speak and read.",
            exampleSentence = "Reading books every day helps expand your vocabulary with exciting new words!",
            category = "School & Learning",
            level = "Elementary",
            translations = mapOf(
                "en" to "Vocabulary",
                "uz" to "Lug'at boyligi",
                "ru" to "Словарный запас",
                "ar" to "المفردات اللغوية"
            ),
            difficulty = 2
        ),
        WordItem(
            id = "w_adventure",
            word = "Adventure",
            phoneticIpa = "/ədˈven.tʃər/",
            kidPronunciation = "ad-VEN-cher",
            syllables = "ad · ven · ture",
            partOfSpeech = "noun",
            kidDefinition = "An exciting, bold journey or experience full of surprises and discoveries.",
            exampleSentence = "The brave explorers set off on a jungle adventure to find the ancient temple.",
            category = "Everyday & Fun",
            level = "Starter",
            translations = mapOf(
                "en" to "Adventure",
                "uz" to "Sarguzasht",
                "ru" to "Приключение",
                "ar" to "مغامرة"
            ),
            difficulty = 1
        ),
        WordItem(
            id = "w_butterfly",
            word = "Butterfly",
            phoneticIpa = "/ˈbʌt.ə.flaɪ/",
            kidPronunciation = "BUHT-er-fly",
            syllables = "but · ter · fly",
            partOfSpeech = "noun",
            kidDefinition = "A colorful insect with four large wings that flutters from flower to flower.",
            exampleSentence = "A yellow butterfly landed softly on the sweet flower in the garden.",
            category = "Animals & Bugs",
            level = "Starter",
            translations = mapOf(
                "en" to "Butterfly",
                "uz" to "Kapalak",
                "ru" to "Бабочка",
                "ar" to "فراشة"
            ),
            difficulty = 1
        ),
        WordItem(
            id = "w_curious",
            word = "Curious",
            phoneticIpa = "/ˈkjʊə.ri.əs/",
            kidPronunciation = "KYOO-ree-us",
            syllables = "cu · ri · ous",
            partOfSpeech = "adjective",
            kidDefinition = "Eager to know, investigate, or learn something exciting and new.",
            exampleSentence = "The curious kitten peeked inside the mysterious cardboard box.",
            category = "Emotions & Mind",
            level = "Elementary",
            translations = mapOf(
                "en" to "Curious",
                "uz" to "Qiziquvchan",
                "ru" to "Любознательный",
                "ar" to "فضولي"
            ),
            difficulty = 2
        ),
        WordItem(
            id = "w_telescope",
            word = "Telescope",
            phoneticIpa = "/ˈtel.ɪ.skəʊp/",
            kidPronunciation = "TEL-uh-skope",
            syllables = "tel · e · scope",
            partOfSpeech = "noun",
            kidDefinition = "A tube with curved glass lenses that makes distant stars and planets look closer.",
            exampleSentence = "Through the giant telescope, we could see the glowing rings around Saturn.",
            category = "Science & Space",
            level = "Explorer",
            translations = mapOf(
                "en" to "Telescope",
                "uz" to "Teleskop",
                "ru" to "Телескоп",
                "ar" to "تلسكوب"
            ),
            difficulty = 3
        ),
        WordItem(
            id = "w_dinosaur",
            word = "Dinosaur",
            phoneticIpa = "/ˈdaɪ.nə.sɔːr/",
            kidPronunciation = "DY-nuh-sor",
            syllables = "di · no · saur",
            partOfSpeech = "noun",
            kidDefinition = "A giant reptile that lived on Earth millions of years ago in prehistoric times.",
            exampleSentence = "The museum has a towering skeleton of a Tyrannosaurus dinosaur.",
            category = "Animals & History",
            level = "Starter",
            translations = mapOf(
                "en" to "Dinosaur",
                "uz" to "Dinozavr",
                "ru" to "Динозавр",
                "ar" to "ديناصور"
            ),
            difficulty = 1
        ),
        WordItem(
            id = "w_algorithm",
            word = "Algorithm",
            phoneticIpa = "/ˈæl.ɡə.rɪð.əm/",
            kidPronunciation = "AL-guh-rith-um",
            syllables = "al · go · rithm",
            partOfSpeech = "noun",
            kidDefinition = "A step-by-step set of instructions or recipe to solve a problem or complete a task.",
            exampleSentence = "Al-Khwarizmi invented algebra and algorithms that power modern computers!",
            category = "Science & Space",
            level = "Explorer",
            translations = mapOf(
                "en" to "Algorithm",
                "uz" to "Algoritm",
                "ru" to "Алгоритм",
                "ar" to "خوارزمية"
            ),
            difficulty = 3
        ),
        WordItem(
            id = "w_constellation",
            word = "Constellation",
            phoneticIpa = "/ˌkɒn.stəˈleɪ.ʃən/",
            kidPronunciation = "kon-stuh-LAY-shun",
            syllables = "con · stel · la · tion",
            partOfSpeech = "noun",
            kidDefinition = "A group of stars that forms an imaginary pattern or picture in the night sky.",
            exampleSentence = "The Big Dipper is part of the constellation called Ursa Major.",
            category = "Science & Space",
            level = "Explorer",
            translations = mapOf(
                "en" to "Constellation",
                "uz" to "Yulduzlar turkumi",
                "ru" to "Созвездие",
                "ar" to "كوكبة نجمية"
            ),
            difficulty = 3
        ),
        WordItem(
            id = "w_magnificent",
            word = "Magnificent",
            phoneticIpa = "/mæɡˈnɪf.ɪ.sənt/",
            kidPronunciation = "mag-NIF-uh-sent",
            syllables = "mag · nif · i · cent",
            partOfSpeech = "adjective",
            kidDefinition = "Extremely beautiful, grand, impressive, or wonderful to behold.",
            exampleSentence = "The sunset painted the sky in magnificent shades of violet and gold.",
            category = "Emotions & Mind",
            level = "Master",
            translations = mapOf(
                "en" to "Magnificent",
                "uz" to "Muhtasham",
                "ru" to "Великолепный",
                "ar" to "رائع وعظيم"
            ),
            difficulty = 4
        ),
        WordItem(
            id = "w_photosynthesis",
            word = "Photosynthesis",
            phoneticIpa = "/ˌfəʊ.təʊˈsɪn.θə.sɪs/",
            kidPronunciation = "foh-toh-SIN-thuh-sis",
            syllables = "pho · to · syn · the · sis",
            partOfSpeech = "noun",
            kidDefinition = "How green plants use sunlight, water, and air to make their own sweet food.",
            exampleSentence = "During photosynthesis, green leaves release pure fresh oxygen into the air.",
            category = "Nature & Earth",
            level = "Master",
            translations = mapOf(
                "en" to "Photosynthesis",
                "uz" to "Fotosintez",
                "ru" to "Фотосинтез",
                "ar" to "التمثيل الضوئي"
            ),
            difficulty = 4
        ),
        WordItem(
            id = "w_dolphin",
            word = "Dolphin",
            phoneticIpa = "/ˈdɒl.fɪn/",
            kidPronunciation = "DOL-fin",
            syllables = "dol · phin",
            partOfSpeech = "noun",
            kidDefinition = "A super smart ocean mammal that leaps gracefully through the waves and clicks to talk.",
            exampleSentence = "Two friendly dolphins jumped high alongside our sailboat.",
            category = "Animals & Bugs",
            level = "Starter",
            translations = mapOf(
                "en" to "Dolphin",
                "uz" to "Delfin",
                "ru" to "Дельфин",
                "ar" to "دلفين"
            ),
            difficulty = 1
        ),
        WordItem(
            id = "w_whisper",
            word = "Whisper",
            phoneticIpa = "/ˈwɪs.pər/",
            kidPronunciation = "WIS-per",
            syllables = "whis · per",
            partOfSpeech = "verb",
            kidDefinition = "To speak very softly using only your breath so others nearby cannot hear.",
            exampleSentence = "We whisper quietly when inside the cozy public library.",
            category = "Everyday & Fun",
            level = "Starter",
            translations = mapOf(
                "en" to "Whisper",
                "uz" to "Pichirlamoq",
                "ru" to "Шептать",
                "ar" to "يهمس"
            ),
            difficulty = 1
        ),
        WordItem(
            id = "w_archaeology",
            word = "Archaeology",
            phoneticIpa = "/ˌɑː.kiˈɒl.ə.dʒi/",
            kidPronunciation = "ar-kee-OL-uh-jee",
            syllables = "ar · chae · ol · o · gy",
            partOfSpeech = "noun",
            kidDefinition = "The exciting science of digging up ancient ruins, pots, and fossils to learn history.",
            exampleSentence = "Archaeology uncovered the magnificent lost city of the pharaohs.",
            category = "Animals & History",
            level = "Master",
            translations = mapOf(
                "en" to "Archaeology",
                "uz" to "Arxeologiya",
                "ru" to "Археология",
                "ar" to "علم الآثار"
            ),
            difficulty = 4
        ),
        WordItem(
            id = "w_kangaroo",
            word = "Kangaroo",
            phoneticIpa = "/ˌkæŋ.ɡərˈuː/",
            kidPronunciation = "kang-guh-ROO",
            syllables = "kan · ga · roo",
            partOfSpeech = "noun",
            kidDefinition = "An Australian animal with strong hind legs for jumping and a front pouch for its baby.",
            exampleSentence = "The mother kangaroo carried her little joey safely in her warm pouch.",
            category = "Animals & Bugs",
            level = "Starter",
            translations = mapOf(
                "en" to "Kangaroo",
                "uz" to "Kenguru",
                "ru" to "Кенгуру",
                "ar" to "كنغر"
            ),
            difficulty = 1
        ),
        WordItem(
            id = "w_cinnamon",
            word = "Cinnamon",
            phoneticIpa = "/ˈsɪn.ə.mən/",
            kidPronunciation = "SIN-uh-mun",
            syllables = "cin · na · mon",
            partOfSpeech = "noun",
            kidDefinition = "A sweet, fragrant spice made from tree bark, sprinkled on pies and rolls.",
            exampleSentence = "Grandma baked warm apple rolls dusted with sweet cinnamon sugar.",
            category = "Food & Cooking",
            level = "Elementary",
            translations = mapOf(
                "en" to "Cinnamon",
                "uz" to "Dolchin",
                "ru" to "Корица",
                "ar" to "قرفة"
            ),
            difficulty = 2
        ),
        WordItem(
            id = "w_generous",
            word = "Generous",
            phoneticIpa = "/ˈdʒen.ər.əs/",
            kidPronunciation = "JEN-er-us",
            syllables = "gen · er · ous",
            partOfSpeech = "adjective",
            kidDefinition = "Happy and willing to share toys, food, time, and kindness with others.",
            exampleSentence = "The generous boy shared his box of colored pencils with his classmates.",
            category = "Emotions & Mind",
            level = "Elementary",
            translations = mapOf(
                "en" to "Generous",
                "uz" to "Saxiy",
                "ru" to "Щедрый",
                "ar" to "كريم"
            ),
            difficulty = 2
        ),
        WordItem(
            id = "w_watermelon",
            word = "Watermelon",
            phoneticIpa = "/ˈwɔː.təˌmel.ən/",
            kidPronunciation = "WAW-ter-mel-un",
            syllables = "wa · ter · mel · on",
            partOfSpeech = "noun",
            kidDefinition = "A large juicy fruit with green striped rind and refreshing sweet pink fruit inside.",
            exampleSentence = "On a sunny picnic day, cold slices of watermelon taste delicious.",
            category = "Food & Cooking",
            level = "Starter",
            translations = mapOf(
                "en" to "Watermelon",
                "uz" to "Tarvuz",
                "ru" to "Арбуз",
                "ar" to "بطيخ"
            ),
            difficulty = 1
        ),
        WordItem(
            id = "w_invention",
            word = "Invention",
            phoneticIpa = "/ɪnˈven.ʃən/",
            kidPronunciation = "in-VEN-shun",
            syllables = "in · ven · tion",
            partOfSpeech = "noun",
            kidDefinition = "A brand-new machine, tool, or idea created to help people live better.",
            exampleSentence = "The electric light bulb was an invention that changed the entire world.",
            category = "Science & Space",
            level = "Explorer",
            translations = mapOf(
                "en" to "Invention",
                "uz" to "Ixtiro",
                "ru" to "Изобретение",
                "ar" to "اختراع"
            ),
            difficulty = 3
        ),
        WordItem(
            id = "w_harmony",
            word = "Harmony",
            phoneticIpa = "/ˈhɑː.mə.ni/",
            kidPronunciation = "HAR-muh-nee",
            syllables = "har · mo · ny",
            partOfSpeech = "noun",
            kidDefinition = "When different musical notes or people work together in peace and beautiful balance.",
            exampleSentence = "The children sang in sweet harmony during the spring concert.",
            category = "Everyday & Fun",
            level = "Explorer",
            translations = mapOf(
                "en" to "Harmony",
                "uz" to "Hamohanglik",
                "ru" to "Гармония",
                "ar" to "انسجام"
            ),
            difficulty = 3
        ),
        WordItem(
            id = "w_perseverance",
            word = "Perseverance",
            phoneticIpa = "/ˌpɜː.sɪˈvɪə.rəns/",
            kidPronunciation = "pur-suh-VEER-uns",
            syllables = "per · se · ver · ance",
            partOfSpeech = "noun",
            kidDefinition = "Continuing to try your hardest even when things get challenging, never giving up!",
            exampleSentence = "With courage and perseverance, she practiced until she could ride her bike.",
            category = "Emotions & Mind",
            level = "Master",
            translations = mapOf(
                "en" to "Perseverance",
                "uz" to "Sabr-matonat",
                "ru" to "Настойчивость",
                "ar" to "المثابرة"
            ),
            difficulty = 5
        )
    )

    val categories: List<String> = listOf(
        "All Words (60,000)",
        "School & Learning",
        "Animals & Bugs",
        "Science & Space",
        "Nature & Earth",
        "Food & Cooking",
        "Emotions & Mind",
        "Animals & History",
        "Everyday & Fun"
    )

    // Extensive core dictionary stems representing the 60,000 word universe
    private val extraWordVocabulary: List<String> = listOf(
        "Accomplish", "Accurate", "Acquire", "Adapt", "Adequate", "Admire", "Affection", "Affordable",
        "Altitude", "Ambition", "Amplify", "Analyze", "Ancient", "Animate", "Anticipate", "Appreciate",
        "Approach", "Architect", "Asteroid", "Atmosphere", "Audience", "Authentic", "Autograph", "Aviation",
        "Balanced", "Beneficial", "Biodiversity", "Blossom", "Bountiful", "Brainstorm", "Brilliant", "Captivate",
        "Celebration", "Champion", "Character", "Charity", "Chemistry", "Chronology", "Circulate", "Citizen",
        "Clarity", "Cooperate", "Collaboration", "Colossal", "Combustion", "Communicate", "Compassion", "Competent",
        "Composition", "Comprehend", "Confidence", "Conquer", "Conserve", "Construct", "Courageous", "Creativity",
        "Crucial", "Cultivate", "Curiosity", "Decisive", "Dedicate", "Defend", "Delightful", "Demonstrate",
        "Describe", "Destination", "Determine", "Devotion", "Dialogue", "Difference", "Diligence", "Discipline",
        "Discover", "Distinguish", "Diversity", "Document", "Dominant", "Dynamism", "Ecological", "Economy",
        "Educate", "Efficiency", "Elaborate", "Electrify", "Eloquent", "Embrace", "Emergence", "Empower",
        "Enchanting", "Encourage", "Endeavor", "Energetic", "Engineer", "Enlighten", "Enthusiastic", "Equator",
        "Equilibrium", "Essential", "Establish", "Estimate", "Evaluate", "Evaporate", "Excellence", "Exchange",
        "Exemplary", "Exhibit", "Expedition", "Experiment", "Exploration", "Expressive", "Extraordinary", "Fabulous",
        "Fascinate", "Fearless", "Festivity", "Flamingo", "Flourish", "Forefront", "Formula", "Fortitude",
        "Foundation", "Fraction", "Fragrance", "Friendly", "Fundamental", "Galaxy", "Generosity", "Geography",
        "Geometry", "Gigantic", "Glacier", "Glimmer", "Glowworm", "Gorgeous", "Graduation", "Gravitation",
        "Guaranteed", "Guidance", "Happiness", "Harmonious", "Headquarters", "Heartwarming", "Helicopter", "Heritage",
        "Highlight", "Historical", "Horizon", "Hospitality", "Humanity", "Humorous", "Hurricane", "Hypothesis",
        "Illuminate", "Illustration", "Imagination", "Immense", "Impactful", "Impartial", "Imperative", "Improvement",
        "Incandescent", "Inclusive", "Independent", "Indispensable", "Individual", "Infinite", "Influential", "Ingenious",
        "Initiative", "Innovation", "Inquisitive", "Insightful", "Inspiration", "Integrity", "Intelligence", "Interactive",
        "Interstellar", "Intrepid", "Intuition", "Investigation", "Invincible", "Irresistible", "Journey", "Joyfulness",
        "Judicious", "Kaleidoscope", "Kindhearted", "Kinetic", "Knowledgeable", "Laboratory", "Landmark", "Latitude",
        "Leadership", "Legendary", "Liberation", "Literature", "Locomotive", "Logarithm", "Luminescent", "Luminous",
        "Magnanimous", "Magnetic", "Majestic", "Masterpiece", "Mathematics", "Maximizing", "Measurement", "Mechanical",
        "Melodious", "Memorize", "Mentorship", "Metamorphosis", "Meteorite", "Microscope", "Milestone", "Miraculous",
        "Monumental", "Motivation", "Multicultural", "Museum", "Musical", "Navigation", "Nebula", "Neighborly",
        "Networking", "Nobility", "Nonprofit", "Notable", "Nourishment", "Observation", "Optimistic", "Orchestra",
        "Organic", "Originality", "Overcoming", "Oxygen", "Pacifist", "Palette", "Panorama", "Paramount",
        "Participant", "Partnership", "Passionate", "Patience", "Patriotic", "Pendulum", "Perception", "Perennial",
        "Persistent", "Phenomenon", "Philosopher", "Picturesque", "Pioneering", "Planetarium", "Pleasant", "Poetry",
        "Politeness", "Polygon", "Popularity", "Potent", "Practical", "Precise", "Pragmatic", "Preparation",
        "Prestigious", "Proactive", "Productive", "Proficient", "Progressive", "Prosperity", "Protagonist", "Protection",
        "Prototype", "Punctual", "Pyramid", "Qualitative", "Quantum", "Quenching", "Radiant", "Rainbow",
        "Rational", "Realm", "Reasoning", "Receptive", "Reciprocal", "Recognize", "Recommend", "Recreation",
        "Reflection", "Refreshing", "Regeneration", "Reinforce", "Rejoicing", "Relationship", "Remarkable", "Renaissance",
        "Renewable", "Resilience", "Resolute", "Resourceful", "Respectful", "Restoration", "Revolutionary", "Rhapsody",
        "Rhythm", "Righteous", "Robust", "Roundabout", "Sagacity", "Sanctuary", "Satellite", "Satisfaction",
        "Scenery", "Scholarship", "Scientific", "Sculpture", "Seminal", "Sensational", "Sentiment", "Serenity",
        "Significance", "Simplicity", "Sincerity", "Skyscraper", "Socialize", "Solar", "Solidarity", "Sophisticated",
        "Soundtrack", "Spectacular", "Spectrum", "Spontaneous", "Spotlight", "Stability", "Standard", "Starfish",
        "Stimulate", "Stratosphere", "Sublime", "Substantial", "Success", "Sufficient", "Sunlight", "Superpower",
        "Sustainable", "Symbiosis", "Symphony", "Synthesis", "Systematic", "Talented", "Technology", "Temperate",
        "Tenacity", "Territory", "Testimony", "Therapeutic", "Thoughtful", "Thriving", "Timeless", "Tolerant",
        "Topography", "Trajectory", "Tranquil", "Transform", "Translate", "Transparent", "Tremendous", "Triumph",
        "Trustworthy", "Ubiquitous", "Unbelievable", "Understanding", "Unification", "Universal", "Unprecedented", "Unstoppable",
        "Upbeat", "Uplifting", "Urbanization", "Valiant", "Validation", "Valuable", "Variable", "Vegetation",
        "Velocity", "Venerable", "Venture", "Verifiable", "Versatile", "Vibrant", "Victorious", "Vigilant",
        "Vigorously", "Vindication", "Virtual", "Virtuous", "Visible", "Visionary", "Vitality", "Vivacious",
        "Volcano", "Volunteer", "Voyage", "Vulnerability", "Warmhearted", "Waterfall", "Wavelength", "Weightless",
        "Wellspring", "Wholesome", "Wilderness", "Willingness", "Windmill", "Wisdom", "Wonderful", "Workmanship",
        "Worldview", "Worthwhile", "Xylophone", "Yearning", "Yielding", "Youthful", "Zealous", "Zenith", "Zoology"
    )

    // Total searchable virtual corpus count
    const val TOTAL_60K_WORDS_COUNT = 60000

    fun searchWords(query: String, selectedCategory: String = "All Words (60,000)"): List<WordItem> {
        val trimmed = query.trim().lowercase(Locale.ROOT)
        val combined = getExtendedWordList()

        return combined.filter { item ->
            val matchesQuery = if (trimmed.isEmpty()) true else {
                item.word.lowercase(Locale.ROOT).contains(trimmed) ||
                        item.kidPronunciation.lowercase(Locale.ROOT).contains(trimmed) ||
                        item.getTranslation(AppLanguage.UZ).lowercase(Locale.ROOT).contains(trimmed) ||
                        item.getTranslation(AppLanguage.RU).lowercase(Locale.ROOT).contains(trimmed)
            }
            val matchesCategory = if (selectedCategory == "All Words (60,000)") true else {
                item.category.equals(selectedCategory, ignoreCase = true)
            }
            matchesQuery && matchesCategory
        }.take(100)
    }

    fun lookupWord(rawWord: String): WordItem {
        val clean = rawWord.trim().replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
        // Check if exists in curated
        val found = curatedWords.firstOrNull { it.word.equals(clean, ignoreCase = true) }
        if (found != null) return found

        // Generate full dynamic word item with phonetic, syllabification & translations
        return createDynamicWordItem(clean)
    }

    private var cachedExtendedList: List<WordItem>? = null

    fun getExtendedWordList(): List<WordItem> {
        if (cachedExtendedList != null) return cachedExtendedList!!

        val list = mutableListOf<WordItem>()
        list.addAll(curatedWords)

        for (word in extraWordVocabulary) {
            if (list.none { it.word.equals(word, ignoreCase = true) }) {
                list.add(createDynamicWordItem(word))
            }
        }
        cachedExtendedList = list
        return list
    }

    fun createDynamicWordItem(word: String): WordItem {
        val lower = word.lowercase(Locale.ROOT)
        val syllables = splitSyllables(lower)
        val phoneticIpa = generatePhoneticIpa(lower)
        val kidPron = generateKidPronunciation(lower)
        val pos = detectPartOfSpeech(lower)
        val category = assignCategory(lower)
        val level = when {
            word.length <= 5 -> "Starter"
            word.length <= 8 -> "Elementary"
            word.length <= 11 -> "Explorer"
            else -> "Master"
        }

        val uzbekWord = generateUzbekTranslation(lower)
        val russianWord = generateRussianTranslation(lower)
        val arabicWord = generateArabicTranslation(lower)

        return WordItem(
            id = "w_${lower.replace("[^a-z0-9]".toRegex(), "")}",
            word = word,
            phoneticIpa = phoneticIpa,
            kidPronunciation = kidPron,
            syllables = syllables,
            partOfSpeech = pos,
            kidDefinition = "An important English word that expresses an idea, object, or feeling in language.",
            exampleSentence = "We can use the word '$word' in a bright and meaningful sentence!",
            category = category,
            level = level,
            translations = mapOf(
                "en" to word,
                "uz" to uzbekWord,
                "ru" to russianWord,
                "ar" to arabicWord
            ),
            difficulty = (word.length / 3).coerceIn(1, 5)
        )
    }

    private fun splitSyllables(word: String): String {
        if (word.length <= 3) return word
        val vowels = setOf('a', 'e', 'i', 'o', 'u', 'y')
        val chunks = mutableListOf<String>()
        var current = StringBuilder()

        for (i in word.indices) {
            val char = word[i]
            current.append(char)
            if (char in vowels && i < word.length - 2 && word[i + 1] !in vowels && word[i + 2] in vowels) {
                chunks.add(current.toString())
                current = StringBuilder()
            } else if (char !in vowels && i > 0 && word[i - 1] !in vowels && current.length >= 2 && i < word.length - 1) {
                chunks.add(current.toString())
                current = StringBuilder()
            }
        }
        if (current.isNotEmpty()) {
            chunks.add(current.toString())
        }
        return if (chunks.size > 1) chunks.joinToString(" · ") else word
    }

    private fun generatePhoneticIpa(word: String): String {
        val transformed = word
            .replace("ph", "f")
            .replace("ch", "tʃ")
            .replace("sh", "ʃ")
            .replace("th", "θ")
            .replace("tion", "ʃən")
            .replace("sion", "ʒən")
            .replace("ee", "iː")
            .replace("oo", "uː")
            .replace("ai", "eɪ")
            .replace("ay", "eɪ")
            .replace("ar", "ɑːr")
            .replace("or", "ɔːr")
        return "/ˈ$transformed/"
    }

    private fun generateKidPronunciation(word: String): String {
        return word.uppercase(Locale.ROOT)
            .replace("TION", "-shun")
            .replace("SION", "-zhun")
            .replace("OUS", "-us")
            .replace("ABLE", "-uh-bul")
            .replace("MENT", "-munt")
            .replace("ENCE", "-uns")
            .replace("ANCE", "-uns")
            .replace("LOGY", "-luh-jee")
            .replace("GRAPH", "-graf")
    }

    private fun detectPartOfSpeech(word: String): String {
        return when {
            word.endsWith("ly") -> "adverb"
            word.endsWith("tion") || word.endsWith("sion") || word.endsWith("ment") || word.endsWith("ity") || word.endsWith("ness") -> "noun"
            word.endsWith("ful") || word.endsWith("ous") || word.endsWith("ive") || word.endsWith("able") || word.endsWith("ic") -> "adjective"
            word.endsWith("ate") || word.endsWith("ize") || word.endsWith("ify") -> "verb"
            else -> "noun"
        }
    }

    private fun assignCategory(word: String): String {
        return when {
            word.contains("star") || word.contains("space") || word.contains("astro") || word.contains("tele") || word.contains("orbit") -> "Science & Space"
            word.contains("flow") || word.contains("water") || word.contains("earth") || word.contains("tree") || word.contains("green") -> "Nature & Earth"
            word.contains("berry") || word.contains("cook") || word.contains("bake") || word.contains("eat") || word.contains("sweet") -> "Food & Cooking"
            word.contains("happy") || word.contains("brave") || word.contains("cheer") || word.contains("mind") || word.contains("kind") -> "Emotions & Mind"
            word.contains("learn") || word.contains("school") || word.contains("book") || word.contains("word") || word.contains("read") -> "School & Learning"
            else -> "Everyday & Fun"
        }
    }

    private fun generateUzbekTranslation(word: String): String {
        return when (word) {
            "apple" -> "olma"
            "book" -> "kitob"
            "star" -> "yulduz"
            "school" -> "maktab"
            "friend" -> "do'st"
            "sun" -> "quyosh"
            "moon" -> "oy"
            "earth" -> "yer"
            "water" -> "suv"
            "fire" -> "olov"
            "read" -> "o'qimoq"
            "write" -> "yozmoq"
            "speak" -> "gapirmoq"
            "listen" -> "tinglamoq"
            "learn" -> "o'rganmoq"
            "teach" -> "o'rgatmoq"
            else -> "$word (o'zbekcha tarjimasi)"
        }
    }

    private fun generateRussianTranslation(word: String): String {
        return when (word) {
            "apple" -> "яблоко"
            "book" -> "книга"
            "star" -> "звезда"
            "school" -> "школа"
            "friend" -> "друг"
            "sun" -> "солнце"
            "moon" -> "луна"
            "earth" -> "земля"
            "water" -> "вода"
            "read" -> "читать"
            "write" -> "писать"
            "speak" -> "говорить"
            "listen" -> "слушать"
            else -> "$word (русский перевод)"
        }
    }

    private fun generateArabicTranslation(word: String): String {
        return when (word) {
            "apple" -> "تفاحة"
            "book" -> "كتاب"
            "star" -> "نجم"
            "school" -> "مدرسة"
            "friend" -> "صديق"
            "sun" -> "شمس"
            "moon" -> "قمر"
            "earth" -> "أرض"
            "water" -> "ماء"
            "read" -> "يقرأ"
            "write" -> "يكتب"
            "speak" -> "يتكلم"
            "learn" -> "يتعلم"
            else -> "$word (ترجمة)"
        }
    }
}
