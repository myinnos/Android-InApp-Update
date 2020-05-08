# Android-InApp-Update
InApp update library for Android, In-app update is a library a new request flow to prompt active users to update your app.

In-app updates works only with devices running Android 5.0 (API level 21) or higher, and requires you to use Play Core library 1.5.0 or higher. Additionally, in-app updates support apps running on only Android mobile devices and tablets, and Chrome OS devices.
  
#### Kindly use the following links to use this library:

In build.gradle (Project)
```java
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```
And then in the other gradle file(may be your app gradle or your own module library gradle, but never add in both of them to avoid conflict.)
```java
dependencies {
    ...
    implementation 'com.github.myinnos:Android-InApp-Update:BETA-0.2'	  
    implementation 'com.google.android.play:core:1.7.2' //to initiate AppUpdateManager
}
```          
How to use
-----
**Step 1:** Initiate AppUpdateManager in MainActivity:
```java
private AppUpdateManager appUpdateManager;
  .....
    
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         .............

        appUpdateManager = AppUpdateManagerFactory.create(this);
        InAppUpdate.setImmediateUpdate(appUpdateManager, this);
    }
    
```
**Step 2:** Add function in onResume.
```java
@Override
    protected void onResume() {
        super.onResume();
        InAppUpdate.setImmediateUpdateOnResume(appUpdateManager, this);
    }
    
```

**optional/add-ons:** onActivityResult/strings/colors
```java
 @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == InAppUpdate.REQUEST_APP_UPDATE){
           ........
        }
    }
  
  // strings [For Flexibale Update]
  <string name="in_app_snack_bar_message">Update almost finished!</string>
  <string name="in_app_snack_bar_action_title">RESTART</string>
  // colors [For Flexibale Update]
  <color name="in_app_snack_bar_text_color">#37474F</color>
```

After meeting these requirements, your app can support the following UX for in-app updates:

##### Immediate:
A full screen user experience that requires the user to update and restart the app in order to continue using the app. This UX is best for cases where an update is critical for continued use of the app. After a user accepts an immediate update, Google Play handles the update installation and app restart.

![Android-InApp-Update Immediate - Example](https://developer.android.com/images/app-bundle/immediate_flow.png)
##### Flexible: [BETA]
A user experience that provides background download and installation with graceful state monitoring. This UX is appropriate when it’s acceptable for the user to use the app while downloading the update. For example, you want to urge users to try a new feature that’s not critical to the core functionality of your app.

![Android-InApp-Update Flexible - Example](https://developer.android.com/images/app-bundle/flexible_flow.png)
##### Any Queries? or Feedback, please let me know by opening a [new issue](https://github.com/myinnos/Android-InApp-Update/issues/new)!

## Contact
#### Prabhakar Thota
* :globe_with_meridians: Website: [myinnos.in](http://www.myinnos.in "Prabhakar Thota")
* :email: e-mail: contact@myinnos.in
* :mag_right: LinkedIn: [PrabhakarThota](https://www.linkedin.com/in/prabhakarthota "Prabhakar Thota on LinkedIn")
* :thumbsup: Twitter: [@myinnos](https://twitter.com/myinnos "Prabhakar Thota on twitter")    
* :camera: Instagram: [@prabhakar_t_](https://www.instagram.com/prabhakar_t_/ "Prabhakar Thota on Instagram")   

> If you appreciate my work, consider buying me a cup of :coffee: to keep me recharged :metal: by [PayPal](https://www.paypal.me/fansfolio)

License
-------

    Copyright 2017 MyInnos

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
