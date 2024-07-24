import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundPlayTest {
	public static void main(String[] args) throws Exception{
		//▼再生したいwavファイルをクリップ型のオブジェクトとして生成
		Clip clip = createClip(new File("グー.wav"));
		//▼作ったクリップを再生
		clip.start();
		//▼プログラムを一時停止（終了させない）することにより再生音を最後まで聞かせる
		Thread.sleep(1000);
	}

	//▼Clip型のオブジェクトを返すcreateClipメソッドを定義
	public static Clip createClip(File path) {
		//▼指定されたURLのオーディオ入力ストリームを取得
		//▼例外が発生する処理のためtry～catch構文で対応する
		try (AudioInputStream ais = AudioSystem.getAudioInputStream(path)){

			//▼ファイルの形式取得
			AudioFormat af = ais.getFormat();

			//▼単一のオーディオ形式を含む指定した情報からデータラインの情報オブジェクトを構築
			DataLine.Info dataLine = new DataLine.Info(Clip.class,af);

			//▼指定された Line.Info オブジェクトの記述に一致するライン（各種デジタルオーディオ入出力の１要素）を取得
			Clip c = (Clip)AudioSystem.getLine(dataLine);

			//▼再生準備完了
			c.open(ais);

			return c;
		//▼不正な形式のURLが見つかった
		} catch (MalformedURLException e) {
			//▼例外の発生状況と発生箇所を出力
			e.printStackTrace();
		//▼認識されたファイル・タイプとファイル形式の有効データをそのファイルが含んでいない
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		//▼入出力の例外
		} catch (IOException e) {
			e.printStackTrace();
		//▼ラインが使用不可のため開けない
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		return null;
	}
}