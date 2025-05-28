import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.StyleSpan
import android.view.View
import android.widget.TextView
import java.text.NumberFormat
import java.util.Locale

fun Double.formatAsCurrency(): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    return formatter.format(this)
}

fun String.ensureHttps(): String {
    return this.replace("http://", "https://")
}

fun TextView.setExpandableText(fullText: String, maxCharsCollapsed: Int = 150) {
    var isExpanded = false
    movementMethod = LinkMovementMethod.getInstance()

    fun updateText() {
        if (isExpanded) {
            val full = "$fullText Ver menos"
            val spannable = SpannableStringBuilder(full)
            val start = full.indexOf("Ver menos")

            spannable.setSpan(object : ClickableSpan() {
                override fun onClick(widget: View) {
                    isExpanded = false
                    updateText()
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = false
                }
            }, start, start + "Ver menos".length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

            spannable.setSpan(
                StyleSpan(Typeface.BOLD_ITALIC),
                start,
                start + "Ver menos".length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            text = spannable

        } else {
            val shortText = fullText.take(maxCharsCollapsed).trim()
            val full = "$shortText... Ver mais"
            val spannable = SpannableStringBuilder(full)
            val start = full.indexOf("Ver mais")

            spannable.setSpan(object : ClickableSpan() {
                override fun onClick(widget: View) {
                    isExpanded = true
                    updateText()
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = false
                }
            }, start, start + "Ver mais".length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

            spannable.setSpan(
                StyleSpan(Typeface.BOLD_ITALIC),
                start,
                start + "Ver mais".length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            text = spannable
        }
    }

    updateText()
}
