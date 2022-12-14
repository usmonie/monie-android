package mon.ie.uikit.helpers.alert

import androidx.compose.ui.graphics.Color
import mon.ie.theme.MonieTypograhy
import mon.ie.uikit.helpers.nodes.ButtonNode
import mon.ie.uikit.helpers.nodes.TextNode

class AlertDialogBuilderImpl : AlertDialogBuilder {

    private val items by lazy(LazyThreadSafetyMode.NONE) {
        ArrayList<AlertDialogItem>(MAX_ALERT_ITEMS_SIZE)
    }

    private val hasTitle
        get() = items.any { it is AlertDialogItem.Title }

    override fun withTitle(value: String) {
        items.removeIf { it is AlertDialogItem.Title }
        items.add(
            0,
            AlertDialogItem.Title(
                node = TextNode(
                    value = value,
                    style = { MonieTypograhy.body2 }
                )
            )
        )
    }

    override fun withSubtitle(value: String) {
        items.removeIf { it is AlertDialogItem.Subtitle }
        items.add(
            if (hasTitle) 1 else 0,
            AlertDialogItem.Subtitle(
                node = TextNode(
                    value = value,
                    style = { MonieTypograhy.button2 }
                )
            )
        )
    }

    override fun withButton(value: String, onClick: () -> Unit) {
        items.add(
            AlertDialogItem.Button(
                node = ButtonNode(
                    title = TextNode(value = value, style = { MonieTypograhy.button1 }),
                    onClick = onClick
                )
            )
        )
    }

    override fun withButton(
        value: String,
        color: Color,
        onClick: () -> Unit
    ) {
        items.add(
            AlertDialogItem.Button(
                node = ButtonNode(
                    title = TextNode(
                        value = value,
                        style = { MonieTypograhy.button1 },
                        color = color
                    ),
                    onClick = onClick
                )
            )
        )
    }

    override fun build(): AlertDialogConfig {
        return AlertDialogConfig(items = items)
    }

    private companion object {
        const val MAX_ALERT_ITEMS_SIZE = 4
    }
}