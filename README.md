[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-GifView-green.svg?style=true)](https://android-arsenal.com/details/1/2968)
# Deprecated 
GifView is deprecated. No more development will be taking place. For an up-to-date gif lib use <a href = "https://github.com/bumptech/glide">Glide</a>.

Thanks for all your support!
# GifView
Library for playing gifs on Android

Simple android view to display gifs efficiently. You can start, pause and stop gifView. Example usages can be found in example project.

Inspired by <a href = "https://github.com/sbakhtiarov/gif-movie-view">sbakhtiarov/gif-movie-view</a>
# Screen
<img src = "https://media.giphy.com/media/26tPsYL5hA4IEGAfu/giphy.gif"></img>
# Usage
 <pre style='color:#000000;background:#ffffff;'><span style='color:#a65700; '>&lt;</span><span style='color:#5f5035; '>cuneyt.example.com.gifview.Utils.GifView</span>
            <span style='color:#007997; '>android</span><span style='color:#800080; '>:</span><span style='color:#274796; '>id</span><span style='color:#808030; '>=</span><span style='color:#800000; '>"</span><span style='color:#0000e6; '>@+id/gif1</span><span style='color:#800000; '>"</span>
            <span style='color:#007997; '>android</span><span style='color:#800080; '>:</span><span style='color:#274796; '>layout_width</span><span style='color:#808030; '>=</span><span style='color:#800000; '>"</span><span style='color:#0000e6; '>wrap_content</span><span style='color:#800000; '>"</span>
            <span style='color:#007997; '>android</span><span style='color:#800080; '>:</span><span style='color:#274796; '>layout_height</span><span style='color:#808030; '>=</span><span style='color:#800000; '>"</span><span style='color:#0000e6; '>wrap_content</span><span style='color:#800000; '>"</span>
            <span style='color:#007997; '>android</span><span style='color:#800080; '>:</span><span style='color:#274796; '>visibility</span><span style='color:#808030; '>=</span><span style='color:#800000; '>"</span><span style='color:#0000e6; '>gone</span><span style='color:#800000; '>"</span>
            <span style='color:#007997; '>custom</span><span style='color:#800080; '>:</span><span style='color:#274796; '>gif</span><span style='color:#808030; '>=</span><span style='color:#800000; '>"</span><span style='color:#0000e6; '>@mipmap/gif1</span><span style='color:#800000; '>"</span> <span style='color:#a65700; '>/></span>
</pre>
            
              GifView gifView1 = (GifView) view.findViewById(R.id.gif1);
                     gifView1.setVisibility(View.VISIBLE);
                       gifView1.setPaused(true);
                       gifView1.setMovie(movie);
                       gifView1.setMovieResource(resource);
                       gifView1.setMovieTime(time);
                       gifView1.getMovie();
                       
#Sample APK
https://www.dropbox.com/s/a4rl91lmlm6loj0/base%20%281%29.apk?dl=0


#License
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
