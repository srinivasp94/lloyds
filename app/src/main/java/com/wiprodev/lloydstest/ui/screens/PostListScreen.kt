package com.wiprodev.lloydstest.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.wiprodev.domain.model.PostModel
import com.wiprodev.lloydstest.R
import com.wiprodev.lloydstest.state.PostsResponse
import com.wiprodev.lloydstest.viewmodel.PostsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostListScreen(postsViewModel: PostsViewModel = hiltViewModel()) {
    val response by postsViewModel.postResponse.collectAsState()
    Scaffold(modifier = Modifier.background(Color.White),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.posts),
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                ),
            )
        }) { innerPadding ->
        Box(
            modifier = Modifier
                .background(Color.White)
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            when (response) {
                is PostsResponse.Loading -> {
                    ShowProgressDialogView()
                }

                is PostsResponse.Failure -> {
                    val errorMsg =
                        (response as PostsResponse.Failure<List<PostModel>>).message.toString()
                    ShowErrorMessageInCenter(errorMsg)
                }

                is PostsResponse.Success -> {
                    val postList = (response as PostsResponse.Success<List<PostModel>>).data
                    PostListView(postList)
                }
            }
        }
    }
}

@Composable
fun PostListView(postList: List<PostModel>) {
    Box(modifier = Modifier.background(Color.White)) {
        LazyColumn {
            items(postList, itemContent = { item: PostModel ->
                PostItemView(item)
            })
        }
    }
}

@Composable
fun PostItemView(model: PostModel) {
    Card(
        /* colors = CardDefaults.cardColors(
             containerColor = MaterialTheme.colorScheme.surfaceVariant,
         ),*/
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp)
            .background(Color.White)
    ) {
        ConstraintLayout(modifier = Modifier.padding(10.dp)) {
            val (title, body, likesIcon, likes, dislikesIcon, dislikes, viewIcon, views) = createRefs()

            Text(
                text = model.title,
                modifier = Modifier
                    .constrainAs(title) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    }
                    .fillMaxWidth(),
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = model.body,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(body) {
                        start.linkTo(parent.start)
                        top.linkTo(title.bottom)
                    },
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                fontSize = 14.sp,
                color = Color.LightGray,
            )
            Icon(
                painter = painterResource(R.drawable.baseline_thumb_up_off_alt_24),
                contentDescription = "likes",
                modifier = Modifier
                    .padding(2.dp)
                    .constrainAs(likesIcon) {
                        start.linkTo(parent.start)
                        top.linkTo(body.bottom)
                    }
            )
            Text(
                text = model.likes.toString(),
                modifier = Modifier
                    .padding(2.dp)
                    .constrainAs(likes) {
                        start.linkTo(likesIcon.end)
                        top.linkTo(body.bottom)
                    },
                fontSize = 10.sp
            )

            Icon(
                painter = painterResource(R.drawable.sharp_face_down_24),
                contentDescription = "likes",
                modifier = Modifier
                    .padding(2.dp)
                    .constrainAs(dislikesIcon) {
                        start.linkTo(likes.end)
                        top.linkTo(body.bottom)
                    }
            )
            Text(
                text = model.disLikes.toString(),
                modifier = Modifier
                    .padding(2.dp)
                    .constrainAs(dislikes) {
                        start.linkTo(dislikesIcon.end)
                        top.linkTo(body.bottom)
                    },
                fontSize = 10.sp
            )

            Icon(
                painter = painterResource(R.drawable.outline_domino_mask_24),
                contentDescription = "likes",
                modifier = Modifier
                    .padding(2.dp)
                    .constrainAs(viewIcon) {
                        end.linkTo(views.start)
                        top.linkTo(body.bottom)
                    }
            )
            Text(
                text = model.views.toString(),
                modifier = Modifier
                    .padding(2.dp)
                    .constrainAs(views) {
                        end.linkTo(parent.end)
                        top.linkTo(body.bottom)
                    },
                fontSize = 10.sp
            )

        }
    }

}