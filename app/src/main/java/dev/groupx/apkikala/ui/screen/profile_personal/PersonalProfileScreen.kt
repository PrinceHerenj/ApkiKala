package dev.groupx.apkikala.ui.screen.profile_personal

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.groupx.apkikala.R
import dev.groupx.apkikala.ui.navigation.NavigationDestination
import dev.groupx.apkikala.ui.screen.AccountUiState
import dev.groupx.apkikala.R.string as AppText

object PersonalProfileNode : NavigationDestination {
    override val route = "ProfileScreen"
    override val titleRes = AppText.app_name
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalProfileScreen(
    popUp: () -> Unit,
    //restartApp: (String) -> Unit,
    openScreen: (String) -> Unit,
    openAndPopUp: (String, String) -> Unit,
    clearAndNavigate: (String) -> Unit,
    viewModel: PersonalProfileViewModel = hiltViewModel(),


    ) {
    val uiState by viewModel.uiState.collectAsState(initial = AccountUiState(false))
    Column(modifier = Modifier.fillMaxSize()) {

        Scaffold(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.secondary),
            topBar = {
                var expanded by remember { mutableStateOf(false) }
                CenterAlignedTopAppBar(
                    modifier = Modifier.height(48.dp),
                    title = { Text(text = "") },
                    navigationIcon = {
                        IconButton(onClick = { viewModel.onBackClick(popUp) }) {
                            Icon(
                                Icons.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.Black,
                                modifier = Modifier.size(25.dp)
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { expanded = !expanded }) {
                            Icon(
                                Icons.Filled.MoreVert,
                                contentDescription = "Options",
                                tint = Color.Black,
                                modifier = Modifier.size(25.dp)
                            )
                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false }) {
                                if (uiState.isAnonymousAccount) {
                                    DropdownMenuItem(text = { Text("Login") },
                                        onClick = { viewModel.onLoginClick(openScreen) })
                                    DropdownMenuItem(text = { Text("SignUp") },
                                        onClick = { viewModel.onSignUpClick(openScreen) })
                                } else {
                                    DropdownMenuItem(
                                        text = { Text("Edit Profile") },
                                        onClick = { /*viewModel.onEditClick(openScreen)*/ })
                                    DropdownMenuItem(text = { Text("Logout") },
                                        onClick = { viewModel.onSignOutClick(clearAndNavigate) })
                                    DropdownMenuItem(text = { Text("Delete") }, onClick = {
                                        viewModel.onDeleteAccClick(
                                            clearAndNavigate, uiState.currentUserId
                                        )
                                    }
                                    )
                                }

                            }

                        }
                    }
                )
            },
            bottomBar = {
                NavigationBar {
                    NavigationBarItem(selected = false, onClick = {
                        viewModel.onHomeClick(openAndPopUp)
                    }, icon = { Icon(Icons.Filled.Home, contentDescription = null) })
                    NavigationBarItem(selected = false, onClick = {
                        viewModel.onSearchClick(openScreen)
                    }, icon = { Icon(Icons.Filled.Search, contentDescription = null) })
                    NavigationBarItem(selected = false, onClick = {
                        viewModel.onCollabClick(openScreen)
                    }, icon = { Icon(Icons.Filled.PlayArrow, contentDescription = null) })
                    NavigationBarItem(selected = true,
                        onClick = { },
                        icon = { Icon(Icons.Filled.Person, contentDescription = null) })
                }
            },

            ) {

            ProfileSection(Modifier.padding(it))
        }
    }
}

@Composable
fun ProfileSection(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.tertiary)
            .fillMaxWidth()

            .padding(vertical = 5.dp)
            .fillMaxSize()
            , horizontalAlignment = Alignment.CenterHorizontally
    ) {

                Column(
                    modifier = Modifier
                        .background(Color.LightGray)
                        .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally,
                                                                                    verticalArrangement = Arrangement.Center
                ){



               Image(
                   painter = painterResource(id = R.drawable.icons8_son_goku),
                   contentDescription = null,
                   contentScale = ContentScale.Fit,

                   modifier = modifier
                       .size(100.dp)
                       .border(
                           width = 1.dp, color = Color.LightGray, shape = CircleShape
                       )

                       .padding(2.dp)
                       .clip(CircleShape),


                   )

               /*Box() {
                   Image(
                       painter = painterResource(id = R.drawable.post_image_blank),
                       contentDescription = null,
                       contentScale = ContentScale.FillWidth,
                       modifier = Modifier.fillMaxWidth()
                   )
*/


            }}

           Column(
                modifier = Modifier.padding(top = 250.dp)
            )
            {
            Card(elevation = CardDefaults.cardElevation(3.dp)){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = modifier.padding(horizontal = 45.dp )


            ) {

                stats(numberText = "11", text = "Posts")
                Spacer(modifier = Modifier.weight(4f))
                stats(numberText = "11k", text = "Followers")
                Spacer(modifier = Modifier.weight(3f))
                stats(numberText = "111", text = "Following")
                Spacer(modifier = Modifier.weight(1f))


            }

                ProfileDescription(
                    displayName = "Rakess",
                    description = "This is a Sample Text \n\n",
                    url = "Rakess@gmail.com",
                    followedBy = listOf("bablu", "parkaass"),
                    otherFollow = 59
                )
            }}

        }




@Composable
fun stats(
    numberText: String,
    text: String,
    modifier: Modifier = Modifier,

    ) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = numberText, fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = text, fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 15.sp
        )

    }
}

@Composable
fun ProfileDescription(
    displayName: String,
    description: String,
    url: String,
    followedBy: List<String>,
    otherFollow: Int,
    modifier: Modifier = Modifier,
) {
    val letterSpacing = 0.5.sp
    val lineHeight = 20.sp

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = displayName,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )
        Text(
            text = description,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )

        Text(
            text = url, color = Color.Blue, letterSpacing = letterSpacing, lineHeight = lineHeight
        )
        if (followedBy.isNotEmpty()) {
            Text(
                text = buildAnnotatedString {
                    val boldStyle = SpanStyle(
                        color = Color.Black, fontWeight = FontWeight.Bold
                    )
                    append("Followed by ")
                    pushStyle(boldStyle)
                    followedBy.forEachIndexed { index, name ->
                        pushStyle(boldStyle)
                        append(name)
                        pop()
                        if (index < followedBy.size - 1) {
                            append(", ")
                        }

                    }
                    if (otherFollow > 2) {
                        append(" and ")
                        pushStyle(boldStyle)
                        append("$otherFollow others")
                    }
                }, letterSpacing = letterSpacing, lineHeight = lineHeight
            )
        }
    }
}






