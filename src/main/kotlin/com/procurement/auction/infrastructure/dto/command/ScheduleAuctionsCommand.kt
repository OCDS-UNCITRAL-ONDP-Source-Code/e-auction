package com.procurement.auction.infrastructure.dto.command

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.procurement.auction.domain.model.amount.Amount
import com.procurement.auction.domain.model.amount.AmountDeserializer
import com.procurement.auction.domain.model.amount.AmountSerializer
import com.procurement.auction.domain.model.auction.id.AuctionId
import com.procurement.auction.domain.model.auction.id.AuctionIdDeserializer
import com.procurement.auction.domain.model.auction.id.AuctionIdSerializer
import com.procurement.auction.domain.model.command.id.CommandId
import com.procurement.auction.domain.model.command.name.CommandName
import com.procurement.auction.domain.model.country.Country
import com.procurement.auction.domain.model.country.CountryDeserializer
import com.procurement.auction.domain.model.country.CountrySerializer
import com.procurement.auction.domain.model.cpid.Cpid
import com.procurement.auction.domain.model.currency.Currency
import com.procurement.auction.domain.model.currency.CurrencyDeserializer
import com.procurement.auction.domain.model.currency.CurrencySerializer
import com.procurement.auction.domain.model.date.JsonDateTimeDeserializer
import com.procurement.auction.domain.model.date.JsonDateTimeSerializer
import com.procurement.auction.domain.model.lotId.LotId
import com.procurement.auction.domain.model.lotId.LotIdDeserializer
import com.procurement.auction.domain.model.lotId.LotIdSerializer
import com.procurement.auction.domain.model.ocid.Ocid
import com.procurement.auction.domain.model.operationId.OperationId
import com.procurement.auction.domain.model.operationId.OperationIdDeserializer
import com.procurement.auction.domain.model.operationId.OperationIdSerializer
import com.procurement.auction.infrastructure.web.response.version.ApiVersion
import java.time.LocalDateTime

@JsonIgnoreProperties(ignoreUnknown = true)
data class ScheduleAuctionsCommand(
    @field:JsonProperty("version") @param:JsonProperty("version") val version: ApiVersion,
    @field:JsonProperty("id") @param:JsonProperty("id") val id: CommandId,
    @field:JsonProperty("command") @param:JsonProperty("command") val name: CommandName,
    @field:JsonProperty("context") @param:JsonProperty("context") val context: Context,
    @field:JsonProperty("data") @param:JsonProperty("data") val data: Data
) {

    data class Context(
        @field:JsonProperty("cpid") @param:JsonProperty("cpid") val cpid: Cpid,
        @field:JsonProperty("ocid") @param:JsonProperty("ocid") val ocid: Ocid,

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

    data class Data(
        @field:JsonProperty("tenderPeriod") @param:JsonProperty("tenderPeriod") val tenderPeriod: TenderPeriod,
        @field:JsonProperty("electronicAuctions") @param:JsonProperty("electronicAuctions") val electronicAuctions: ElectronicAuctions
    ) {
        data class TenderPeriod(
            @JsonDeserialize(using = JsonDateTimeDeserializer::class)
            @JsonSerialize(using = JsonDateTimeSerializer::class)
            @field:JsonProperty("endDate") @param:JsonProperty("endDate") val endDate: LocalDateTime
        )

        data class ElectronicAuctions(
            @field:JsonProperty("details") @param:JsonProperty("details") val details: List<Detail>
        ) {

            data class Detail(

                @JsonDeserialize(using = AuctionIdDeserializer::class)
                @JsonSerialize(using = AuctionIdSerializer::class)
                @field:JsonProperty("id") @param:JsonProperty("id") val id: AuctionId,

                @JsonDeserialize(using = LotIdDeserializer::class)
                @JsonSerialize(using = LotIdSerializer::class)
                @field:JsonProperty("relatedLot") @param:JsonProperty("relatedLot") val relatedLot: LotId,

                @field:JsonProperty("electronicAuctionModalities") @param:JsonProperty("electronicAuctionModalities") val electronicAuctionModalities: List<ElectronicAuctionModalities>
            ) {
                data class ElectronicAuctionModalities(
                    @field:JsonProperty("eligibleMinimumDifference") @param:JsonProperty("eligibleMinimumDifference") val eligibleMinimumDifference: EligibleMinimumDifference
                ) {

                    data class EligibleMinimumDifference(
                        @JsonDeserialize(using = AmountDeserializer::class)
                        @JsonSerialize(using = AmountSerializer::class)
                        @JsonInclude(JsonInclude.Include.NON_NULL)
                        @field:JsonProperty("amount") @param:JsonProperty("amount") val amount: Amount?,

                        @JsonDeserialize(using = CurrencyDeserializer::class)
                        @JsonSerialize(using = CurrencySerializer::class)
                        @field:JsonProperty("currency") @param:JsonProperty("currency") val currency: Currency
                    )
                }
            }
        }
    }
}
