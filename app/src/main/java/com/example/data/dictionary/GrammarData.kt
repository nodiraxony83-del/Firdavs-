package com.example.data.dictionary

import com.example.data.model.GrammarExercise
import com.example.data.model.GrammarTopic

object GrammarData {

    val topics: List<GrammarTopic> = listOf(
        GrammarTopic(
            id = "g_nouns_plurals",
            title = "Nouns & Magic Plurals",
            description = "Naming people, places, animals, and things, plus making them plural!",
            kidRule = "A noun is a naming word. To talk about more than one, usually add 's' or 'es'!",
            formula = "One cat 🐱 ➔ Two cats 🐱🐱  |  One box 📦 ➔ Three boxes 📦📦📦",
            examples = listOf(
                "One apple" to "Three apples (add -s)",
                "One watch" to "Two watches (ends in -ch, add -es)",
                "One puppy" to "Two puppies (change -y to -ies)",
                "One child" to "Three children (magic irregular plural!)"
            ),
            exercises = listOf(
                GrammarExercise(
                    id = "ex_noun_1",
                    questionText = "What is the plural of 'fox'?",
                    options = listOf("foxes", "foxs", "foxies", "foxen"),
                    correctIndex = 0,
                    explanation = "Words ending in -x add '-es' to form the plural: fox ➔ foxes!"
                ),
                GrammarExercise(
                    id = "ex_noun_2",
                    questionText = "Which of these words is a NOUN (naming word)?",
                    options = listOf("Quickly", "Elephant", "Sing", "Blue"),
                    correctIndex = 1,
                    explanation = "'Elephant' is an animal noun. 'Sing' is a verb and 'Blue' is an adjective."
                ),
                GrammarExercise(
                    id = "ex_noun_3",
                    questionText = "Look at the magic word 'child'. What is the plural?",
                    options = listOf("childs", "childes", "children", "childer"),
                    correctIndex = 2,
                    explanation = "'Child' is an irregular noun: one child, many children!"
                )
            )
        ),
        GrammarTopic(
            id = "g_action_verbs",
            title = "Action Verbs & Present Simple",
            description = "Words that show what someone is doing: run, jump, read, sleep!",
            kidRule = "Verbs are action words. When talking about He, She, or It in present time, add 's' to the verb!",
            formula = "I read 📖  ➔  She reads 📖  |  They jump 🤸  ➔  He jumps 🤸",
            examples = listOf(
                "I eat breakfast" to "He eats breakfast",
                "We play soccer" to "She plays soccer",
                "Birds fly in the sky" to "The eagle flies high",
                "You smile" to "The baby smiles"
            ),
            exercises = listOf(
                GrammarExercise(
                    id = "ex_verb_1",
                    questionText = "Choose the correct verb: 'The dog _____ in the park.'",
                    options = listOf("barks", "bark", "barking is", "barked tomorrow"),
                    correctIndex = 0,
                    explanation = "Because 'The dog' is singular (it), we say 'The dog barks'!"
                ),
                GrammarExercise(
                    id = "ex_verb_2",
                    questionText = "Which sentence uses the verb correctly?",
                    options = listOf("He play chess.", "He plays chess.", "He playing chess.", "He to play chess."),
                    correctIndex = 1,
                    explanation = "With 'He', we add -s to 'play', making 'He plays chess.'"
                ),
                GrammarExercise(
                    id = "ex_verb_3",
                    questionText = "Which word is an ACTION verb?",
                    options = listOf("Castle", "Wonderful", "Explore", "Tomorrow"),
                    correctIndex = 2,
                    explanation = "'Explore' is an action you can do with your mind and body!"
                )
            )
        ),
        GrammarTopic(
            id = "g_adjectives",
            title = "Super Adjectives & Comparatives",
            description = "Describing colors, sizes, feelings, and comparing things!",
            kidRule = "Adjectives describe nouns. To compare two things, add '-er'. For the highest degree, add '-est'!",
            formula = "Fast 🏃 ➔ Faster 🏎️ ➔ Fastest 🚀",
            examples = listOf(
                "Bright star" to "The star is bright and shiny",
                "Tall giraffe" to "Taller than a zebra",
                "Warm tea" to "Warmer soup",
                "Happy puppy" to "Happiest of all"
            ),
            exercises = listOf(
                GrammarExercise(
                    id = "ex_adj_1",
                    questionText = "A blue whale is the _____ animal in the ocean.",
                    options = listOf("biggest", "bigger", "big", "most big"),
                    correctIndex = 0,
                    explanation = "When comparing among all animals in the ocean, use the superlative: 'biggest'!"
                ),
                GrammarExercise(
                    id = "ex_adj_2",
                    questionText = "An airplane is _____ than a bicycle.",
                    options = listOf("faster", "fastest", "fast", "more fast"),
                    correctIndex = 0,
                    explanation = "When comparing two things (airplane vs bicycle), use comparative: 'faster'!"
                )
            )
        ),
        GrammarTopic(
            id = "g_pronouns",
            title = "Friendly Pronouns",
            description = "Words that replace nouns so we don't repeat names constantly!",
            kidRule = "Use 'He' for a boy, 'She' for a girl, 'It' for things/animals, and 'They' for groups!",
            formula = "Ali is reading ➔ He is reading  |  Lina is smiling ➔ She is smiling",
            examples = listOf(
                "The book is on the desk" to "It is on the desk",
                "Nodir and I are studying" to "We are studying",
                "The students are singing" to "They are singing"
            ),
            exercises = listOf(
                GrammarExercise(
                    id = "ex_pro_1",
                    questionText = "Replace 'Sara' with a pronoun: '_____ loves reading stories.'",
                    options = listOf("She", "He", "They", "It"),
                    correctIndex = 0,
                    explanation = "Sara is a girl, so we use 'She'!"
                ),
                GrammarExercise(
                    id = "ex_pro_2",
                    questionText = "'Tom and Jerry are funny. _____ make us laugh.'",
                    options = listOf("He", "She", "They", "We"),
                    correctIndex = 2,
                    explanation = "For two other characters together, use the plural pronoun 'They'!"
                )
            )
        ),
        GrammarTopic(
            id = "g_prepositions",
            title = "Prepositions of Place",
            description = "Words showing where an object or animal is hiding!",
            kidRule = "Prepositions like 'in', 'on', 'under', 'next to' tell us the exact location!",
            formula = "The ball is IN the box 📦⚽  |  The cat is ON the mat 🐱🧶",
            examples = listOf(
                "In" to "The pencil is in the pencil case",
                "On" to "The apple is on the wooden table",
                "Under" to "The puppy is sleeping under the cozy bed",
                "Behind" to "The sun came out from behind the cloud"
            ),
            exercises = listOf(
                GrammarExercise(
                    id = "ex_prep_1",
                    questionText = "The fish swims _____ the clear water.",
                    options = listOf("in", "on", "underneath of", "at"),
                    correctIndex = 0,
                    explanation = "Fish swim INSIDE ('in') the water."
                ),
                GrammarExercise(
                    id = "ex_prep_2",
                    questionText = "The boy is sitting _____ the oak tree shade.",
                    options = listOf("under", "between", "off", "into"),
                    correctIndex = 0,
                    explanation = "People sit 'under' the shade of a tall tree."
                )
            )
        )
    )
}
