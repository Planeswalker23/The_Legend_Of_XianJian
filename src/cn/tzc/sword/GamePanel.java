package cn.tzc.sword;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 本类表示游戏项目的自定义画板类，即界面
 * @author Administrator
 *
 */
//Java当中：遵循单继承（类）多实现（接口）
public class GamePanel extends JPanel implements Runnable,KeyListener{
	//定义李家村
	Image ljcImage;
	int ljcX;
	int ljcY;
	//定义阿旺婶
	Image[] awsImages;
	int awsX;
	int awsY;
	int awsIndex;//用来表示阿旺婶图片数组的当前下标
	//定义阿朱
	Image[] azImages;
	int azX;
	int azY;
	int azIndex;
	//定义旺财嫂
	Image[] wcsImages;
	int wcsX;
	int wcsY;
	int wcsIndex;
	//定义小孩
	Image[] xhImages;
	int xhX;
	int xhY;
	int xhIndex;
	//定义母鸡
	Image[] mjImages;
	int mjX;
	int mjY;
	int mjIndex;
	//定义小鸡
	Image[] xjImages;
	int xjX;
	int xjY;
	int xjIndex;
	//定义小小鸡
	Image[] xxjImages;
	int xxjX;
	int xxjY;
	int xxjIndex;
	//定义李逍遥
	Image[] lxyUpImages;
	Image[] lxyDownImages;
	Image[] lxyLeftImages;
	Image[] lxyRightImages;
	int lxyX;
	int lxyY;
	int lxyIndex;
	int lxyDir;//用来表示李逍遥当前方向
	int lxySpeed;//用来表示李逍遥的移动速度
	Image lxyImage;//用来表示李逍遥的当前图片内容(当李逍遥移动时需要改变该值得内容)
	
	//定义对话框
	int dialogX;
	int dialogY;
	Image dialogImage;
	boolean isTalk;
	String[] awsMessages;
	int awsMessagesIndex;
	String[] xjMessages;
	int xjMessagesIndex;
	String[]  scwcsMessages;
	int scwcsMessagesIndex;
	int MessageId;//对话选择
	
	//定义障碍物图
	BufferedImage ljcDataMap;
	BufferedImage scDataMap;
	
	//更换场景准备
	int MapId;//id号为1时场景lcj;id号为2时场景ljc市场
	Image[] scImages; 
	int scIndex;
	
	//欢迎界面
	int WelcomeX;
	int WelcomeY;
	Image WelcomeImage;
	int ScheduleX;
	int ScheduleY;
	int isback;//0为初始，1为进入市场，2为进入李家村，3为出海
	
	String[] welcomeMessages;
	int welcomeMessagesIndex;
	String[] wcsMessages;
	int wcsMessagesIndex;
	String[] xhMessages;
	int xhMessagesIndex;
	String[] azMessages;
	int azMessagesIndex;
	String[] WrongMessages;
	int WrongIndex;
	String[] backMessages;
	int backIndex;
	
	//判断鸡在不在背包
	int isinbag;//1为鸡在地上;2为鸡在背包;3为鸡送给婶婶
	
	//游戏结束界面
	int EndX;
	int EndY;
	Image EndImage;
	
	boolean isstart;
	//重新开始按钮
	Image RestartImage;
	
	//等级系统内容
	Image LevelImages[];
	int LevelIndex;
	boolean isup;
	
	//操作指导图片信息
	int GuideX;
	int GuideY;
	
	//进入游戏按钮图片信息
	int entergameX;
	int entergameY;
	
	//任务栏图片信息
	int MissionX;
	int MissionY;
	Image MissionImages[];
	int MissionIndex;
	
