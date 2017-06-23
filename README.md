# SwitchSkin
皮肤切换

工程目录介绍

Android-Skin-Loader
├── android-skin-loader-lib      // 皮肤加载库
├── app                          // 皮肤库应用实例
├── android-skin-loader-skin     // 皮肤包生成demo
└── skin-package                 // 皮肤包输出目录



1. 下载demo, 将BlackFantacy.skin放在SD卡根目录
2. 效果图


![image](https://github.com/dgyqll/SwitchSkin/blob/master/app/src/main/res/drawable/jdfw.gif)


用法
1. 在Application中进行初始化

public class SkinApplication extends Application {
	public void onCreate() {
		super.onCreate();
		// Must call init first 
		SkinManager.getInstance().init(this);
		SkinManager.getInstance().load();
	}
}

2. 在布局文件中标识需要换肤的View

...
xmlns:skin="http://schemas.android.com/android/skin"
...
  <TextView
     ...
     skin:enable="true" 
     ... />

3. 继承BaseActivity或者BaseFragmentActivity作为BaseActivity进行开发
4. 从.skin文件中设置皮肤

String SKIN_NAME = "BlackFantacy.skin";
String SKIN_DIR = Environment.getExternalStorageDirectory() + File.separator + SKIN_NAME;
File skin = new File(SKIN_DIR);
SkinManager.getInstance().load(skin.getAbsolutePath(),
				new ILoaderListener() {
					@Override
					public void onStart() {
					}

					@Override
					public void onSuccess() {
					}

					@Override
					public void onFailed() {
					}
				});

5. 重设默认皮肤

SkinManager.getInstance().restoreDefaultTheme();

6. 对代码中创建的View的换肤支持

主要由IDynamicNewView接口实现该功能，在BaseActivity，BaseFragmentActivity和BaseFragment中已经实现该接口.

public interface IDynamicNewView {
	void dynamicAddView(View view, List<DynamicAttr> pDAttrs);
}

**用法：**动态创建View后，调用dynamicAddView方法注册该View至皮肤映射表即可(如下).详见sample工程

	private void dynamicAddTitleView() {
		TextView textView = new TextView(getActivity());
		textView.setText("Small Article (动态new的View)");
		RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		param.addRule(RelativeLayout.CENTER_IN_PARENT);
		textView.setLayoutParams(param);
		textView.setTextColor(getActivity().getResources().getColor(R.color.color_title_bar_text));
		textView.setTextSize(20);
		titleBarLayout.addView(textView);
		
		List<DynamicAttr> mDynamicAttr = new ArrayList<DynamicAttr>();
		mDynamicAttr.add(new DynamicAttr(AttrFactory.TEXT_COLOR, R.color.color_title_bar_text));
		dynamicAddView(textView, mDynamicAttr);
	}
  
  
  7. 皮肤包是什么？如何生成？

    皮肤包（后缀名为.skin）的本质是一个apk文件，该apk文件不包含代码，只包含资源文件
    在皮肤包工程中（示例工程为skin/BlackFantacy）添加需要换肤的同名的资源文件，直接编译生成apk文件，再更改后缀名为.skinj即可（防止用户点击安装）
    使用gradle的同学，buildandroid-skin-loader-skin工程后即可在skin-package目录下取皮肤包（修改脚本中def skinName = "BlackFantacy.skin"换成自己想要的皮肤名）


原文地址：https://github.com/fengjundev/Android-Skin-Loader
