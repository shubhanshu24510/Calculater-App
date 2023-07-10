package com.shubhans.calculaterapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.shubhans.calculaterapp.databinding.ActivityMainBinding
import java.lang.RuntimeException
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.log10
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.idTVSecondary
        binding.idTVprimary
        binding.bac.setOnClickListener {
            binding.idTVprimary.text = ""
            binding.idTVSecondary.text = "" }
        binding.bc.setOnClickListener {
            var str: String = binding.idTVprimary.text.toString()
            if (str != "") {
                str = str.substring(0, str.length - 1)
                binding.idTVprimary.text = str
            } }
        binding.bbrac1.setOnClickListener {
            binding.idTVprimary.text.toString() + "("
        }
        binding.bbrac2.setOnClickListener {
            binding.idTVprimary.text.toString() + ")"
        }
        binding.bsin.setOnClickListener {
            binding.idTVprimary.text.toString() + "sin"
        }
        binding.bcos.setOnClickListener {
            binding.idTVprimary.text.toString() + "cos"
        }
        binding.btan.setOnClickListener {
            binding.idTVprimary.text.toString() + "tan"
        }
        binding.blog.setOnClickListener {
            binding.idTVprimary.text.toString() + "log"
        }
        binding.bln.setOnClickListener {
            binding.idTVprimary.text.toString() + "ln"
        }
        binding.bfact.setOnClickListener {
            if (binding.idTVprimary.text.toString().isEmpty()) {
                Toast.makeText(this, "Please Enter a valid number..", Toast.LENGTH_LONG).show()
            } else {
                val value: Int = binding.idTVprimary.text.toString().toInt()
                val fact: Int = factorial(value)
                binding.idTVprimary.text = fact.toString()
                "$value !".also { binding.idTVSecondary.text = it }
            } }
        binding.bsquare.setOnClickListener {
            if (binding.idTVprimary.text.toString().isEmpty()) {
                Toast.makeText(this, "Please Enter a valid number..", Toast.LENGTH_LONG).show()
            } else {
                val str: String = binding.idTVprimary.text.toString()
                val r = sqrt(str.toDouble())
                val result = r.toString()
                binding.idTVprimary.text = result
            } }
        binding.bsqrt.setOnClickListener {
            if (binding.idTVprimary.text.toString().isEmpty()) {
                Toast.makeText(this, "Please Enter a valid number..", Toast.LENGTH_LONG).show()
            } else {
                val d: Double = binding.idTVprimary.text.toString().toDouble()
                val square = d * d
                binding.idTVSecondary.text = square.toString()
                "$d^2".also { binding.idTVSecondary.text = it }
            }
        }
        binding.binv
        binding.b0.setOnClickListener {
            binding.idTVprimary.text.toString() + "0"
        }
        binding.b9.setOnClickListener {
            binding.idTVprimary.text.toString() + "9"
        }
        binding.b8.setOnClickListener {
            binding.idTVprimary.text.toString() + "8"
        }
        binding.b7.setOnClickListener {
            binding.idTVprimary.text.toString() + "7"
        }
        binding.b6.setOnClickListener {
            binding.idTVprimary.text.toString() + "6"
        }
        binding.b5.setOnClickListener {
            binding.idTVprimary.text.toString() + "5"
        }
        binding.b4.setOnClickListener {
            binding.idTVprimary.text.toString() + "4"
        }
        binding.b3.setOnClickListener {
            binding.idTVprimary.text.toString() + "3"
        }
        binding.b2.setOnClickListener {
            binding.idTVprimary.text.toString() + "2"
        }
        binding.b1.setOnClickListener {
            binding.idTVprimary.text.toString() + "1"
        }
        binding.bpi.setOnClickListener {
            binding.idTVprimary.text.toString() + "3.142"
        }
        binding.bmul.setOnClickListener {
            val str: String = binding.idTVprimary.text.toString()
            if (!str.get(index = str.length - 1).equals("*")) {
                binding.idTVprimary.text.toString() + "*"
            } }
        binding.bminus.setOnClickListener {
            val str: String = binding.idTVprimary.text.toString()
            if (!str.get(index = str.length - 1).equals("-")) {
                binding.idTVprimary.text.toString() + "-"
            } }
        binding.bplus.setOnClickListener {
            binding.idTVprimary.text.toString() + "+"
        }
        binding.bequal.setOnClickListener {
            val str: String = binding.idTVprimary.text.toString()
            val result: Double = evaluate(str) as Double
            val r = result.toString()
            binding.idTVprimary.text = r
            binding.idTVSecondary.text = str
        }
        binding.bdot.setOnClickListener {
            binding.idTVprimary.text.toString() + "."
        }
        binding.bdiv.setOnClickListener {
            binding.idTVprimary.text.toString() + "/"
        } }
    private fun factorial(n: Int): Int {
        return if (n == 1 || n == 0) 1 else n * factorial(n - 1)
    }
    private fun evaluate(str: String): Any {
        return object : Any() {
            var pos = -1
            var ch = 0
            fun nextChar() {
                ch = if (++pos < str.length) str[pos].code else -1 }
            fun eat(charTOEat: Int): Boolean {
                while (ch == ' '.code) nextChar()
                if (ch == charTOEat) {
                    nextChar()
                    return true }
                return false }
            fun parse(): Double {
                nextChar()
                val x = parseExpression()
                if (pos < str.length) throw RuntimeException("Unexpected :" + ch.toChar())
                return x }
            fun parseExpression(): Double {
                var x = parseTerm()
                while (true) {
                    if (eat('+'.code)) x += parseTerm()
                    else if (eat('-'.code)) x -= parseTerm()
                    else return x } }
            private fun parseTerm(): Double {
                var x = parseTerm()
                while (true) {
                    if (eat('*'.code)) x *= parseFacter()
                    else if (eat('/'.code)) x /= parseFacter()
                    else return x } }
            fun parseFacter(): Double {
                if (eat('+'.code)) return parseFacter()
                if (eat('-'.code)) return -parseFacter()
                var x: Double
                val startpos = pos
                if (eat('('.code)) {
                    x = parseExpression()
                    eat(')'.code)
                } else if (ch >= '0'.code && ch <= '9'.code || ch == '.'.code) {
                    while (ch >= '0'.code && ch <= '9'.code || ch == '.'.code) nextChar()
                    x = str.substring(startpos, pos).toDouble()
                } else if (ch >= 'a'.code && ch <= 'z'.code) {
                    while (ch >= 'a'.code && ch <= 'z'.code) nextChar()
                    val func = str.substring(startpos, pos)
                    x = parseFacter()
                    x =
                        when (func) {
                            "sqrt" -> sqrt(x)
                            "sin" -> sin(
                                Math.toRadians(x))
                            "cos" -> cos(
                                Math.toRadians(x))
                            "tan" -> tan(Math.toRadians(x))
                            "log" -> log10(x)
                            "ln" -> ln(x)
                            else -> throw RuntimeException(
                                "Unknown function: $func") }
                } else {
                    throw RuntimeException("Unexpected: " + ch.toChar()) }
                if (eat('^'.code)) x = x.pow(parseFacter())
                return x }
        }.parse()
    }
}




















