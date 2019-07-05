# Project 3 - *TwitterApp*

**TwitterApp** is an android app that allows a user to view his Twitter timeline and post a new tweet. The app utilizes [Twitter REST API](https://dev.twitter.com/rest/public).

Time spent: **X** hours spent in total

## User Stories

The following **required** functionality is completed:

* [X]	User can **sign in to Twitter** using OAuth login
* [X]	User can **view tweets from their home timeline**
  * [X] User is displayed the username, name, and body for each tweet
  * [X] User is displayed the [relative timestamp](https://gist.github.com/nesquena/f786232f5ef72f6e10a7) for each tweet 
  * **Additional**: Display @ handle
  
* [X] User can **compose and post a new tweet**
  * [X] User can click a “Compose” icon in the Action Bar on the top right
  * [X] User can then enter a new tweet and post this to twitter
  * [X] User is taken back to home timeline with **new tweet visible** in timeline
  * [X] Newly created tweet should be manually inserted into the timeline and not rely on a full refresh

The following **stretch** features are implemented:

* [X] User can **see a counter with total number of characters left for tweet** on compose tweet page
* [X] User can **pull down to refresh tweets timeline**
* [X] User is using **"Twitter branded" colors and styles**
* [X] User sees an **indeterminate progress indicator** when any background or network task is happening
* [X] User can **select "reply" from detail view to respond to a tweet**
  * [X] User that wrote the original tweet is **automatically "@" replied in compose**
* [X] User can tap a tweet to **open a detailed tweet view**
  * [ ] User can **take favorite (and unfavorite) or reweet** actions on a tweet
* [ ] User can **see embedded image media within a tweet** on list or detail view.

The following **bonus** features are implemented:

* [X] User can view more tweets as they scroll with infinite pagination
* [X] Use Parcelable instead of Serializable using the popular [Parceler library](http://guides.codepath.org/android/Using-Parceler).
* [~] **partial:** Replace all icon drawables and other static image assets with [vector drawables](http://guides.codepath.org/android/Drawables#vector-drawables) where appropriate.
* [X] Use the popular ButterKnife annotation library to reduce view boilerplate.


The following **additional** features are implemented:

* [X] Added Twitter icon to ActionBar.
* [X] Added Twitter icon and styling to login page.
* [X] Added like, retweet, and home buttons on main timeline, as well as like, retweet, and follow buttons on detail view (though they don't have functionality).
* [X] Made profile pictures rounded.

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='http://i.imgur.com/link/to/your/gif/file.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

- Very minor struggle with some layout stuff (the vector drawables & some constraint layout issues).
- Definitely have a better understanding of click listeners now.
- The Butterknife library was actually very useful and quick to implement.
- I really liked how much we could play around with this app and add the features we wanted to add! Lots of room for creativity.

## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Android

## License

    Copyright [yyyy] [name of copyright owner]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
