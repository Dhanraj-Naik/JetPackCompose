package com.dj.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dj.jetpackcompose.data.Message
import com.dj.jetpackcompose.ui.theme.JetPackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            MessageCard(name = "Carol")
            MessageCard(Message("Dhanraj", "The learning Art"))
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
                )
            //add horizontal space between image and the column
            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(text = "Patrao ${msg.author}")
                //add vertical space between author and message text
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "${msg.body}")
            }
        }

    }
    
    @Preview
    @Composable
    fun PreviewMessageCard(){
//        MessageCard(name = "Carol")
        MessageCard(msg = Message("Dhanraj", "Look at me"))
    }

}