package com.beproud.domain.rds.creator

import org.springframework.data.jpa.repository.JpaRepository

interface CreatorRedisRepository : JpaRepository<Creator, Long> {
    fun findByWalletAddress(walletAddress: String): Creator?
}