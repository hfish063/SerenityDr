Creating a Firebase Project

Go to Firebase Console.
Click Add project and follow the setup.
Click the Android icon on the banner that says "Get started by adding Firebase to your app"
Register an Android app with your package name. View the project files at the Android level (use the drop down tab near the folder icon in Android Studio)  
The package name is found in the Gradle Scripts folder in the file [build.gradle.kts] (Module:App). 
The string that is assigned to applicationId (ex. "com.example.myapplication") is your package name.
Download the google-services.json file and place it in your project's app/ directory. ( use the drop down tab near the folder to view files at the project level, then add the file under the app directory)
Follow firebase directions to add the rest of the plugins and dependencies
sync project with gradle files (button near the top right in android studio)

Now, the app is accessing firebase. This can be confirmed by running the app and using the Logcat (bottom left cat icon in Android Studio) and typing firebase in the search bar


List of Firebase SDK's that we may use
- Firebase (com.google.firebase) 
- Authentication (com.google.firebase.auth) ... FirebaseAuth class has two useful methods: createUserWithEmailAndPassword and signInWithEmailAndPassword
- Database (com.google.firebase.database)

https://firebase.google.com/docs/reference/kotlin/packages