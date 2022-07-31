# Diceware Generator

<div align="center">

![kotlin](https://img.shields.io/badge/Kotlin-7F52FF.svg?style=for-the-badge&logo=Kotlin&logoColor=222222)
![android](https://img.shields.io/badge/android-3DDC84.svg?style=for-the-badge&logo=android&logoColor=ffffff)
![license](https://img.shields.io/npm/l/vue-zooming-image?style=for-the-badge)

</div>

A tiny Android app to generate passphrases using the [Diceware method](https://theworld.com/~reinhold/diceware.html) based on the cryptographically strong random number generator [SecureRandom](https://docs.oracle.com/javase/8/docs/api/java/security/SecureRandom.html) and the [diceware8k wordlist](https://theworld.com/~reinhold/dicewarefaq.html#computer).

Diceware randomly picks a certain number of words (usually five or more) from a predefined list and concatenates them to form a secure and easily remembered passphrase. Additionally, a random character can be inserted into the passphrase to make it even more secure without adding another word.

![MainActivity screenshot](screenshots/Main.png)
