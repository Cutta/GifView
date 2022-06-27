[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-GifView-green.svg?style=true)](https://android-arsenal.com/details/1/2968)
<a href="http://www.methodscount.com/?lib=com.github.Cutta%3AGifView%3A1.1"><img src="https://img.shields.io/badge/Methods and size-55 | 6 KB-e91e63.svg"/></a>

# GifView
Library for playing gifs on Android

Simple android view to display gifs efficiently. You can start, pause and stop gifView. Example usages can be found in example project.

Inspired by <a href = "https://github.com/sbakhtiarov/gif-movie-view">sbakhtiarov/gif-movie-view</a>
# Screen
<img src = "https://media.giphy.com/media/26tPsYL5hA4IEGAfu/giphy.gif"></img>
# Usage
Add these lines on top-level build file
```
repositories {
    maven {
        url "https://jitpack.io"
    }
}
```
On app's build.gradle
```
implementation 'com.github.Cutta:GifView:1.6'
```

# Usage
 <pre style='color:#000000;background:#ffffff;'><span style='color:#a65700; '>&lt;</span><span style='color:#5f5035; '>com.cunoraz.gifview.library.GifView</span>
            <span style='color:#007997; '>android</span><span style='color:#800080; '>:</span><span style='color:#274796; '>id</span><span style='color:#808030; '>=</span><span style='color:#800000; '>"</span><span style='color:#0000e6; '>@+id/gif1</span><span style='color:#800000; '>"</span>
            <span style='color:#007997; '>android</span><span style='color:#800080; '>:</span><span style='color:#274796; '>layout_width</span><span style='color:#808030; '>=</span><span style='color:#800000; '>"</span><span style='color:#0000e6; '>wrap_content</span><span style='color:#800000; '>"</span>
            <span style='color:#007997; '>android</span><span style='color:#800080; '>:</span><span style='color:#274796; '>layout_height</span><span style='color:#808030; '>=</span><span style='color:#800000; '>"</span><span style='color:#0000e6; '>wrap_content</span><span style='color:#800000; '>"</span>
            <span style='color:#007997; '>custom</span><span style='color:#800080; '>:</span><span style='color:#274796; '>gif</span><span style='color:#808030; '>=</span><span style='color:#800000; '>"</span><span style='color:#0000e6; '>@mipmap/gif1</span><span style='color:#800000; '>"</span> <span style='color:#a65700; '>/></span>
</pre>
            
              GifView gifView1 = (GifView) view.findViewById(R.id.gif1);
                     gifView1.setVisibility(View.VISIBLE);
                       gifView1.play();
                       gifView1.pause();
                       gifView1.setGifResource(R.mipmap.gif5);
                       gifView1.getGifResource();
                       gifView1.setMovieTime(time);
                       gifView1.getMovie();

# Credits
<a href = "https://plus.google.com/u/0/116948443141721480957"><img src = "https://raw.githubusercontent.com/florent37/DaVinci/master/mobile/src/main/res/drawable-hdpi/gplus.png"/></a>
<a href = "https://twitter.com/Cuneyt_Carikci"><img src = "https://raw.githubusercontent.com/florent37/DaVinci/master/mobile/src/main/res/drawable-hdpi/twitter.png"/></a>
<a href = "https://www.linkedin.com/in/c%C3%BCneyt-%C3%A7ar%C4%B1k%C3%A7i-b4619161?trk=nav_responsive_tab_profile_pic"><img src = "https://raw.githubusercontent.com/florent37/DaVinci/master/mobile/src/main/res/drawable-hdpi/linkedin.png"/></a>

# License
Copyright 2015 Cüneyt Çarıkçi.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
