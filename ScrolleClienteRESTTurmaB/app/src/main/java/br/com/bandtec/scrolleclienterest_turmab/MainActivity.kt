package br.com.bandtec.scrolleclienterest_turmab

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Criamos uma objeto do tipo TextView
        val novoTv = TextView(baseContext)

        // configurando a TextView
        novoTv.text = "texto em tempo de execução!"
        novoTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f) // 15sp
        novoTv.setTextColor(Color.parseColor("#FF0099")) // cor RGB

        // inserindo a TextView na LinearLayout
        ll_conteudo.addView(novoTv)

        // iria inserir no Layout Principal da tela
        // layout_principal.addView(novoTv)

        apiTrazerTodos()

        apiCriarFilme()

    }

    fun criarRequisicoes(): ApiFilmes {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://5f861cfdc8a16a0016e6aacd.mockapi.io/bandtec-api/")
            .build()

        val requisicoes = retrofit.create(ApiFilmes::class.java)

        return requisicoes
    }

    fun apiCriarFilme() {
        val novoFilme = Filme(
            5000,
            "As lindas tranças do Rei careca",
            1500,
            0.01.toBigDecimal(),
            false
        )

        val callNovoFilme = criarRequisicoes().postFilme(novoFilme)

        callNovoFilme.enqueue(object : Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(baseContext, "ERRO: $t", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Toast.makeText(baseContext, "Cadastrado :)", Toast.LENGTH_SHORT).show()
            }

        })

    }

    fun apiTrazerTodos() {
        val callFilmes = criarRequisicoes().getFilmes()

        callFilmes.enqueue(object : Callback<List<Filme>> {
            override fun onFailure(call: Call<List<Filme>>, t: Throwable) {
                Toast.makeText(baseContext, "ERRO: $t", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Filme>>, response: Response<List<Filme>>) {
                response.body()?.forEach{
                    val novoTv = TextView(baseContext)

                    novoTv.text = "Filme: ${it.nome} - Ano: ${it.ano} - Custou: ${it.custoProducao}"
                    novoTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f) // 15sp
                    novoTv.setTextColor(Color.parseColor("#FF0099")) // cor RGB

                    // inserindo a TextView na LinearLayout
                    ll_conteudo.addView(novoTv)

                }
            }
        })
    }
}