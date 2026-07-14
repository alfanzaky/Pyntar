package com.alfanro.pyntar.core.common.security

interface PasswordHasher {
    fun hash(plain: String): String
    fun verify(plain: String, hash: String): Boolean
}
