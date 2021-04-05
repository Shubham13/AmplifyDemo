package com.example.amplifydemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.amplifyframework.api.graphql.model.ModelMutation
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.generated.model.Todo

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Amplify.Auth.fetchAuthSession(
            { Log.i("AmplifyQuickstart", "Auth session = $it") },
            { Log.e("AmplifyQuickstart", "Failed to fetch auth session", ) }
        )

        val todo = Todo.builder()
            .name("My first todo")
            .description("todo description")
            .build()

        Amplify.API.mutate(
            ModelMutation.create(todo),
            { Log.i("MyAmplifyApp", "Added Todo with id: ${it.data.id}") },
            { Log.e("MyAmplifyApp", "Create failed", it) }
        )
    }
}