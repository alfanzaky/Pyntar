package com.alfanro.pyntar.core.common.security

import org.mindrot.jbcrypt.BCrypt
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BCryptPasswordHasher @Inject constructor() : PasswordHasher {
    override fun hash(plain: String): String = BCrypt.hashpw(plain, BCrypt.gensalt(6))

    override fun verify(plain: String, hash: String): Boolean = BCrypt.checkpw(plain, hash)
}