	//构造方法
	public GamePanel(){
		
	    //初始化李家村
		try {
			//绝对路径：以盘符（C:）或者根目录（/）开始的路径
			//相对路径：在工程中写相对路径，是从绝对路径中工程名称后面开始书写
			ljcImage = ImageIO.read(
					new File("Legend_of_Sword_and_Fairy/LiJiaCun/0.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ljcX = -200;
		ljcY = -200;
		//初始化阿旺婶
		awsImages = new Image[17];
		for(int i = 0;i<awsImages.length;i++){
			//将第i张图片从本地磁盘读取到内存当中，存储在数组的第i个下标位置上
			try {
				awsImages[i]=ImageIO.read(
						new File("Legend_of_Sword_and_Fairy/AWangShen/" + i +".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//以ljc地图为坐标系的坐标
		awsX = 430 + 200;
		awsY = 390 + 200;
		awsIndex = 0;
		//初始化阿朱
		azImages = new Image[6];
		for(int i = 0;i<azImages.length;i++){
			//将第i张图片从本地磁盘读取到内存当中，存储在数组的第i个下标位置上
			try {
				azImages[i]=ImageIO.read(
						new File("Legend_of_Sword_and_Fairy/AZhu/" + i +".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		azX = 810 + 200;
		azY = 400 + 200;
		azIndex = 0;
		//初始化旺财嫂
		wcsImages = new Image[14];
		for(int i = 0;i<wcsImages.length;i++){
			//将第i张图片从本地磁盘读取到内存当中，存储在数组的第i个下标位置上
			try {
				wcsImages[i]=ImageIO.read(
						new File("Legend_of_Sword_and_Fairy/WangCaiSao/" + i +".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		wcsX = 700 + 200;
		wcsY = 220 + 200;
		wcsIndex = 0;
		//初始化小孩
		xhImages = new Image[4];
		for(int i = 0;i<xhImages.length;i++){
			//将第i张图片从本地磁盘读取到内存当中，存储在数组的第i个下标位置上
			try {
				xhImages[i]=ImageIO.read(
						new File("Legend_of_Sword_and_Fairy/XiaoHai/" + i +".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		xhX = 1000 + 200;
		xhY = 600 + 200;
		xhIndex = 0;
		//初始化母鸡
		mjImages = new Image[6];
		for(int i = 0;i<mjImages.length;i++){
			//将第i张图片从本地磁盘读取到内存当中，存储在数组的第i个下标位置上
			try {
				mjImages[i]=ImageIO.read(
						new File("Legend_of_Sword_and_Fairy/MuJi/" + i +".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		mjX = 770 + 200;
		mjY = 500 + 200;
		mjIndex = 0;
		//初始化小鸡
		xjImages = new Image[2];
		for(int i = 0;i<xjImages.length;i++){
			//将第i张图片从本地磁盘读取到内存当中，存储在数组的第i个下标位置上
			try {
				xjImages[i]=ImageIO.read(
						new File("Legend_of_Sword_and_Fairy/XiaoJi/" + i +".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		xjX = 750 + 200;
		xjY = 520 + 200;
		xjIndex = 0;
		//初始化小小鸡
		xxjImages = new Image[2];
		for(int i = 0;i<xxjImages.length;i++){
			//将第i张图片从本地磁盘读取到内存当中，存储在数组的第i个下标位置上
			try {
				xxjImages[i]=ImageIO.read(
						new File("Legend_of_Sword_and_Fairy/XiaoXiaoJi/" + i + ".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		xxjX = 750 + 200;
		xxjY = 545 + 200;
		xxjIndex = 0;
		//初始化李逍遥
		lxyUpImages = new Image[8];
		lxyDownImages = new Image[8];
		lxyLeftImages = new Image[8];
		lxyRightImages = new Image[8];
		for(int i = 0; i < lxyUpImages.length; i++){
			try {
				lxyUpImages[i] = ImageIO.read
						(new File("Legend_of_Sword_and_Fairy/LiXiaoYao_Up/" + i + ".png"));
				lxyDownImages[i] = ImageIO.read
						(new File("Legend_of_Sword_and_Fairy/LiXiaoYao_Down/" + i + ".png"));
				lxyLeftImages[i] = ImageIO.read
						(new File("Legend_of_Sword_and_Fairy/LiXiaoYao_Left/" + i + ".png"));
				lxyRightImages[i] = ImageIO.read
						(new File("Legend_of_Sword_and_Fairy/LiXiaoYao_Right/" + i + ".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		lxyX = 600 + 200;
		lxyY = 380 + 200;
		lxyIndex = 0;
		lxyDir = KeyEvent.VK_DOWN;//默认向下
		
		lxySpeed = 10;
		lxyImage = lxyDownImages[lxyIndex];
		
		//初始化对话框
		try {
			dialogImage = ImageIO.read(new File("Legend_of_Sword_and_Fairy/LiaoTian/0.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//主角对话框X150;配角对话框274
		isTalk = false;
		welcomeMessages = new String[]{"我叫李逍遥！","这是我的家乡——李家村！","那不是阿旺嫂吗！"
				,"小的时候她非常照顾我！","我记得她经常偷偷给我吃糖。","去拜访一下她吧！"};
		welcomeMessagesIndex = 0;
		awsMessages = new String[]{"咿！这不是逍遥吗！","这么早起床啊！","都长那么大了，真好！"
				,"还有，旺财嫂她有点事找你帮忙！","你去村子最北面找她！"};
		awsMessagesIndex = 0;
		wcsMessages = new String[]{"逍遥你来了啊！","看你一副那么闲的样子"
				,"去帮我照看孩子吧。","他们在村子东南边跳绳。"};
		wcsMessagesIndex = 0;
		xhMessages = new String[]{"皮球,香蕉梨,马兰开花二十一","二五六,二五七,二八二九三十一","。。。"
				,"逍遥哥哥你不跟我们一起玩吗？","算了","阿朱姐姐在家，她好像有事想找你帮忙。"};
		xhMessagesIndex = 0;
		azMessages = new String[]{"逍遥哥哥!","好久不见了。。。","你过得好吗？","我想请你帮个忙"
				," 把这只老母鸡送给市场的婶婶吧！","你不知道怎么去市场？","从村西口能过去","别忘记把母鸡带过去哟！"};
		azMessagesIndex = 0;
		xjMessages = new String[]{"呱","呱呱","呱呱呱","两只小鸡在跟着母鸡学捕虫。"
				,"老母鸡长得真壮！","快去给市场的婶婶送过去吧"};
		xjMessagesIndex = 0;
		scwcsMessages = new String[]{"阿朱真是个孝顺的孩子！","逍遥谢谢你给我送来啊！","你已经长大了"
				,"是时候去见识见识外面的世界了","外面有你一生的牵挂。","。。。","。。。。。。","你怎么还不走！！！"};
		scwcsMessagesIndex = 0;
		WrongMessages = new String[]{"你找错人啦！","现在你不该来找我！！！"};
		WrongIndex = 0;
		backMessages = new String[]{"村子外面太凶险了！","你还没有足够强大！","回村子里历练历练再出海吧！"};
		backIndex = 0;
		MessageId = 1;
		isinbag = 1;
		
		//初始化障碍物图
		try {
			ljcDataMap = ImageIO.read(new File("Legend_of_Sword_and_Fairy/LiJiaCun/RedMap.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			scDataMap = ImageIO.read
					(new File("Legend_of_Sword_and_Fairy/LiJiaCunShiChang/RedMap.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//初始化更换场景内容
		scIndex = 0;
		scImages = new Image[3];
		for(int i = 0; i < scImages.length; i++){
			try {
				scImages[i] = ImageIO.read
						(new File("Legend_of_Sword_and_Fairy/LiJiaCunShiChang/" + i + ".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//初始化欢迎界面内容
		MapId = 3;
		WelcomeX = 0;
		WelcomeY = 0;
		try {
			WelcomeImage = ImageIO.read(new File("start.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ScheduleX = 150;
		ScheduleY = 100;
		isback = 0;
		
		//初始化游戏结束界面MapId=4
		EndX = -200;
		EndY = 0;
		try {
			EndImage = ImageIO.read(new File("outworld.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		isstart = true;
		try {
			RestartImage = ImageIO.read(new File("restart.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//初始化等级系统内容
		LevelImages = new Image[7];
		LevelIndex = 1;
		for(int i = 1; i <= LevelImages.length - 1; i++){
			try {
				LevelImages[i] = ImageIO.read(new File("level/lv" + i + ".gif"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		isup = false;
		
		//初始化操作指导图片内容
		GuideX = 100;
		GuideY = 160;
		
		//初始化进入游戏按钮图片信息
		entergameX = 0;
		entergameY = 400;
		
		//初始化任务栏信息
		MissionX = 870;
		MissionY = 100;
		MissionIndex = 1;
		MissionImages = new Image[7];
		for(int i = 1; i <= MissionImages.length - 1; i++){
			try {
				MissionImages[i] = ImageIO.read(new File("mission/mission" + i + ".gif"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}	
	
	//绘画方法
	public void paint(Graphics g){
		//g-->理解成一支画笔
		if(MapId == 1){
			ljcX = (this.getWidth() - lxyImage.getWidth(null)) / 2 - lxyX;
			ljcY = (this.getHeight() - lxyImage.getHeight(null)) / 2 - lxyY;
			//若地图遇到边界，地图坐标不再发生变化
			if(ljcX >= 0){
				ljcX = 0;
			}else if(ljcX <= (this.getWidth() - ljcImage.getWidth(null))){
				ljcX = this.getWidth() - ljcImage.getWidth(null);
			}
			if(ljcY >= 0){
				ljcY =0;
			}else if(ljcY <= (this.getHeight() -ljcImage.getHeight(null))){
				ljcY = this.getHeight() -ljcImage.getHeight(null);
			}
			//前提准备：将素材文件夹复制到工程下
			g.drawImage(ljcImage, ljcX, ljcY, this);//李家村
			g.drawImage(awsImages[awsIndex], awsX + ljcX, awsY + ljcY, this);//阿旺婶
			g.drawImage(azImages[azIndex], azX + ljcX, azY + ljcY, this);//阿朱
			g.drawImage(wcsImages[wcsIndex], wcsX + ljcX, wcsY + ljcY, this);//旺财嫂
			g.drawImage(xhImages[xhIndex], xhX + ljcX, xhY + ljcY, this);//小孩
			if(isinbag == 1){
				g.drawImage(mjImages[mjIndex], mjX + ljcX, mjY + ljcY, this);//母鸡
			}else if(isinbag == 2){
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//提示母鸡已放入背包
				//此效果在KeyType类中实现
				isinbag = 3;
			}
			g.drawImage(xjImages[xjIndex], xjX + ljcX, xjY + ljcY, this);//小鸡
			g.drawImage(xxjImages[xxjIndex], xxjX + ljcX, xxjY + ljcY, this);//小小鸡
			g.drawImage(lxyImage, lxyX + ljcX, lxyY + ljcY, this);//李逍遥
			g.drawImage(LevelImages[LevelIndex], lxyX + ljcX, lxyY - 35 + ljcY, this);//等级
			g.drawImage(MissionImages[MissionIndex], MissionX, MissionY, this);//任务栏
			
			//刚进入李家村 系统文字提示
			if(isstart){
				isTalk =true;
				dialogX = (this.getWidth() - dialogImage.getWidth(null)) / 2;
				dialogY = this.getHeight() - dialogImage.getHeight(null);
				g.drawImage(dialogImage, dialogX, dialogY, this);
				g.setColor(Color.white);
				g.setFont(new Font("楷体",Font.BOLD,25));
				if(MessageId == 1){
					g.drawString(welcomeMessages[welcomeMessagesIndex], dialogX + 50, dialogY + 50);
				}
				//isstart = false;
			}
			
			if(isTalk){
				dialogX = (this.getWidth() - dialogImage.getWidth(null)) / 2;
				dialogY = this.getHeight() - dialogImage.getHeight(null);
				g.drawImage(dialogImage, dialogX, dialogY, this);
				g.setColor(Color.white);
				g.setFont(new Font("微软雅黑",Font.BOLD,25));
				if(MessageId == 0){
					g.drawString(WrongMessages[WrongIndex], dialogX + 50, dialogY + 50);
				}else if(MessageId == 2){
					g.drawString(awsMessages[awsMessagesIndex], dialogX + 50, dialogY + 50);
				}else if(MessageId == 3){
					g.drawString(wcsMessages[wcsMessagesIndex], dialogX + 50, dialogY + 50);
				}else if(MessageId == 4){
					g.drawString(xhMessages[xhMessagesIndex], dialogX + 50, dialogY + 50);
				}else if(MessageId == 5){
					g.drawString(azMessages[azMessagesIndex], dialogX + 50, dialogY + 50);
				}else if(MessageId == 6){
					g.drawString(xjMessages[xjMessagesIndex], dialogX + 50, dialogY + 50);
				}else if(MessageId == 7){
					g.drawString(scwcsMessages[scwcsMessagesIndex], dialogX + 50, dialogY + 50);
				}
			}else{
				//升级系统
				if(isup){
					if(MessageId == 2){
						LevelIndex = 2;
						MissionIndex = 2;
					}else if(MessageId == 3){
						LevelIndex = 3;
						MissionIndex = 3;
					}else if(MessageId == 4){
						LevelIndex = 4;	
						MissionIndex = 4;
					}else if(MessageId == 5){
						LevelIndex = 5;
						MissionIndex = 5;
					}
					isup =  false;
				}
			}
		}else if(MapId ==2){
			ljcX = (this.getWidth() - lxyImage.getWidth(null)) / 2 - lxyX;
			ljcY = (this.getHeight() - lxyImage.getHeight(null)) / 2 - lxyY;
			//若地图遇到边界，地图坐标不再发生变化
			if(ljcX >= 0){
				ljcX = 0;
			}else if(ljcX <= (this.getWidth() - scImages[scIndex].getWidth(null))){
				ljcX = this.getWidth() - scImages[scIndex].getWidth(null);
			}
			if(ljcY >= 0){
				ljcY =0;
			}else if(ljcY <= (this.getHeight() -scImages[scIndex].getHeight(null))){
				ljcY = this.getHeight() -scImages[scIndex].getHeight(null);
			}
			g.drawImage(scImages[scIndex], ljcX, ljcY, this);
			g.drawImage(wcsImages[wcsIndex], wcsX + ljcX, wcsY + ljcY, this);//旺财嫂
			g.drawImage(lxyImage, lxyX + ljcX, lxyY + ljcY, this);
			g.drawImage(LevelImages[LevelIndex], lxyX  + ljcX, lxyY - 35 + ljcY, this);//等级
			g.drawImage(MissionImages[MissionIndex], MissionX, MissionY, this);//任务栏
			
			if(isTalk){
				dialogX = (this.getWidth() - dialogImage.getWidth(null)) / 2;
				dialogY = this.getHeight() - dialogImage.getHeight(null);
				g.drawImage(dialogImage, dialogX, dialogY, this);
				g.setColor(Color.white);
				g.setFont(new Font("微软雅黑",Font.BOLD,25));
				if(MessageId == 7){
					g.drawString(scwcsMessages[scwcsMessagesIndex], dialogX + 50, dialogY + 50);
				}else if(MessageId == 0){
					g.drawString(WrongMessages[WrongIndex], dialogX + 50, dialogY + 50);
				}else if(MessageId == 8){
					g.drawString(backMessages[backIndex], dialogX + 50, dialogY + 50);
				}
			}else{
				if(isup){
					if(MessageId == 7){
							LevelIndex = 6;
							MissionIndex = 6;
						}
					isup =  false;
				}
			}
		}else if(MapId == 3){
			g.drawImage(WelcomeImage, WelcomeX, WelcomeY, this);			
			g.drawImage(Toolkit.getDefaultToolkit().
					getImage(GamePanel.class.getResource("schedule.gif")), 180, 480, this);
			g.drawImage(Toolkit.getDefaultToolkit().
					getImage(GamePanel.class.getResource("guide.gif")), GuideX, GuideY, this);
			g.drawImage(Toolkit.getDefaultToolkit().
					getImage(GamePanel.class.getResource("entergame.gif")), entergameX, entergameY, this);
		}else if(MapId == 4){
			g.drawImage(EndImage, EndX, EndY, this);
			g.drawImage(Toolkit.getDefaultToolkit().
					getImage(GamePanel.class.getResource("word.gif")), 350, 100, this);
			g.setColor(Color.WHITE);
			g.setFont(new Font("楷体",Font.BOLD,15));
			g.drawString("按下ESC重新开始游戏", 450, 490);
			g.drawImage(RestartImage, 500, 400, this);
		}
		//场景转换提示
		if(isback == 1){
			g.drawImage(Toolkit.getDefaultToolkit().
					getImage(GamePanel.class.getResource("entersc.gif")), 150, 420, this);
		}else if(isback == 2){
			g.drawImage(Toolkit.getDefaultToolkit().
					getImage(GamePanel.class.getResource("enterljc.gif")), 50, 420, this);
		}else if(isback == 3){
			g.drawImage(Toolkit.getDefaultToolkit().
					getImage(GamePanel.class.getResource("goout.gif")), 600, 400, this);
		}
	}
	//运动方法
	public void run(){
		//实现配角的运动效果
		//也就是需要不断的循环播放图片，也就是不断的循环修改图片数组对应的下标
		while(true){
			awsIndex++;
			if(awsIndex >= awsImages.length){
				awsIndex = 0;
			}
			azIndex++;
			if(azIndex >= azImages.length){
				azIndex = 0;
			}
			wcsIndex++;
			if(wcsIndex >= wcsImages.length){
				wcsIndex = 0;
			}
			xhIndex++;
			if(xhIndex >= xhImages.length){
				xhIndex = 0;
			}
			mjIndex++;
			if(mjIndex >= mjImages.length){
				mjIndex = 0;
			}
			xjIndex++;
			if(xjIndex >= xjImages.length){
				xjIndex = 0;
			}
			xxjIndex++;
			if(xxjIndex >= xxjImages.length){
				xxjIndex = 0;
			}
			scIndex++;
			if(scIndex >= scImages.length){
				scIndex = 0;
			}
			//停顿操作
			try {
				Thread.sleep(180);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//重画命令->也就是重新调用执行paint();方法
			repaint();
		}
	}
	
	//添加KeyListener接口中的方法
	@Override
	public void keyPressed(KeyEvent e) {
		//实现主角的运动效果，即根据玩家按下的键盘按键进行对应操作(移动或者原地不定)
		int key = e.getKeyCode();//e-->可以理解成事件源
		//esc键回到加载界面
		if(key == KeyEvent.VK_ESCAPE){
			MapId = 3;
			lxyX = 600 + 200;
			lxyY = 380 + 200;
			wcsX = 700 + 200;
			wcsY = 220 + 200;
			LevelIndex = 1;
			isstart = true;
			MessageId = 1;
			lxyImage = lxyDownImages[lxyIndex];
			repaint();
		}else if(key == KeyEvent.VK_ENTER){
			//场景切换
			if(MapId == 1){
				int x = lxyX + lxyImage.getWidth(null) / 2;
				int y = lxyY + lxyImage.getHeight(null) - 10;
				if(x > 180 && x < 220 && y > 780 && y < 840){
					MapId = 2;
					lxyX = 160;
					lxyY = 680;
					wcsX = 320;
					wcsY = 460;
					lxyImage = lxyRightImages[lxyIndex];
					isback = 0;
					repaint();
				}
			}else if(MapId == 2){
				int x = lxyX + lxyImage.getWidth(null) / 2;
				int y = lxyY + lxyImage.getHeight(null) - 10;
				if(x > 0 && x < 100 && y > 600 && y < 800){
					MapId = 1;
					lxyX = 250;
					lxyY = 760;
					wcsX = 700 + 200;
					wcsY = 220 + 200;
					lxyImage = lxyRightImages[lxyIndex];
					isback = 0;
					repaint();
				}
				else if(x >= 1320 && x <=1400 && y >= 440 && y <= 480){
					if(LevelIndex == 6){
						MapId = 4;//游戏结束界面
						isback = 0;
					}else{
						isTalk = true;
						MessageId = 8;
					}
					repaint();
				}
			}else if(MapId == 3){//欢迎界面
				MapId = 1;
				repaint();
			}
		}
		if(isTalk){
			//聊天内容的切换+聊天结束的判断
			if(key == KeyEvent.VK_SPACE){
				if(MessageId == 0){
					WrongIndex++;
					if(WrongIndex >= WrongMessages.length){
						WrongIndex = 0;
						isTalk =false;
					}
				}else if(MessageId == 1){
					welcomeMessagesIndex++;
					if(welcomeMessagesIndex >= welcomeMessages.length){
						welcomeMessagesIndex = 0;
						isTalk = false;
						isup = true;
						isstart = false;
					}
				}else if(MessageId == 2){
					awsMessagesIndex++;
					if(awsMessagesIndex >= awsMessages.length){
						awsMessagesIndex = 0;
						isTalk = false;
						isup = true;
					}
				}else if(MessageId == 3){
					wcsMessagesIndex++;
					if(wcsMessagesIndex >= wcsMessages.length){
						wcsMessagesIndex = 0;
						isTalk =false;
						isup = true;
					}
				}else if(MessageId == 4){
					xhMessagesIndex++;
					if(xhMessagesIndex >= xhMessages.length){
						xhMessagesIndex = 0;
						isTalk =false;
						isup = true;
					}
				}else if(MessageId == 5){
					azMessagesIndex++;
					if(azMessagesIndex >= azMessages.length){
						azMessagesIndex = 0;
						isTalk =false;
						isup = true;
					}
				}else if(MessageId == 6){
					xjMessagesIndex++;
					if(xjMessagesIndex >= xjMessages.length){
						xjMessagesIndex = 0;
						isTalk = false;
						isinbag = 2;
					}
				}else if(MessageId == 7){
					scwcsMessagesIndex++;
					if(scwcsMessagesIndex >= scwcsMessages.length){
						scwcsMessagesIndex = 0;
						isTalk = false;
						isup = true;
					}
				}else if(MessageId == 8){
					backIndex++;
					if(backIndex >= backMessages.length){
						backIndex = 0;
						isTalk = false;
					}
				}
			}
		}else{
			//李逍遥的移动+聊天开始的判断+切换场景
			//上=38;下=40;左=37;右=39;W=87;S=83;A=65;D=68
			if(key == 38){	//上
				lxyY -= lxySpeed;
				lxyDir = KeyEvent.VK_UP;
				//lxy不能走出地图
				if(lxyY <= 0){
					lxyY = 0;
				}
				
				lxyIndex++;
				if(lxyIndex >= lxyUpImages.length){
					lxyIndex = 0;
				}
				lxyImage = lxyUpImages[lxyIndex];
				//判断是否遇到障碍物
				if(MapId == 1){
					int x = lxyX + lxyImage.getWidth(null) / 2;
					int y = lxyY + lxyImage.getHeight(null);
					if(ljcDataMap.getRGB(x, y) == -521461){
						lxyY += lxySpeed;
						//碰撞后原地不动
						lxyIndex--;
						if(lxyIndex <= -1){
							lxyIndex = 7;
						}
						lxyImage = lxyUpImages[lxyIndex];
					}
					//移动到指定区域显示按下回车进行场景转换
					int xback = lxyX + lxyImage.getWidth(null) / 2;
					int yback = lxyY + lxyImage.getHeight(null) - 10;
					if(xback > 180 && xback < 220 && yback > 780 && yback < 840){
						isback = 1;
						repaint();
					}else{
						isback = 0;
						repaint();
					}
				}else if(MapId ==2){
					int x = lxyX + lxyImage.getWidth(null) / 2;
					int y = lxyY + lxyImage.getHeight(null);
					if(scDataMap.getRGB(x, y) == -65536){
						lxyY += lxySpeed;
						//碰撞后原地不动
						lxyIndex--;
						if(lxyIndex <= -1){
							lxyIndex = 7;
						}
						lxyImage = lxyUpImages[lxyIndex];
					}
					int xback = lxyX + lxyImage.getWidth(null) / 2;
					int yback = lxyY + lxyImage.getHeight(null) - 10;
					if(xback > 0 && xback < 100 && yback > 600 && yback < 800){
						isback = 2;
						repaint();
					}else if(xback >= 1320 && xback <=1400 && yback >= 440 && yback <= 480){
						isback = 3;
						repaint();
					}else{
						isback = 0;
						repaint();
					}
				}
				repaint();//重画命令
			}else if(key == 40){	//下
				lxyY += lxySpeed;
				lxyDir = KeyEvent.VK_DOWN;
				
				lxyIndex++;
				if(lxyIndex >= lxyDownImages.length){
					lxyIndex = 0;
				}
				lxyImage = lxyDownImages[lxyIndex];
				//判断是否遇到障碍物
				if(MapId == 1){
					//lxy不能走出地图
					if(lxyY >= ljcImage.getHeight(null) - lxyImage.getHeight(null)){
						lxyY = ljcImage.getHeight(null) - lxyImage.getHeight(null);
					}
					
					int x = lxyX + lxyImage.getWidth(null) / 2;
					int y = lxyY + lxyImage.getHeight(null);
					if(ljcDataMap.getRGB(x, y) == -521461){
						lxyY -= lxySpeed;
						//碰撞后原地不动
						lxyIndex--;
						if(lxyIndex <= -1){
							lxyIndex = 7;
						}
						lxyImage = lxyDownImages[lxyIndex];
					}
					//移动到指定区域显示按下回车进行场景转换
					int xback = lxyX + lxyImage.getWidth(null) / 2;
					int yback = lxyY + lxyImage.getHeight(null) - 10;
					if(xback > 180 && xback < 220 && yback > 780 && yback < 840){
						isback = 1;
						repaint();
					}else{
						isback = 0;
						repaint();
					}
				}else if(MapId ==2){
					if(lxyY >= scImages[scIndex].getHeight(null) - lxyImage.getHeight(null)){
						lxyY = scImages[scIndex].getHeight(null) - lxyImage.getHeight(null);
					}
					int x = lxyX + lxyImage.getWidth(null) / 2;
					int y = lxyY + lxyImage.getHeight(null);
					if(scDataMap.getRGB(x, y) == -65536){
						lxyY -= lxySpeed;
						//碰撞后原地不动
						lxyIndex--;
						if(lxyIndex <= -1){
							lxyIndex = 7;
						}
						lxyImage = lxyDownImages[lxyIndex];
					}
					int xback = lxyX + lxyImage.getWidth(null) / 2;
					int yback = lxyY + lxyImage.getHeight(null) - 10;
					if(xback > 0 && xback < 100 && yback > 600 && yback < 800){
						isback = 2;
						repaint();
					}else if(xback >= 1320 && xback <=1400 && yback >= 440 && yback <= 480){
						isback = 3;
						repaint();
					}else{
						isback = 0;
						repaint();
					}
				}
				repaint();
			}else if(key == 37){	//左
				lxyX -= lxySpeed;
				lxyDir = KeyEvent.VK_LEFT;
				//lxy不能走出地图
				if(lxyX <= 0){
					lxyX = 0;
				}
				
				lxyIndex++;
				if(lxyIndex >= lxyLeftImages.length){
					lxyIndex = 0;
				}
				lxyImage = lxyLeftImages[lxyIndex];
				//判断是否遇到障碍物
				if(MapId == 1){
					int x = lxyX;
					int y = lxyY + lxyImage.getHeight(null) - 20;
					if(ljcDataMap.getRGB(x, y) == -521461){
						lxyX += lxySpeed;
						lxyIndex--;
						if(lxyIndex <= -1){
							lxyIndex =7;
						}
						lxyImage = lxyLeftImages[lxyIndex];
					}
					//移动到指定区域显示按下回车进行场景转换
					int xback = lxyX + lxyImage.getWidth(null) / 2;
					int yback = lxyY + lxyImage.getHeight(null) - 10;
					if(xback > 180 && xback < 220 && yback > 780 && yback < 840){
						isback = 1;
						repaint();
					}else{
						isback = 0;
						repaint();
					}
				}else if(MapId ==2){
					int x = lxyX;
					int y = lxyY + lxyImage.getHeight(null) - 20;
					if(scDataMap.getRGB(x, y) == -65536){
						lxyX += lxySpeed;
						lxyIndex--;
						if(lxyIndex <= -1){
							lxyIndex =7;
						}
						lxyImage = lxyLeftImages[lxyIndex];
					}
					int xback = lxyX + lxyImage.getWidth(null) / 2;
					int yback = lxyY + lxyImage.getHeight(null) - 10;
					if(xback > 0 && xback < 100 && yback > 600 && yback < 800){
						isback = 2;
						repaint();
					}else if(xback >= 1320 && xback <=1400 && yback >= 440 && yback <= 480){
						isback = 3;
						repaint();
					}else{
						isback = 0;
						repaint();
					}
				}
				repaint();
			}else if(key == 39){	//右
				lxyX += lxySpeed;
				lxyDir = KeyEvent.VK_RIGHT;
				
				lxyIndex++;
				if(lxyIndex >= lxyRightImages.length){
					lxyIndex = 0;
				}
				lxyImage = lxyRightImages[lxyIndex];
				//判断是否遇到障碍物
				if(MapId == 1){
					//lxy不能走出地图
					if(lxyX >= (ljcImage.getWidth(null) - lxyImage.getWidth(null))){
						lxyX = ljcImage.getWidth(null) - lxyImage.getWidth(null);
					}
					
					int x = lxyX + lxyImage.getWidth(null);
					int y = lxyY + lxyImage.getHeight(null) - 20;
					if(ljcDataMap.getRGB(x, y) == -521461){
						lxyX -= lxySpeed;
						lxyIndex--;
						if(lxyIndex <= -1){
							lxyIndex =7;
						}
						lxyImage = lxyRightImages[lxyIndex];
					}
					//移动到指定区域显示按下回车进行场景转换
					int xback = lxyX + lxyImage.getWidth(null) / 2;
					int yback = lxyY + lxyImage.getHeight(null) - 10;
					if(xback > 180 && xback < 220 && yback > 780 && yback < 840){
						isback = 1;
						repaint();
					}else{
						isback = 0;
						repaint();
					}
				}else if(MapId ==2){
					if(lxyX >= (scImages[scIndex].getWidth(null) - lxyImage.getWidth(null))){
						lxyX = scImages[scIndex].getWidth(null) - lxyImage.getWidth(null);
					}
					int x = lxyX + lxyImage.getWidth(null);
					int y = lxyY + lxyImage.getHeight(null) - 20;
					if(scDataMap.getRGB(x, y) == -65536){
						lxyX -= lxySpeed;
						lxyIndex--;
						if(lxyIndex <= -1){
							lxyIndex =7;
						}
						lxyImage = lxyRightImages[lxyIndex];
					}
					int xback = lxyX + lxyImage.getWidth(null) / 2;
					int yback = lxyY + lxyImage.getHeight(null) - 10;
					if(xback > 0 && xback < 100 && yback > 600 && yback < 800){
						isback = 2;
						repaint();
					}else if(xback >= 1320 && xback <=1400 && yback >= 440 && yback <= 480){
						isback = 3;
						repaint();
					}else{
						isback = 0;
						repaint();
					}
				}	
				repaint();
			}else if(key == KeyEvent.VK_SPACE){
				 if(MapId == 1){
					 	//与aws对话位置信息
						int x1 = lxyX;
						int y1 = lxyY + lxyImage.getHeight(null);
						int xaws = awsX + awsImages[awsIndex].getWidth(null);
						int yaws = awsY + awsImages[awsIndex].getHeight(null) + 10;
						int xaws1 = xaws - 20;
						int yaws1 = yaws - 20;
						
						//与wcs对话位置信息
						int xwcs = wcsX;
						int ywcs = wcsY + wcsImages[wcsIndex].getHeight(null);
						int xwcs1 = xwcs - 20;
						int ywcs1 = ywcs - 20;
						
						//与xh对话位置信息
						int x2 = lxyX + lxyImage.getWidth(null);
						int y2 = lxyY + lxyImage.getHeight(null);
						int xxh = xhX + 20;
						int yxh = xhY + xhImages[xhIndex].getHeight(null) + 20;
						int xxh1 = xxh + xhImages[xhIndex].getWidth(null) / 2 - 20;
						int yxh1 = yxh - xhImages[xhIndex].getHeight(null) / 3;
						
						//与az对话位置信息
						int xaz = azX + 10;
						int yaz = azY + azImages[azIndex].getHeight(null) + 20;
						int xaz1 = xaz + 20;
						int yaz1 = yaz - 30;
						
						//与ji对话位置信息
						int x3 = lxyX + lxyImage.getWidth(null);
						int y3 = lxyY + lxyImage.getHeight(null);
						int xj = xjX - lxyImage.getWidth(null) / 3;
						int yj = xxjY;
						int xj1 = xj + lxyImage.getWidth(null) / 2;
						int yj1 = yj + lxyImage.getHeight(null) / 3;
						
						if(lxyDir == KeyEvent.VK_LEFT && x1 >= xaws1 && x1 <= xaws 
								&& y1 >= yaws1 && y1 <= yaws){
							isTalk = true;
							if(LevelIndex == 1){
								MessageId = 2;
							}else{
								MessageId = 0;
							}
						}else if(lxyDir == KeyEvent.VK_RIGHT && x2 >= xwcs1 && x2 <= xwcs 
								&& y2 >= ywcs1 && y2 <= ywcs){
							isTalk = true;
							if(LevelIndex == 2){
								MessageId = 3;
							}else{
								MessageId = 0;
							}
						}else if(lxyDir == KeyEvent.VK_RIGHT && x2 >= xxh && x2 <= xxh1 
								&& y2 <= yxh && y2 >= yxh1){
							isTalk = true;
							if(LevelIndex == 3){
								MessageId = 4;
							}else{
								MessageId = 0;
							}
						}else if(lxyDir == KeyEvent.VK_RIGHT && x2 >= xaz && x2 <= xaz1 
								&& y2<= yaz && y2 >= yaz1){
							isTalk = true;
							if(LevelIndex == 4){
								MessageId = 5;
							}else{
								MessageId = 0;
							}
						}
						else if(lxyDir == KeyEvent.VK_RIGHT && isinbag != 3 && x3 >= xj
								&& x3 <= xj1 && y3 >= yj && y3 <= yj1){
							isTalk = true;
							if(LevelIndex == 5){
								MessageId = 6;
							}else{
								MessageId = 0;
							}
						}
				 }else if(MapId == 2){
					 //与市场中的旺财嫂对话位置信息
					 int x = lxyX + lxyImage.getWidth(null);
					 int y = lxyY + lxyImage.getHeight(null);
					 int xwcs = wcsX + 20;
					 int ywcs = wcsY + wcsImages[wcsIndex].getHeight(null) + 20;
					 int xwcs1 = xwcs - 30;
					 int ywcs1 = ywcs - 30;
					 if(lxyDir == KeyEvent.VK_RIGHT && x >= xwcs1 
							 && x <= xwcs && y >= ywcs1 && y <= ywcs){
						 if(LevelIndex == 5){
							MessageId = 7;
						 }else{
							MessageId = 0;
						}
						 isTalk = true;
					 }
				 }
				repaint();
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}
	@Override
	public void keyTyped(KeyEvent e) {
		if(isinbag == 2){
			Object[] options = {"确定"}; 
			JOptionPane.showOptionDialog(null, "母鸡已放入背包", "提示", 
			JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]); 
		}
	}
}