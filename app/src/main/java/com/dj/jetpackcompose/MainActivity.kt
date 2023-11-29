package com.dj.jetpackcompose

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dj.jetpackcompose.data.Message
import com.dj.jetpackcompose.sampleData.SampleData
import com.dj.jetpackcompose.ui.theme.JetPackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            JetPackComposeTheme(false) {
                Surface(modifier = Modifier.fillMaxSize()) {
//                    MessageCard(Message("Dhanraj", "The learning Art"))
                    val list = remember {
                        SampleData.conversationSample
                    }
                    Conversation(messages = list)
                }
            }
        }
    }


//    @Composable
//    fun MessageCard(name: String){
//        Text(text = "Patrao $name")
//    }

    @Composable
    fun MessageCard(msg: Message){

        //Add padding round our message
        Row(modifier = Modifier.padding(all = 8.dp)) {
            Image(painter = painterResource(id = R.drawable.user),
                contentDescription = "Profile Pic",
                modifier = Modifier
                    .
                        //set image height to 40dp
                    size(40.dp)
                    //clip image to be shaped as circle
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
                )
            //add horizontal space between image and the column
            Spacer(modifier = Modifier.width(8.dp))

            //// We keep track if the message is expanded or not in this
            //   variable
            var isExpanded by remember { mutableStateOf(false) }

            //// surfaceColor will be updated gradually from one color to the other
            val surfaceColor by animateColorAsState(
                if(isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                label = "Hide Show Preview"
            )

            // We toggle the isExpanded variable when we click on this Column
            Column {
                Text(text = "Patrao ${msg.author}",
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.clickable { isExpanded = !isExpanded }
                )
                //add vertical space between author and message text
                Spacer(modifier = Modifier.height(4.dp))

                Surface(
                    shape = MaterialTheme.shapes.extraLarge,
                    shadowElevation = 1.dp,
                    // surfaceColor color will be changing gradually from primary to surface
                    color = surfaceColor,
                    // animateContentSize will change the Surface size gradually
                    modifier = Modifier
                        .animateContentSize()
                        .padding(1.dp)
                ) {

                    Text(text = "${msg.body}",
                        modifier = Modifier.padding(all = 4.dp),
                        // If the message is expanded, we display all its content
                        // otherwise we only display the first line
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1 ,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

            }
        }

    }

    @Preview("Dark Mode")
    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark Mode"
    )
    @Composable
    fun PreviewMessageCard(){
//        MessageCard(name = "Carol")

        JetPackComposeTheme {
            Surface(
//                modifier = Modifier.fillMaxSize()
            ) {
                MessageCard(msg = Message("Dhanraj", "Look at me"))
            }
        }
    }


    @Composable
    fun Conversation(messages: List<Message>){
        LazyColumn{
            items(
                count = messages.size,
                key = {
                  "id${it}"
                },
                itemContent = { index ->
                    val mItem = messages[index]
                    MessageCard(msg = mItem)
                }
                )
        }
    }

    @Preview(showBackground = true, widthDp = 320)
    @Composable
    fun PreviewConversation(){
        JetPackComposeTheme {
            Conversation(messages = SampleData.conversationSample)
        }
    }



}