Auto Bundle
=======

Field binding for Android Bundle's extra which users annotaion processing to generate boilerplate code for you.

- Eliminate `bundle.getXXX("key")` calls by using `@Extra` on fields


```java
@AutoBundle
public class ExampleActivity  extend Activity {
    @Extra(key = "name")
    String userName;
    @Extra(key = "age")
    String age;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AutoBundle.read(this, getIntent().getExtras(), savedInstanceState);
        //TODO user fields...
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        AutoBundle.write(this, outState);
    }
}
```

- Eliminate `bundle.putXXX("key", value)` calls by using `@AutoBundle` on class

```java
void gotoExample() {
	Intent intent = new Intent(context, ExampleActivity.class);
	intent.putExtras(new ATBundleExampleActivity().name("name").age(26).build());
	context.startActivity(intent);
}
```

Add it to your project today
-------

```gradle
buildscript {
    repositories {
      mavenCentral()
    }
    dependencies {
        // the latest version of the android-apt plugin
        classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'
    }
}

apply plugin: 'com.android.application'
apply plugin: 'com.neenbedankt.android-apt'

dependencies {
	compile project(":bundle")
	apt project(":compiler")
}
```

Proguard
---------
```proguard
-keep class com.jackoder.auto.bundle.** { *; }

-keep class * implements com.jackoder.auto.bundle.IAutoBundle { *; }

-keepclasseswithmembernames class * {
    @com.jackoder.auto.bundle.ann.* <fields>;
}
```

TODO
-----

- Add library to jcenter

License
-------

    Copyright 2016 Jackoder

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
