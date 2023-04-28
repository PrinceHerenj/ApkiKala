package dev.groupx.apkikala.ui.screen.profile_personal

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
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
    restartApp: (String) -> Unit,
    openScreen: (String) -> Unit,
    openAndPopUp: (String, String) -> Unit,
    viewModel: PersonalProfileViewModel = hiltViewModel(),


) {
    val uiState by viewModel.uiState.collectAsState(initial = AccountUiState(false))
    Column(modifier = Modifier.fillMaxSize()) {
        Topbar(name = "" )

        ProfileSection ()

    Scaffold(
        modifier = Modifier.background(color = MaterialTheme.colorScheme.secondary),
        /*topBar = {
                 TopAppBar(
                     title = {
                         Text("Profile")
                     },

                 )

        },*/
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        viewModel.onHomeClick(openAndPopUp)
                    },
                    icon = { Icon(Icons.Filled.Home, contentDescription = null) }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        viewModel.onSearchClick(openScreen)
                    },
                    icon = { Icon(Icons.Filled.Search, contentDescription = null) }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        viewModel.onCollabClick(openScreen)
                    },
                    icon = { Icon(Icons.Filled.PlayArrow, contentDescription = null) }
                )
                NavigationBarItem(
                    selected = true,
                    onClick = {  },
                    icon = { Icon(Icons.Filled.Person, contentDescription = null) }
                )
            }
        }
    ){
        Column(Modifier.padding(it)) {
           // Text(text = uiState.currentUserId, modifier = Modifier.padding(start = 8.dp))
           /*
            if (uiState.isAnonymousAccount) {
                Row {
                    BasicButton(AppText.sign_in, modifier = Modifier.padding(start = 8.dp)) {
                        viewModel.onLoginClick(openScreen)
                    }
                    BasicButton(AppText.sign_up, modifier = Modifier.padding(start = 8.dp)) {
                        viewModel.onSignUpClick(openScreen)
                    }

                }

            } else {
                Row {
                    BasicButton(AppText.sign_out, modifier = Modifier.padding(start = 8.dp)) {
                        viewModel.onSignOutClick(openAndPopUp)
                    }
                    BasicButton(AppText.delete_acc, modifier = Modifier.padding(start = 8.dp)) {
                        viewModel.onDeleteAccClick(openAndPopUp, uiState.currentUserId)
                    }
                }
            }*/
        }
    }

    }
}


@Composable
fun Topbar(
    name: String,
    modifier: Modifier = Modifier,

    ){

    val context = LocalContext.current
    Surface(color = MaterialTheme.colorScheme.secondary) {

    Row(

        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier.fillMaxWidth()
    ) {

        IconButton(onClick = {}) {
            Icon(Icons.Default.ArrowBack,
            contentDescription = "Back",
            tint = Color.Black, modifier = modifier.size(25.dp)
            )

        }

        Text(
            text = name,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,


        )
        IconButton(onClick = {}) {
            Icon(Icons.Default.MoreVert,
            contentDescription = "options",
            tint = Color.Black,modifier = modifier.size(25.dp)
            )

        }
        /*DropdownMenu(expanded = , onDismissRequest = { showMenu = false }) {

        }*/
    }
    }
}

@Composable
fun ProfileSection(

    modifier: Modifier = Modifier,

){

    Column(modifier = modifier
        .background(color = MaterialTheme.colorScheme.tertiary)
        .fillMaxWidth()
        .padding(16.dp)
        .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    )  {
        Card(
            elevation = CardDefaults.cardElevation(4.dp),
        ){
            Image(painter = painterResource(id = R.drawable.icons8_son_goku), contentDescription =null,
                contentScale = ContentScale.FillWidth
            )
        Row(

            modifier = modifier

                .fillMaxWidth()
                .padding(horizontal = 150.dp)
                .padding(top = 40.dp)


        )  {



            Image(painter = painterResource(id = R.drawable.icons8_son_goku), contentDescription =null ,

                modifier = modifier
                    .aspectRatio(1f, matchHeightConstraintsFirst = true)
                    .border(
                        width = 1.dp,
                        color = Color.LightGray,
                        shape = CircleShape
                    )
                    .padding(2.dp)
                    .clip(CircleShape),

                )

            }

        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = modifier
                .padding(start = 40.dp)
                .padding(top = 10.dp)
        ) {

            stats(numberText = "11", text = "Posts" )
            Spacer(modifier = Modifier.weight(3f))
            stats(numberText = "11k", text = "Followers" )
            Spacer(modifier = Modifier.weight(2f))
            stats(numberText = "111", text = "Following" )
            Spacer(modifier = Modifier.weight(1f))


        }
        Row(
            modifier = modifier.padding(top = 10.dp)
        )
        {
            ProfileDescription(
                displayName = "Rakess",
                description = "This is a Sample Text \n\n",
                url ="Rakess@gmail.com",
                followedBy = listOf("bablu","parkaass"),
                otherFollow = 59
            )
        }




    }
}

@Composable
fun stats(
    numberText: String,
    text: String,
    modifier: Modifier = Modifier

)  {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        )
        {
            Text(
                text = numberText,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = text,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 15.sp
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
    modifier: Modifier = Modifier
){
    val letterSpacing = 0.5.sp
    val lineHeight = 20.sp

    Column(modifier = modifier
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
            text = url,
            color = Color.Blue,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )
        if(followedBy.isNotEmpty()) {
            Text(
                text = buildAnnotatedString {
                    val boldStyle = SpanStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    append("Followed by ")
                    pushStyle(boldStyle)
                    followedBy.forEachIndexed { index, name ->
                        pushStyle(boldStyle)
                        append(name)
                        pop()
                        if(index < followedBy.size - 1) {
                            append(", ")
                        }

                    }
                    if(otherFollow > 2) {
                        append(" and ")
                        pushStyle(boldStyle)
                        append("$otherFollow others")
                    }
                },
                letterSpacing = letterSpacing,
                lineHeight =  lineHeight
            )
        }
    }
}






