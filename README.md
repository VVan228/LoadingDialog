# LoadingDialog
#### Library to show fancy loading dialog with animation

## implimentation algorythm:

1) paste this in build.gradle (project)
``` 
allprojects {
     repositories {
          ...
          maven { url 'https://jitpack.io' }
     }
} 
```
2) paste this in build.gradle (module)
```
dependencies {
     implementation 'com.github.VVan228:LoadingDialog:0.3'
}
```
#### done
## usage example
```
DialogFragment dial = new DialogFragment();
Loading load = new Loading(Color.rgb(255,250,250), Color.TRANSPARENT, Color.RED, 20.0f);
load.show(getSupportFragmentManager(), "dial");
```
#### Loading class takes 4-5 args:
1) main background color
2) second background color (fragment back)
3) foreground color
4) border radius
5) string (default - Loading)

#### cancel dialog
```
load.cancel();
```
### onCancel
You can also set onCancelListener like this:
```
load.setOnCancelListener(new onCancelListener() {
     @Override
     public void onCancel() {
          Log.d("animation", "cancelled");
     }
});
```
