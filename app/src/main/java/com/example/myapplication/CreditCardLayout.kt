package com.example.myapplication

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import com.example.myapplication.services.CardService

class CreditCardLayout (
    context: Context,
    attrs: AttributeSet
) : LinearLayout(context, attrs) {
    init {
        inflate(context, R.layout.credit_card_layout, this)

        val customAttributesStyle = context.obtainStyledAttributes(
            attrs, R.styleable.CreditCardLayout, 0, 0
        )

        val card = CardService().getCard()

        val tvName = findViewById<TextView>(R.id.tv_cc_name)
        val tvNumber = findViewById<TextView>(R.id.tv_cc_number)


        try {
            tvName.text = card.name
            tvNumber.text = card.number
        } finally {
            customAttributesStyle.recycle()
        }
    }
}