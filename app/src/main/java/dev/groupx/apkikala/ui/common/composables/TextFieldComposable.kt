@file:OptIn(ExperimentalMaterial3Api::class)

package dev.groupx.apkikala.ui.common.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import dev.groupx.apkikala.R.drawable as AppIcon
import dev.groupx.apkikala.R.string as AppText

@Composable
fun BasicField(
  @StringRes text: Int,
  value: String,
  onNewValue: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  OutlinedTextField(
    singleLine = true,
    modifier = modifier,
    value = value,
    onValueChange = { onNewValue(it) },
    placeholder = { Text(stringResource(text)) }
  )
}

@Composable
fun CustomField(text: String, value: String, onNewValue: (String) -> Unit, modifier: Modifier = Modifier) {
  TextField(
    singleLine = true,
    modifier = modifier,
    value = value,
    onValueChange = { onNewValue(it) },
    placeholder = { Text(text) },
    colors = TextFieldDefaults.textFieldColors(
      containerColor = MaterialTheme.colorScheme.tertiary
    )
//    leadingIcon = { Icon(imageVector = Icons.Filled., contentDescription = "Email") }
  )
}

@Composable
fun EmailField(value: String, onNewValue: (String) -> Unit, modifier: Modifier = Modifier) {
  TextField(
    singleLine = true,
    modifier = modifier,
    value = value,
    onValueChange = { onNewValue(it) },
    placeholder = { Text(stringResource(AppText.email)) },
    leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Email") },
    colors = TextFieldDefaults.textFieldColors(
      containerColor = MaterialTheme.colorScheme.tertiary
    )
  )
}

@Composable
fun PasswordField(value: String, onNewValue: (String) -> Unit, modifier: Modifier = Modifier) {
  PasswordField(value, AppText.password, onNewValue, modifier)
}

@Composable
fun RepeatPasswordField(
  value: String,
  onNewValue: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  PasswordField(value, AppText.repeat_password, onNewValue, modifier)
}

@Composable
private fun PasswordField(
  value: String,
  @StringRes placeholder: Int,
  onNewValue: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  var isVisible by remember { mutableStateOf(false) }

  val icon =
    if (isVisible) painterResource(AppIcon.ic_visibility_on)
    else painterResource(AppIcon.ic_visibility_off)

  val visualTransformation =
    if (isVisible) VisualTransformation.None else PasswordVisualTransformation()

  TextField(
    modifier = modifier,
    value = value,
    onValueChange = { onNewValue(it) },
    placeholder = { Text(text = stringResource(placeholder)) },
    leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "Lock") },
    colors = TextFieldDefaults.textFieldColors(
      containerColor = MaterialTheme.colorScheme.tertiary
    ),
    trailingIcon = {
      IconButton(onClick = { isVisible = !isVisible }) {
        Icon(painter = icon, contentDescription = "Visibility")
      }
    },
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
    visualTransformation = visualTransformation
  )
}
