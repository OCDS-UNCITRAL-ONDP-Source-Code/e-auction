package com.procurement.auction.domain.command

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.procurement.auction.domain.model.command.id.CommandId
import com.procurement.auction.domain.model.command.id.CommandIdDeserializer
import com.procurement.auction.domain.model.command.id.CommandIdSerializer
import com.procurement.auction.domain.model.command.name.CommandName
import com.procurement.auction.domain.model.command.name.CommandNameDeserializer
import com.procurement.auction.domain.model.command.name.CommandNameSerializer
import com.procurement.auction.domain.model.country.Country
import com.procurement.auction.domain.model.country.CountryDeserializer
import com.procurement.auction.domain.model.country.CountrySerializer
import com.procurement.auction.domain.model.cpid.CPID
import com.procurement.auction.domain.model.cpid.CPIDDeserializer
import com.procurement.auction.domain.model.cpid.CPIDSerializer
import com.procurement.auction.domain.model.date.JsonDateTimeDeserializer
import com.procurement.auction.domain.model.date.JsonDateTimeSerializer
import com.procurement.auction.domain.model.operationId.OperationId
import com.procurement.auction.domain.model.operationId.OperationIdDeserializer
import com.procurement.auction.domain.model.operationId.OperationIdSerializer
import com.procurement.auction.domain.model.version.ApiVersion
import com.procurement.auction.domain.model.version.ApiVersionDeserializer
import com.procurement.auction.domain.model.version.ApiVersionSerializer
import java.time.LocalDateTime

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder("version", "id", "command", "context")
class Command(
    @JsonDeserialize(using = ApiVersionDeserializer::class)
    @JsonSerialize(using = ApiVersionSerializer::class)
    @field:JsonProperty("version") @param:JsonProperty("version") val version: ApiVersion,

    @JsonDeserialize(using = CommandIdDeserializer::class)
    @JsonSerialize(using = CommandIdSerializer::class)
    @field:JsonProperty("id") @param:JsonProperty("id") val id: CommandId,

    @JsonDeserialize(using = CommandNameDeserializer::class)
    @JsonSerialize(using = CommandNameSerializer::class)
    @field:JsonProperty("command") @param:JsonProperty("command") val name: CommandName,

    @field:JsonProperty("context") @param:JsonProperty("context") val context: Context
) {
    @JsonPropertyOrder("cpid", "operationId", "startDate", "pmd", "country")
    data class Context(
        @JsonDeserialize(using = CPIDDeserializer::class)
        @JsonSerialize(using = CPIDSerializer::class)
        @field:JsonProperty("cpid") @param:JsonProperty("cpid") val cpid: CPID,

        @JsonDeserialize(using = OperationIdDeserializer::class)
        @JsonSerialize(using = OperationIdSerializer::class)
        @field:JsonProperty("operationId") @param:JsonProperty("operationId") val operationId: OperationId,

        @JsonDeserialize(using = JsonDateTimeDeserializer::class)
        @JsonSerialize(using = JsonDateTimeSerializer::class)
        @field:JsonProperty("startDate") @param:JsonProperty("startDate") val operationDate: LocalDateTime,

        @field:JsonProperty("pmd") @param:JsonProperty("pmd") val pmd: String,

        @JsonDeserialize(using = CountryDeserializer::class)
        @JsonSerialize(using = CountrySerializer::class)
        @field:JsonProperty("country") @param:JsonProperty("country") val country: Country
    )
}