package by.bsuir.gameslist.ui.screens.profile.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import by.bsuir.gameslist.model.User

@Composable
fun GenderSelector(
    selectedGender: User.Gender?,
    onGenderSelected: (User.Gender?) -> Unit,
    modifier: Modifier = Modifier
) {
    var isGenderMenuExpanded by remember { mutableStateOf(false) }

    FormRow(
        title = "Gender",
        modifier = modifier
    ) {
        when (isGenderMenuExpanded) {
            true -> DropdownMenu(
                expanded = isGenderMenuExpanded,
                onDismissRequest = { isGenderMenuExpanded = false },
                offset = DpOffset(40.dp, 0.dp)
            ) {
                DropdownMenuItem(
                    text = {
                        Text(text = "Select Gender")
                    },
                    onClick = {
                        onGenderSelected(null)
                        isGenderMenuExpanded = false
                    }
                )
                HorizontalDivider()

                User.Gender.entries.forEach {
                    DropdownMenuItem(
                        text = {
                            Text(text = it.name)
                        },
                        onClick = {
                            onGenderSelected(it)
                            isGenderMenuExpanded = false
                        }
                    )
                }
            }

            false -> Text(
                text = selectedGender?.name ?: "Select Gender",
                modifier = Modifier
                    .weight(4f)
                    .clickable { isGenderMenuExpanded = true }
            )
        }
    }
}