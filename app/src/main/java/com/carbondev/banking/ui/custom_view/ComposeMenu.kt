package com.carbondev.banking.ui.custom_view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.carbondev.banking.ui.theme.Purple500

@Preview
@Composable
fun MenuPrev() {
    Surface {
        MenuSample()
    }
}


@Composable
fun MenuSample() {

    val billingPeriodItems = listOf("Billing Period", "Annual", "Monthly", "One-Time Payment")

    var billingPeriodExpanded by remember { mutableStateOf(false) }

    var selectedIndex by remember { mutableStateOf(0) }

    Column {
        Text(text = "Text")

        Box(
            modifier = Modifier
                .fillMaxWidth()
//                .padding(16.dp)
                .background(Color.Green),
            contentAlignment = Alignment.CenterStart,
        ) {
            ComposeMenu(
                menuItems = billingPeriodItems,
                menuExpandedState = billingPeriodExpanded,
                seletedIndex = selectedIndex,
                updateMenuExpandStatus = {
                    billingPeriodExpanded = true
                },
                onDismissMenuView = {
                    billingPeriodExpanded = false
                },
                onMenuItemclick = { index ->
                    selectedIndex = index
                    billingPeriodExpanded = false
                }
            )
        }
        Text(text = "Text")
    }
}

@Composable
fun ComposeMenu(
    menuItems: List<String>,
    menuExpandedState: Boolean,
    seletedIndex: Int,
    updateMenuExpandStatus: () -> Unit,
    onDismissMenuView: () -> Unit,
    onMenuItemclick: (Int) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopStart)
//            .padding(top = 10.dp)
            .border(0.5.dp, Purple500)
            .clickable(
                onClick = {
                    updateMenuExpandStatus()
                },
            ),

        ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

//            val (label, iconView) = createRefs()

            Text(
                text = menuItems[seletedIndex],
//                color= seletedIndex.dropdownDisableColor(),
//                    .constrainAs(label) {
//                        top.linkTo(parent.top)
//                        bottom.linkTo(parent.bottom)
//                        start.linkTo(parent.start)
//                        end.linkTo(iconView.start)
//                        width = Dimension.fillToConstraints
//                    }
                color = Purple500
            )

            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp, 20.dp),
//                    .constrainAs(iconView) {
//                        end.linkTo(parent.end)
//                        top.linkTo(parent.top)
//                        bottom.linkTo(parent.bottom)
//                    },
                tint = Purple500
            )

            DropdownMenu(
                expanded = menuExpandedState,
                onDismissRequest = { onDismissMenuView() },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.surface)
            ) {
                menuItems.forEachIndexed { index, title ->
                    DropdownMenuItem(
                        onClick = {
                            if (index != 0) {
                                onMenuItemclick(index)
                            }
                        }) {
                        Text(text = title)
                    }
                }
            }
        }
    }
}