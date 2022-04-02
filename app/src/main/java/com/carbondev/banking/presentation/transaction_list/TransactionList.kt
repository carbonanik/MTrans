package com.carbondev.banking.presentation.transaction_list

import android.net.wifi.p2p.WifiP2pManager
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carbondev.banking.data.model.*
import com.carbondev.banking.ui.theme.MTransTheme
import com.carbondev.banking.ui.theme.Purple500


@Composable
fun TransactionListScreen() {
    Surface {
        LazyColumn {
            items(testTransaction){ t ->
                TransactionItem(t)
//                Divider(Modifier.padding(horizontal = 8.dp))
            }
        }
    }
}

@Composable
fun TransactionItem(transaction: Transaction) {
    Surface(color= Color.White) {
        Column(Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp)) {
            val back = Color(0x42344354)
            Surface(color = Color(0xFFFFC8BF), shape = RoundedCornerShape(4.dp)) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp), verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(imageVector = transaction.transactionType.getIcon(), contentDescription = null, tint = transaction.transactionType.getColor())
                    Column {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(start = 8.dp), horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = transaction.transactionType.value, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                            Text(text = "${transaction.amount} \u09F3", fontSize = 18.sp, color = transaction.transactionType.getColor(), fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(start = 8.dp), horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = transaction.senderId, fontSize = 14.sp)
                            Text(text = transaction.timeAndDate(), fontSize = 14.sp)
                        }
                    }

                }
            }
        }
    }
}

@Preview
@Composable
fun TransactionListPrev() {
    MTransTheme {
        TransactionListScreen()
    }
}


@Preview
@Composable
fun TransactionItemPreview() {
    TransactionItem(testTransaction[0])
}

val testTransaction = listOf<Transaction>(
    Transaction(
        "754345", 100.0f, System.currentTimeMillis(), "wershtheiu", "askdjfiaf", TransactionType.CASH_IN
    ),

    Transaction(
        "754345", 200.0f, System.currentTimeMillis(), "wershtheiu", "askdjfiaf", TransactionType.CASH_OUT
    ),

    Transaction(
        "754345", 1050.0f, System.currentTimeMillis(), "wershtheiu", "askdjfiaf", TransactionType.RECEIVE_MONEY
    ),

    Transaction(
        "754345", 500.0f, System.currentTimeMillis(), "wershtheiu", "askdjfiaf", TransactionType.PAYMENT_RECEIVE
    ),

    Transaction(
        "754345", 1300.0f, System.currentTimeMillis(), "wershtheiu", "askdjfiaf", TransactionType.SEND_MONEY
    ),

    Transaction(
        "754345", 700.0f, System.currentTimeMillis(), "wershtheiu", "askdjfiaf", TransactionType.PAYMENT_RECEIVE
    ),

    Transaction(
        "754345", 100.0f, System.currentTimeMillis(), "wershtheiu", "askdjfiaf", TransactionType.CASH_OUT
    ),

    Transaction(
        "754345", 100.0f, System.currentTimeMillis(), "wershtheiu", "askdjfiaf", TransactionType.CASH_IN
    ),

    Transaction(
        "754345", 100.0f, System.currentTimeMillis(), "wershtheiu", "askdjfiaf", TransactionType.CASH_OUT
    ),

    Transaction(
        "754345", 100.0f, System.currentTimeMillis(), "wershtheiu", "askdjfiaf", TransactionType.CASH_IN
    ),

    Transaction(
        "754345", 100.0f, System.currentTimeMillis(), "wershtheiu", "askdjfiaf", TransactionType.CASH_OUT
    ),

    Transaction(
        "754345", 100.0f, System.currentTimeMillis(), "wershtheiu", "askdjfiaf", TransactionType.CASH_OUT
    ),

    Transaction(
        "754345", 100.0f, System.currentTimeMillis(), "wershtheiu", "askdjfiaf", TransactionType.CASH_OUT
    ),

    Transaction(
        "754345", 100.0f, System.currentTimeMillis(), "wershtheiu", "askdjfiaf", TransactionType.CASH_IN
    ),

    Transaction(
        "754345", 100.0f, System.currentTimeMillis(), "wershtheiu", "askdjfiaf", TransactionType.CASH_IN
    ),

    Transaction(
        "754345", 100.0f, System.currentTimeMillis(), "wershtheiu", "askdjfiaf", TransactionType.CASH_IN
    ),

    Transaction(
        "754345", 100.0f, System.currentTimeMillis(), "wershtheiu", "askdjfiaf", TransactionType.CASH_IN
    ),

    Transaction(
        "754345", 100.0f, System.currentTimeMillis(), "wershtheiu", "askdjfiaf", TransactionType.CASH_IN
    ),

    Transaction(
        "754345", 100.0f, System.currentTimeMillis(), "wershtheiu", "askdjfiaf", TransactionType.CASH_IN
    ),

    Transaction(
        "754345", 100.0f, System.currentTimeMillis(), "wershtheiu", "askdjfiaf", TransactionType.CASH_IN
    ),

    Transaction(
        "754345", 100.0f, System.currentTimeMillis(), "wershtheiu", "askdjfiaf", TransactionType.CASH_IN
    ),

    Transaction(
        "754345", 100.0f, System.currentTimeMillis(), "wershtheiu", "askdjfiaf", TransactionType.CASH_IN
    ),

)