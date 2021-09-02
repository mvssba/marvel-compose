package br.com.marcos2silva.network.di

import java.math.BigInteger
import java.security.MessageDigest

private const val ALGORITHM = "MD5"

fun String.md5(): String =
    BigInteger(
        1, MessageDigest.getInstance(ALGORITHM)
            .digest(toByteArray())
    ).toString(16).padStart(32, '0')