package com.procurement.auction.domain.model.tender.snapshot

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.procurement.auction.domain.model.auction.status.AuctionsStatus
import com.procurement.auction.domain.model.auction.status.AuctionsStatusDeserializer
import com.procurement.auction.domain.model.auction.status.AuctionsStatusSerializer
import com.procurement.auction.domain.model.country.Country
import com.procurement.auction.domain.model.country.CountryDeserializer
import com.procurement.auction.domain.model.country.CountrySerializer
import com.procurement.auction.domain.model.cpid.Cpid
import com.procurement.auction.domain.model.ocid.Ocid
import com.procurement.auction.domain.model.operationId.OperationId
import com.procurement.auction.domain.model.tender.TenderId
import com.procurement.auction.domain.model.version.RowVersion
import com.procurement.auction.infrastructure.web.response.version.ApiVersion

class CancelledAuctionsSnapshot(
    val rowVersion: RowVersion,
    val operationId: OperationId,
    val cpid: Cpid,
    val ocid: Ocid,
    val data: Data
) {
    companion object {
        val apiVersion = ApiVersion(1, 0, 0)
    }

    @JsonPropertyOrder("version", "tender")
    class Data(
        @field:JsonProperty("version") @param:JsonProperty("version") val apiVersion: ApiVersion,
        @field:JsonProperty("tender") @param:JsonProperty("tender") val tender: Tender
    ) {

        @JsonPropertyOrder("id", "country", "status")
        class Tender(
            @field:JsonProperty("id") @param:JsonProperty("id") val id: TenderId,

            @JsonDeserialize(using = CountryDeserializer::class)
            @JsonSerialize(using = CountrySerializer::class)
            @field:JsonProperty("country") @param:JsonProperty("country") val country: Country,

            @JsonDeserialize(using = AuctionsStatusDeserializer::class)
            @JsonSerialize(using = AuctionsStatusSerializer::class)
            @field:JsonProperty("status") @param:JsonProperty("status") val status: AuctionsStatus
        )
    }
}