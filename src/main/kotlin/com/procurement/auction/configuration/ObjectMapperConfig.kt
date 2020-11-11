package com.procurement.auction.configuration

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.node.JsonNodeFactory
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.procurement.auction.domain.model.Ocid
import com.procurement.auction.domain.model.cpid.Cpid
import com.procurement.auction.domain.model.cpid.CpidDeserializer
import com.procurement.auction.domain.model.date.JsonDateTimeModule
import com.procurement.auction.domain.model.ocid.OcidDeserializer
import com.procurement.auction.infrastructure.web.response.version.jackson.ApiVersion2Module
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration

@Configuration
class ObjectMapperConfig(@Autowired objectMapper: ObjectMapper) {

    init {
        objectMapper.registerModule(getModule())
        objectMapper.registerModule(JsonDateTimeModule())
        objectMapper.registerModule(ApiVersion2Module())
        objectMapper.registerKotlinModule()
        objectMapper.configure(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS, true)
        objectMapper.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true)
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        objectMapper.nodeFactory = JsonNodeFactory.withExactBigDecimals(true)
    }

    private fun getModule(): SimpleModule {
        val simpleModule = SimpleModule()
        simpleModule.addDeserializer(Cpid::class.java, CpidDeserializer())
        simpleModule.addDeserializer(Ocid::class.java, OcidDeserializer())
        return simpleModule
    }
}
