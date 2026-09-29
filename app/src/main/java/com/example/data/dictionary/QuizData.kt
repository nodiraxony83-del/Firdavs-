package com.example.data.dictionary

import com.example.data.model.QuizQuestion

object QuizData {

    data class TestSuite(
        val id: String,
        val title: String,
        val category: String,
        val description: String,
        val iconName: String,
        val questions: List<QuizQuestion>
    )

    val testSuites: List<TestSuite> = listOf(
        TestSuite(
            id = "test_vocab_basics",
            title = "Super Vocabulary Challenge",
            category = "Vocabulary",
            description = "Test your knowledge of words, meanings, and word power!",
            iconName = "menu_book",
            questions = listOf(
                QuizQuestion(
                    id = "q_v1",
                    question = "What does the word 'Vocabulary' mean?",
                    audioWord = "Vocabulary",
                    options = listOf(
                        "All the words a person knows and learns",
                        "A type of fast musical instrument",
                        "A spaceship heading towards Mars",
                        "A tasty recipe made with chocolate"
                    ),
                    correctIndex = 0,
                    explanation = "Vocabulary refers to the complete set of words you know and use!"
                ),
                QuizQuestion(
                    id = "q_v2",
                    question = "Which word describes someone who loves to investigate and learn new things?",
                    audioWord = "Curious",
                    options = listOf("Sleepy", "Curious", "Heavy", "Cold"),
                    correctIndex = 1,
                    explanation = "'Curious' means having a strong desire to learn or know things."
                ),
                QuizQuestion(
                    id = "q_v3",
                    question = "A tool with special glass lenses used to look at distant stars is called a:",
                    audioWord = "Telescope",
                    options = listOf("Microscope", "Telescope", "Periscope", "Thermometer"),
                    correctIndex = 1,
                    explanation = "A telescope brings faraway stars and galaxies into clear view!"
                ),
                QuizQuestion(
                    id = "q_v4",
                    question = "What is an 'Algorithm'?",
                    audioWord = "Algorithm",
                    options = listOf(
                        "A step-by-step set of instructions to solve a problem",
                        "A species of tropical tree",
                        "A type of bicycle wheel",
                        "A musical flute"
                    ),
                    correctIndex = 0,
                    explanation = "An algorithm is a step-by-step procedure for calculation and problem-solving!"
                ),
                QuizQuestion(
                    id = "q_v5",
                    question = "Which word means 'never giving up even when things get difficult'?",
                    audioWord = "Perseverance",
                    options = listOf("Silence", "Perseverance", "Hesitation", "Distance"),
                    correctIndex = 1,
                    explanation = "Perseverance means steadfast determination to achieve your goal!"
                )
            )
        ),
        TestSuite(
            id = "test_listening_voice",
            title = "Listening & Pronunciation Ear Test",
            category = "Listening",
            description = "Listen closely to the audio pronunciation and identify the correct word!",
            iconName = "hearing",
            questions = listOf(
                QuizQuestion(
                    id = "q_l1",
                    question = "Listen to the voice. Which word was spoken?",
                    audioWord = "Butterfly",
                    options = listOf("Dragonfly", "Butterfly", "Firefly", "Caterpillar"),
                    correctIndex = 1,
                    explanation = "The audio pronounced 'Butterfly' with three rhythmic syllables: but · ter · fly."
                ),
                QuizQuestion(
                    id = "q_l2",
                    question = "Listen to the pronunciation. Which word is this?",
                    audioWord = "Adventure",
                    options = listOf("Advantage", "Adventure", "Avenue", "Adviser"),
                    correctIndex = 1,
                    explanation = "The voice pronounced 'Adventure' (/ədˈven.tʃər/)!"
                ),
                QuizQuestion(
                    id = "q_l3",
                    question = "Listen to the word spoken by the app:",
                    audioWord = "Magnificent",
                    options = listOf("Beneficent", "Magnificent", "Munificent", "Significant"),
                    correctIndex = 1,
                    explanation = "'Magnificent' means extremely grand and beautiful."
                ),
                QuizQuestion(
                    id = "q_l4",
                    question = "Listen carefully to this animal name:",
                    audioWord = "Kangaroo",
                    options = listOf("Koala", "Kangaroo", "Kiwi", "Camel"),
                    correctIndex = 1,
                    explanation = "The app pronounced 'Kangaroo', the famous hopping marsupial!"
                )
            )
        ),
        TestSuite(
            id = "test_history_minds",
            title = "Historical Geniuses & Books Quiz",
            category = "Reading & History",
            description = "How well do you know Ibn Sina, Al-Khwarizmi, Leonardo, and Edison?",
            iconName = "auto_stories",
            questions = listOf(
                QuizQuestion(
                    id = "q_h1",
                    question = "Who wrote the monumental medical encyclopedia 'The Canon of Medicine'?",
                    audioWord = "Avicenna",
                    options = listOf("Ibn Sina (Avicenna)", "Thomas Edison", "Alexander", "Leonardo"),
                    correctIndex = 0,
                    explanation = "Ibn Sina wrote The Canon of Medicine, which was studied for over six centuries!"
                ),
                QuizQuestion(
                    id = "q_h2",
                    question = "Which scholar from Khwarazm gave us the word 'Algorithm'?",
                    audioWord = "Algorithm",
                    options = listOf("Al-Khwarizmi", "Pythagoras", "Galileo", "Newton"),
                    correctIndex = 0,
                    explanation = "Muhammad ibn Musa al-Khwarizmi is the father of algorithms and algebra!"
                ),
                QuizQuestion(
                    id = "q_h3",
                    question = "Who is the ONLY scientist to win two Nobel Prizes in two different sciences?",
                    audioWord = "Scientist",
                    options = listOf("Albert Einstein", "Marie Curie", "Alexander Fleming", "Nikola Tesla"),
                    correctIndex = 1,
                    explanation = "Marie Curie won Nobel Prizes in both Physics and Chemistry!"
                ),
                QuizQuestion(
                    id = "q_h4",
                    question = "Which city did Amir Timur transform into a jewel with magnificent turquoise domes?",
                    audioWord = "Architecture",
                    options = listOf("Samarkand", "Athens", "Venice", "Cairo"),
                    correctIndex = 0,
                    explanation = "Amir Timur built Samarkand with world-renowned turquoise-domed mosques and madrasahs!"
                )
            )
        )
    )
}
