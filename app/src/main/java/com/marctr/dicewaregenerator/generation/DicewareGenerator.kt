package com.marctr.dicewaregenerator.generation

import android.content.Context
import java.security.SecureRandom
import com.marctr.dicewaregenerator.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DicewareGenerator(private val ctx: Context) {

    private lateinit var dicewareWords: Array<String?>
    private var extraChars = charArrayOf(
        '~', '!', '#', '$', '%', '^', '&', '*', '(', ')', '-', '=', '+', '[', ']', '\\', '{', '}',
        ':', ';', '"', '\'', '<', '>', '?', '/', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    )

    suspend fun init() {
        withContext(Dispatchers.IO) {
            val words = arrayListOf<String>()
            val inputStream = ctx.resources.openRawResource(R.raw.diceware8k)
            inputStream.bufferedReader().useLines { lines ->
                lines.forEach { line ->
                    words.add(line)
                }
                dicewareWords = words.toTypedArray()
            }
        }
    }

    fun generatePassphrase(numberOfWords: Int, insertRandomChar: Boolean): String {
        val randomNum = SecureRandom()
        var passphraseWords = Array(numberOfWords) {""}
        for(x in 0 until numberOfWords) {
            // get random word
            val wordIndex = randomNum.nextInt(dicewareWords.size)
            passphraseWords[x] = dicewareWords[wordIndex]!!
        }

        if(insertRandomChar) {
            passphraseWords = insertRandomChar(passphraseWords)
        }

        return passphraseWords.joinToString(separator = " ")
    }

    private fun insertRandomChar(words: Array<String>) : Array<String> {
        // throw one die to choose a word from the passphrase
        val randomNum = SecureRandom()
        val wordIndex = randomNum.nextInt(words.size) // [0 - words.size)

        // throw one die to choose a letter in that word
        val word = words[wordIndex]
        val letterIndex = randomNum.nextInt(word.length) // [0 - word.length)

        // get a random character to insert
        val randomCharIndex = randomNum.nextInt(extraChars.size)
        val randomChar = extraChars[randomCharIndex]

        // insert the random character after the selected letter
        // zero means put the random character at the beginning of the word
        val wordWithSpecialChar = StringBuilder()
            .append(word)
            .insert(letterIndex, randomChar)
            .toString()
        words[wordIndex] = wordWithSpecialChar
        return words
    }
}