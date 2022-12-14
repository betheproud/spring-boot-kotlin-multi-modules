package com.beproud.appapi.connect.v1

import com.beproud.appapi.connect.v1.dto.ConnectRequest
import com.beproud.appapi.connect.v1.dto.ConnectResponse
import com.beproud.appapi.user.v1.UserDomainService
import com.beproud.auth.JwtTokenProvider
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

private val logger = KotlinLogging.logger {}

@Service
class ConnectService(
    private val jwtTokenProvider: JwtTokenProvider,
    private val userDomainService: UserDomainService,
) {
    @Transactional
    fun connect(request: ConnectRequest): ConnectResponse {
        val user = userDomainService.createIfNotExist(request)
        logger.info("connect user: ${user.walletAddress}")
        val token = jwtTokenProvider.generateToken(walletAddress = request.walletAddress)
        val refreshToken = jwtTokenProvider.generateRefreshToken()
        return ConnectResponse(token, refreshToken)
    }
}