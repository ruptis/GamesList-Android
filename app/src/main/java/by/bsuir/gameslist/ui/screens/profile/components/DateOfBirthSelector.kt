package by.bsuir.gameslist.ui.screens.profile.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateOfBirthSelector(
    dateOfBirth: LocalDate?,
    onDateOfBirthSelected: (LocalDate?) -> Unit,
    modifier: Modifier = Modifier
) {
    var isDatePickerExpanded by remember { mutableStateOf(false) }

    FormRow(
        title = "Date of Birth",
        modifier = modifier
    ) {
        when (isDatePickerExpanded) {
            true -> {
                val datePickerState =
                    rememberDatePickerState(
                        initialSelectedDateMillis = dateOfBirth?.atStartOfDay(
                            ZoneId.systemDefault()
                        )?.toInstant()?.toEpochMilli()
                    )
                DatePickerDialog(
                    onDismissRequest = { isDatePickerExpanded = false },
                    confirmButton = {
                        TextButton(onClick = {
                            onDateOfBirthSelected(datePickerState.selectedDateMillis?.toLocalDate())
                            isDatePickerExpanded = false
                        }) {
                            Text(text = "OK")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = {
                            isDatePickerExpanded = false
                        }) {
                            Text(text = "Cancel")
                        }
                    }
                ) {
                    DatePicker(state = datePickerState)
                }
            }

            false -> Text(
                text = dateOfBirth?.toString() ?: "Select Date of Birth",
                modifier = Modifier
                    .weight(4f)
                    .clickable { isDatePickerExpanded = true }
            )
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
private fun Long.toLocalDate(): LocalDate {
    return Instant.ofEpochMilli(this).atZone(ZoneId.systemDefault()).toLocalDate()
}