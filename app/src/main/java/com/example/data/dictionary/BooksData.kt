package com.example.data.dictionary

import com.example.data.model.BookChapter
import com.example.data.model.HistoricalFigureBook

object BooksData {

    val historicalBooks: List<HistoricalFigureBook> = listOf(
        HistoricalFigureBook(
            id = "book_ibn_sina",
            name = "Ibn Sina (Avicenna)",
            epithet = "The Prince of Physicians",
            era = "980 – 1037 CE (Golden Age of Science)",
            country = "Bukhara (Central Asia)",
            coverSummary = "How a young boy who memorized vast libraries became the most famous doctor in world history, authoring The Canon of Medicine.",
            famousQuote = "The knowledge of anything, since all things have causes, is not complete or attained except through knowledge of its causes.",
            vocabularyWords = listOf("Physician", "Medicine", "Diagnosis", "Herbal", "Philosophy", "Scholar"),
            funFacts = listOf(
                "He memorized the entire Quran by the age of 10!",
                "His masterwork, The Canon of Medicine, was the main medical textbook across Europe and Asia for over 600 years.",
                "He discovered that contagious diseases could travel through air and water."
            ),
            chapters = listOf(
                BookChapter(
                    chapterNumber = 1,
                    title = "The Boy from Afshana",
                    content = "In the year 980 CE, in a village near the ancient oasis city of Bukhara, a bright-eyed boy named Abu Ali al-Husayn ibn Sina was born. While other children played outside, young Ibn Sina was captivated by scrolls, numbers, and nature. By age ten, he had read all the classic books in his father's house. Travelers and teachers were astonished by his phenomenal memory and deep curiosity.",
                    comprehensionQuestion = "Where was young Ibn Sina born?",
                    choices = listOf("Near Bukhara in Central Asia", "In Rome, Italy", "In London, England", "In Cairo, Egypt"),
                    correctChoice = 0
                ),
                BookChapter(
                    chapterNumber = 2,
                    title = "The Royal Library of Samanids",
                    content = "When the Sultan of Bukhara fell mysteriously ill, none of the court doctors could cure him. Sixteen-year-old Ibn Sina was summoned to the royal palace. Through patient observation and natural herbal remedies, he healed the Sultan! In gratitude, the Sultan unlocked the gates of the Grand Royal Library. Ibn Sina spent days and nights reading rare manuscripts on astronomy, mathematics, and medicine.",
                    comprehensionQuestion = "What reward did the Sultan give Ibn Sina for curing him?",
                    choices = listOf("A chest of gold coins", "Access to the Grand Royal Library", "A palace garden", "A silver chariot"),
                    correctChoice = 1
                ),
                BookChapter(
                    chapterNumber = 3,
                    title = "The Canon of Medicine",
                    content = "Throughout his travels across Silk Road cities like Ray, Isfahan, and Hamadan, Ibn Sina treated poor and rich patients alike. He wrote 'Al-Qanun fi al-Tibb' (The Canon of Medicine), an immense encyclopedia detailing 760 medicinal plants, pulse diagnosis, and surgical techniques. His compassionate spirit and brilliant mind illuminated the medieval world like a shining star.",
                    comprehensionQuestion = "What was the name of Ibn Sina's famous medical encyclopedia?",
                    choices = listOf("The Book of Stars", "The Canon of Medicine", "The Secret of Algebra", "The Ocean Voyage"),
                    correctChoice = 1
                )
            )
        ),
        HistoricalFigureBook(
            id = "book_al_khwarizmi",
            name = "Muhammad Al-Khwarizmi",
            epithet = "The Father of Algebra & Algorithms",
            era = "c. 780 – c. 850 CE",
            country = "Khwarazm (Central Asia)",
            coverSummary = "The visionary mathematician whose name gave us the word 'Algorithm' and whose book gave us 'Algebra'!",
            famousQuote = "When I consider what people generally want in calculating, I find that it is always a number.",
            vocabularyWords = listOf("Algorithm", "Algebra", "Calculation", "Astrolabe", "Equation", "Zero"),
            funFacts = listOf(
                "The mathematical word 'Algorithm' comes directly from his name, Al-Khwarizmi!",
                "The word 'Algebra' comes from 'al-jabr', meaning the reunion of broken parts in his book.",
                "He helped introduce the digit zero ('sifr') to the wider world."
            ),
            chapters = listOf(
                BookChapter(
                    chapterNumber = 1,
                    title = "The Sands of Khwarazm",
                    content = "Long ago, south of the Aral Sea along the great Amu Darya river, lay the prosperous land of Khwarazm. Here grew Muhammad ibn Musa al-Khwarizmi. He gazed at the stars and wondered how traders calculated distances, how builders measured giant minarets, and how astronomers predicted eclipses.",
                    comprehensionQuestion = "Near which river oasis was Al-Khwarizmi born?",
                    choices = listOf("Amu Darya in Khwarazm", "The River Thames", "The Amazon River", "The Danube"),
                    correctChoice = 0
                ),
                BookChapter(
                    chapterNumber = 2,
                    title = "The House of Wisdom",
                    content = "Recognizing his genius, the Caliph invited Al-Khwarizmi to the famed 'House of Wisdom' (Bayt al-Hikmah) in Baghdad. Scholars from every corner of the world gathered there. Al-Khwarizmi led the translation and improvement of mathematical works, inventing the method of solving balancing equations known as 'al-jabr'.",
                    comprehensionQuestion = "What grand center of science did Al-Khwarizmi join in Baghdad?",
                    choices = listOf("The House of Wisdom", "The Roman Colosseum", "The Iron Tower", "The Pyramids"),
                    correctChoice = 0
                ),
                BookChapter(
                    chapterNumber = 3,
                    title = "The Legacy of Algorithms",
                    content = "Al-Khwarizmi created precise astronomical tables and introduced the decimal positional number system (1 to 9 plus 0). Today, every smartphone, supercomputer, space shuttle, and internet network runs on step-by-step instructions called 'algorithms'—in honor of this brilliant scholar.",
                    comprehensionQuestion = "What modern computer term is named after Al-Khwarizmi?",
                    choices = listOf("Algorithm", "Internet", "Screen", "Battery"),
                    correctChoice = 0
                )
            )
        ),
        HistoricalFigureBook(
            id = "book_amir_timur",
            name = "Amir Timur (Tamerlane)",
            epithet = "The Master Builder of Samarkand",
            era = "1336 – 1405 CE",
            country = "Kesh / Samarkand (Central Asia)",
            coverSummary = "The legendary leader who transformed Samarkand into the pearl of the Silk Road with majestic turquoise domes and towering academies.",
            famousQuote = "If you want to know about our power and grandeur, look at our buildings!",
            vocabularyWords = listOf("Architecture", "Turquoise", "Grandeur", "Caravan", "Silk Road", "Monument"),
            funFacts = listOf(
                "His motto was 'Rasti Rusti' which means 'Strength lies in Justice'.",
                "He gathered the greatest architects, tile-makers, and painters to build the Registan and Gur-e-Amir.",
                "His grandson Ulugh Beg built one of the greatest astronomical observatories in history."
            ),
            chapters = listOf(
                BookChapter(
                    chapterNumber = 1,
                    title = "The Green City of Kesh",
                    content = "Born in the green oasis of Kesh (modern Shahrisabz), Timur grew up learning horseback riding, chess, and the languages of the Silk Road. He possessed an incredible strategic mind and an appreciation for poetry and great architecture.",
                    comprehensionQuestion = "In which city was Timur born?",
                    choices = listOf("Kesh (Shahrisabz)", "Paris", "Athens", "Kyoto"),
                    correctChoice = 0
                ),
                BookChapter(
                    chapterNumber = 2,
                    title = "Making Samarkand the Center of the World",
                    content = "Timur chose Samarkand as his capital. He declared: 'Let Samarkand be the most beautiful city on Earth!' He brought master stonemasons, ceramic artisans, calligraphers, and glassmakers. Soon, gigantic turquoise domes arose against the blue sky, with intricate geometric tile patterns that still dazzle visitors today.",
                    comprehensionQuestion = "What color are the famous majestic domes of Samarkand?",
                    choices = listOf("Turquoise blue", "Bright red", "Jet black", "Silver gray"),
                    correctChoice = 0
                )
            )
        ),
        HistoricalFigureBook(
            id = "book_leonardo",
            name = "Leonardo da Vinci",
            epithet = "The Renaissance Polymath",
            era = "1452 – 1519 CE",
            country = "Vinci & Florence (Italy)",
            coverSummary = "Painter of the Mona Lisa, dreamer of helicopters and submarines, and one of the most creatively curious people in human history.",
            famousQuote = "Learning never exhausts the mind.",
            vocabularyWords = listOf("Renaissance", "Invention", "Polymath", "Sketchbook", "Perspective", "Curiosity"),
            funFacts = listOf(
                "He wrote his personal journals backwards in mirror writing!",
                "He drew detailed sketches of flying gliders and parachutes 400 years before planes were invented.",
                "He loved animals so much that he bought caged birds in markets just to set them free."
            ),
            chapters = listOf(
                BookChapter(
                    chapterNumber = 1,
                    title = "The Boy Who Observed Birds",
                    content = "In the rolling green hills of Tuscany, Italy, lived young Leonardo. He carried notebook pages everywhere he went. He watched how water swirled in streams, how dragonfly wings fluttered, and how shadows fell across tree trunks. To him, nature was the supreme teacher.",
                    comprehensionQuestion = "What did young Leonardo love to observe in Tuscany?",
                    choices = listOf("Water swirls and bird wings in nature", "Steam engines", "Video games", "Subways"),
                    correctChoice = 0
                ),
                BookChapter(
                    chapterNumber = 2,
                    title = "The Art of Living",
                    content = "Leonardo moved to Florence to study under Master Verrocchio. He mastered painting, sculpture, and mechanics. He painted the famous 'Mona Lisa' with her enigmatic smile and 'The Last Supper'. But his mind never stopped inventing: he drew plans for armored vehicles, hydraulic pumps, and mechanical lions.",
                    comprehensionQuestion = "Which world-famous painting did Leonardo create?",
                    choices = listOf("Mona Lisa", "Starry Night", "The Scream", "Guernica"),
                    correctChoice = 0
                )
            )
        ),
        HistoricalFigureBook(
            id = "book_marie_curie",
            name = "Marie Curie",
            epithet = "Pioneer of Radioactivity",
            era = "1867 – 1934 CE",
            country = "Warsaw (Poland) & Paris (France)",
            coverSummary = "The fearless scientist who discovered radium and polonium, becoming the first person ever to win two Nobel Prizes in two different sciences!",
            famousQuote = "Nothing in life is to be feared, it is only to be understood.",
            vocabularyWords = listOf("Radioactivity", "Laboratory", "Radium", "Nobel Prize", "Experiment", "Perseverance"),
            funFacts = listOf(
                "She is the ONLY person to win Nobel Prizes in two different scientific fields: Physics and Chemistry!",
                "During World War I, she drove mobile X-ray vans to the front lines to help save wounded soldiers.",
                "The element 'Polonium' was named after her beloved homeland, Poland."
            ),
            chapters = listOf(
                BookChapter(
                    chapterNumber = 1,
                    title = "A Dream in Warsaw",
                    content = "Maria Sklodowska was born in Warsaw, Poland. She loved science and books with all her heart. Even though universities in her city did not admit women at that time, Maria studied secretly in the 'Floating University' at night, saving every penny to attend the Sorbonne University in Paris.",
                    comprehensionQuestion = "What was Marie Curie's original birth country?",
                    choices = listOf("Poland", "Canada", "Australia", "Brazil"),
                    correctChoice = 0
                ),
                BookChapter(
                    chapterNumber = 2,
                    title = "The Glowing Discovery",
                    content = "In a cold, drafty wooden shed in Paris, Marie and her husband Pierre stirred heavy cauldrons of pitchblende ore for years. Through relentless perseverance, they discovered two brand new elements: Polonium and Radium, which glowed with an eerie blue light in the dark. Her courage opened the modern atomic age.",
                    comprehensionQuestion = "What glowing element did Marie Curie discover?",
                    choices = listOf("Radium", "Plastic", "Aluminum", "Helium"),
                    correctChoice = 0
                )
            )
        ),
        HistoricalFigureBook(
            id = "book_thomas_edison",
            name = "Thomas Edison",
            epithet = "The Wizard of Menlo Park",
            era = "1847 – 1931 CE",
            country = "United States",
            coverSummary = "The persistent inventor who tested thousands of materials before perfecting the long-lasting incandescent light bulb.",
            famousQuote = "Genius is one percent inspiration and ninety-nine percent perspiration.",
            vocabularyWords = listOf("Incandescent", "Filament", "Phonograph", "Perspiration", "Laboratory", "Patent"),
            funFacts = listOf(
                "He filed over 1,093 U.S. patents during his lifetime!",
                "When asked about failing thousands of times to find a lightbulb filament, he said: 'I have not failed. I've just found 10,000 ways that won't work.'",
                "His first successful recording on the phonograph was reciting 'Mary Had a Little Lamb'."
            ),
            chapters = listOf(
                BookChapter(
                    chapterNumber = 1,
                    title = "The Inquisitive Boy",
                    content = "Young 'Al' Edison was constantly asking questions: 'Why is the sky blue? How does a telegraph tick?' When schoolteachers couldn't keep up with his nonstop curiosity, his caring mother homeschooled him. He converted the basement into a chemistry lab with jars labeled 'Poison' so nobody would touch his experiments!",
                    comprehensionQuestion = "Who homeschooled young Thomas Edison after teachers found him too curious?",
                    choices = listOf("His mother", "His uncle", "A ship captain", "His older brother"),
                    correctChoice = 0
                ),
                BookChapter(
                    chapterNumber = 2,
                    title = "Lighting Up the World",
                    content = "In his Menlo Park laboratory, Edison aimed to create safe, affordable electric light for every home. After trying carbonized cardboard, platinum, and thousands of plant fibers, his team discovered that a carbonized bamboo filament could glow steadily for over 1,200 hours. The era of dark nights was changed forever!",
                    comprehensionQuestion = "What plant material made Edison's long-lasting lightbulb filament work?",
                    choices = listOf("Carbonized bamboo", "Oak bark", "Cotton flower", "Rose petal"),
                    correctChoice = 0
                )
            )
        )
    )
}
