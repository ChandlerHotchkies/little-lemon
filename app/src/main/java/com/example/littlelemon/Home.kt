package com.example.littlelemon

import android.view.MenuItem
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemon.ui.theme.*
import com.example.littlelemon.ui.theme.LittleLemonTheme
import kotlinx.coroutines.delay

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Home(navController: NavController, items: List<MenuItemRoom>) {
    var searchPhrase by remember{
        mutableStateOf("")
    }
    var filteredItems by remember{
        mutableStateOf(emptyList<MenuItemRoom>())
    }
    var selectedIndex by remember{
        mutableStateOf("")
    }
    val listState = rememberLazyListState()
    LittleLemonTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    backgroundColor = Transparent,
                    title = {
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "Little Lemon Logo",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 40.dp)
                                .height(35.dp)
                        )},
                    actions = {
                        Image(
                            painter = painterResource(id = R.drawable.profile),
                            contentDescription = "Profile picture",
                            modifier = Modifier
                                .clip(RoundedCornerShape(50))
                                .padding(10.dp)
                                .clickable { navController.navigate(Profile.route) }
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        ){ PaddingValues ->
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PaddingValues)
            ){
                item{
                    Box(
                        modifier = Modifier.background(color = Green)
                    ) {
                        Text(
                            text = stringResource(id = R.string.restaurant_name),
                            style = MaterialTheme.typography.h1,
                            color = Yellow,
                            fontSize = 60.sp,
                            modifier = Modifier
                                .padding(start = 20.dp, end = 5.dp, top = 5.dp)
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, end = 20.dp, top = 60.dp, bottom = 20.dp)
                        ){
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth(0.65f)
                            ){
                                Text(
                                    text = stringResource(id = R.string.city),
                                    style = MaterialTheme.typography.h2,
                                    color = White
                                )
                                Text(
                                    text = stringResource(id = R.string.short_description),
                                    style = MaterialTheme.typography.subtitle1,
                                    color = White
                                )
                            }
                            Image(
                                painter = painterResource(id = R.drawable.hero_image),
                                contentDescription = "Hero Image",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(150.dp)
                                    .padding(top = 20.dp)
                                    .clip(RoundedCornerShape(15.dp))
                            )
                        }
                        TextField(
                            value = searchPhrase,
                            leadingIcon = {
                                Icon( imageVector = Icons.Default.Search,
                                    contentDescription = "")
                            },
                            onValueChange = { searchPhrase = it },
                            textStyle =  MaterialTheme.typography.caption,
                            placeholder = { Text(text = "Enter search phrase") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 20.dp, start = 20.dp, top = 235.dp, end = 20.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(color = White)
                        )
                    }

                }
                item {
                    Text(
                        text = stringResource(id = R.string.order_category),
                        style = MaterialTheme.typography.h3,
                        modifier = Modifier
                            .padding(top = 20.dp, start = 20.dp, end = 20.dp)
                    )
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                    ) {
                        items(
                            items = items.map{it.category}.toSet().toList(),
                            itemContent = { menuItem ->
                                val bgColor by animateColorAsState(if (menuItem == selectedIndex) Black else White)
                                val txtColor by animateColorAsState(if (menuItem == selectedIndex) White else Green)
                                Button(
                                    onClick = {
                                        if (selectedIndex != menuItem) {
                                            filteredItems = items.filter{ it.category.contains(menuItem,ignoreCase=true)}
                                            selectedIndex = menuItem
                                        } else {
                                            filteredItems = items
                                            selectedIndex = ""
                                        }
                                    },
                                    colors = ButtonDefaults.buttonColors(bgColor),
                                    modifier = Modifier
                                        .padding(horizontal = 10.dp)
                                        .clip(RoundedCornerShape(50))
                                ){
                                    Text(
                                        text = menuItem.replaceFirstChar { it.uppercase() },
                                        color = txtColor,
                                        style = MaterialTheme.typography.h4,
                                    )
                                }
                            }

                        )
                    }
                    Divider(
                        color = White,
                        thickness = 1.dp,
                        modifier = Modifier.padding(top = 20.dp)
                    )
                }
                items(
                    items = if(searchPhrase.isNotEmpty()) {
                        filteredItems.filter{ it.title.contains(searchPhrase,ignoreCase=true) }
                    } else {
                        filteredItems.ifEmpty { items }
                    },
                    itemContent = { menuItem ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                        ) {
                            Column(modifier = Modifier.fillMaxWidth(0.8f)) {
                                Text(
                                    text = menuItem.title,
                                    style = MaterialTheme.typography.h4,
                                    modifier = Modifier.padding(vertical = 5.dp)
                                )
                                Text(
                                    text = menuItem.Description,
                                    style = MaterialTheme.typography.body1,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier.padding(top = 5.dp, bottom = 5.dp,end = 20.dp)
                                )
                                Text(
                                    text = "$"+"%.2f".format(menuItem.price),
                                    style = MaterialTheme.typography.caption,
                                    modifier = Modifier.padding(vertical = 5.dp)
                                )
                            }
                            GlideImage(
                                model = menuItem.image,
                                contentDescription = menuItem.title,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(90.dp)
                                    .padding(vertical = 10.dp)
                            )
                        }
                        Divider(
                            color = White,
                            thickness = 1.dp,
                            modifier = Modifier.padding(horizontal = 5.dp)
                        )
                    },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    LittleLemonTheme {
    }
}