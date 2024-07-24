import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
//▼今回新しく利用するデータ構造は「java.util」パッケージに収められています
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.sound.sampled.Clip;

//▼ActionListenerインターフェースをMainControllerクラスに実装
public class MainController implements ActionListener{
  //▼文字列を型のフィールド（定数）を用意し値を収める
  private final String CODE_ROCK = "グー";
  private final String CODE_SCISSORS = "チョキ";
  private final String CODE_PAPER = "パー";
  //▼MainFrameオブジェクトを収めるためのフィールドを用意
  private MainFrame mainFrame = new MainFrame();

  //▼コンストラクタ
  public MainController(){
    //▼グー、チョキ、パーボタンにリスナーを設定
    mainFrame.getButtonRock().addActionListener(this);
    mainFrame.getButtonScissors().addActionListener(this);
    mainFrame.getButtonPaper().addActionListener(this);
  }

  //▼勝ちパターンを文字列リストとして定義しておく「自分の手:CPUの手」
  private List<String> victoryList;
  {
    this.victoryList = new ArrayList<String>();
    //▼victoryList[0]に「グー:チョキ」を収める
    this.victoryList.add(this.CODE_ROCK + ":" + this.CODE_SCISSORS);
    //▼victoryList[1]に「チョキ:パー」を収める
    this.victoryList.add(this.CODE_SCISSORS + ":" + this.CODE_PAPER);
    //▼victoryList[2]に「パー:グー」を収める
    this.victoryList.add(this.CODE_PAPER + ":" + this.CODE_ROCK);
  }

  //▼押されたボタンオブジェクトとじゃんけんの手（文字列）のマッピング　rpsMapを定義
  private Map<Object, String> rpsMap;
  {
    //▼オブジェクトの等価で判断したいので、IdentityHashMapを使う（詳細は資料参照）
    //▼たとえば、グーボタンがクリックされたら文字列"グー"に対応ということ
    this.rpsMap = new IdentityHashMap<Object, String>();
    this.rpsMap.put(this.mainFrame.getButtonRock(),this.CODE_ROCK);
    this.rpsMap.put(this.mainFrame.getButtonScissors(),this.CODE_SCISSORS);
    this.rpsMap.put(this.mainFrame.getButtonPaper(),this.CODE_PAPER);
  }

  //▼アノテーションを使っているので記述ミスをしたらエラーを吐いてくれます
  @Override
  //▼じゃんけんボタン押下時のアクションリスナー
  public void actionPerformed(ActionEvent e) {
	  
	  
    //▼押したボタンから自分の手として値("グー" or "チョキ" or "パー")を取得
    String result = this.rpsMap.get(e.getSource());

    //▼コンピュータの選択をランダムに設定
    Random random = new Random();
    //▼文字列型配列に"グー","チョキ","パー"を収め、CPUの手として
    //▼ランダムに"グー"もしくは"チョキ"もしくは"パー"をcpuResultに代入
    String cpuResult = new String[]{this.CODE_ROCK,this.CODE_SCISSORS,this.CODE_PAPER}[random.nextInt(3)];

    //▼コンピュータ側の手がグーの時、画像を表示のため呼び出す（MainFrameにあるメソッド）
    if(cpuResult.equals(CODE_ROCK)){
		this.mainFrame.setimageRockPc();
    }
    //▼コンピュータ側の手がチョキの時、画像を表示のため呼び出す
    else if(cpuResult.equals(CODE_SCISSORS)){
        this.mainFrame.setimageScissorsPc();
    }
    //▼コンピュータ側の手がパーの時、画像を表示のため呼び出す
    else if(cpuResult.equals(CODE_PAPER)){
        this.mainFrame.setimagePaperPc();
    }

    //▼自分の手がグーの時、画像表示のため呼び出す（MainFrameにあるメソッド）
    if(result.equals(CODE_ROCK)){
		this.mainFrame.setimageRockUser();
    }
    //▼自分の手がチョキの時、画像表示のため呼び出す
    else if(result.equals(CODE_SCISSORS)){
        this.mainFrame.setimageScissorsUser();
    }
    //▼自分の手がパーの時、画像表示のため呼び出す
    else if(result.equals(CODE_PAPER)){
        this.mainFrame.setimagePaperUser();
    }


    //▼あいこ時
    if(result.equals(cpuResult)){
    	this.mainFrame.setResult("　　　　　相手は" + cpuResult + "。あいこ。");
		//Clip clip = SoundPlayTest.createClip(new File("あいこでしょ.wav"));
		Clip clip = SoundPlayTest.createClip(new File("もう１回やる？.wav"));		clip.start();
		try {
			Thread.sleep(300);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
    //▼人間の勝ち
    //▼人間の勝ちパターンとして定義したvictoryListのいずれかの値である時
    }else if(this.victoryList.contains(result + ":" + cpuResult)){
	    this.mainFrame.setResult("　　　　　相手は" + cpuResult + "。あなたの勝ち。");
		Clip clip = SoundPlayTest.createClip(new File("あなたの勝ち.wav"));
		clip.start();
		try {
			Thread.sleep(300);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
      //▼その他は人間の負け
    }else{
    	this.mainFrame.setResult("　　　　　相手は" + cpuResult + "。あなたの負け。");
		Clip clip = SoundPlayTest.createClip(new File("あなたの負け.wav"));
		clip.start();
		try {
			Thread.sleep(300);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

    }
  }
}