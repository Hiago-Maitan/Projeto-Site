package br.com.bandtec.scrolleclienterest_turmab

import java.math.BigDecimal

data class Filme (
    val id:Int,
    val nome:String,
    val ano:Int,
    val custoProducao:BigDecimal,
    val classico:Boolean
)