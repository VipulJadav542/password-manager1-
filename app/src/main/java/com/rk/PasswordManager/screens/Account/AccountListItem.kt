package com.rk.PasswordManager.screens.Account

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rk.PasswordManager.R
import com.rk.PasswordManager.model.AccountData

@Composable
fun AccountItemList(account: AccountData, function: (id: Int) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(30.dp))
            .clickable {
                function(account.AccountId)
            }
    ) {
        Row(
            modifier = Modifier.padding(20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = if (account.accountName.length > 10) account.accountName.substring(0, 10) + "..." else account.accountName,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(0.dp, 0.dp, 10.dp, 0.dp),
            )
            Text(
                text = generateStarString(account.password.length),
                style = MaterialTheme.typography.titleMedium,
            )
            Icon(
                painter = painterResource(id = R.drawable.right_arrow),
                contentDescription = "new",
            )
        }
    }
}

fun generateStarString(length: Int): String {
    val maxLength = 10
    return if (length > maxLength) {
        "*".repeat(maxLength) + "..."
    } else {
        "*".repeat(length)
    }
}