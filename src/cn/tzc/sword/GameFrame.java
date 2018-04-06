package cn.tzc.sword;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFrame;

/**
 * 本类表示游戏项目的窗口类
 * @author nanbei
 *
 */

public class GameFrame {
	public static void main(String[] args) {
		
		JFrame chuangkou = new JFrame();//创建窗口类的实例化对象
		chuangkou.setSize(1024, 768);//设置窗口对象的属性
		chuangkou.setTitle("仙剑奇侠-Version1.0");
		//windows.setLocation(x, y);//设置窗口显示位置
		chuangkou.setLocationRelativeTo(null);//设置窗口居中显示的方法
		chuangkou.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置窗口默认关闭操作选项
		
		GamePanel huaban = new GamePanel();//创建自定义画板类的实例化对象
		chuangkou.add(huaban);//关联窗口对象和自定义画板对象
		
		Thread xiancheng = new Thread(huaban);//创建线程类的实例化对象，并且关联自定义画板对象
		xiancheng.start();//启动线程对象-->会自动调动执行run();方法
		
		//声明自定义画板类中的键盘监听事件对应方法的有效性
		chuangkou.addKeyListener(huaban);
		huaban.addKeyListener(huaban);
		
		chuangkou.setVisible(true);//让窗口对象显示
		palyMusic();//调用播放音乐类
	}
	//播放背景音乐
	private static void palyMusic() {
		try {
			URL backgroundmusic;
			File address = new File("music.wav");
			backgroundmusic = address.toURL();//解析路径
			
			AudioClip audio;
			audio = Applet.newAudioClip(backgroundmusic);
			audio.play();
			audio.loop();//循环播放
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
