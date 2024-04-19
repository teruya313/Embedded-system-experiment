
/**
 * クラス PreKadai2_ServoXandLaser の注釈をここに書きます.
 * 
 * @author (あなたの名前)
 * @version (バージョン番号もしくは日付)
 */

//import hardware.*; //実験で実機を使用する場合にはコメントを外してこれを使用する
import stub.*; //予習などで実機を使用せずに動作を確認する場合には、これを使用する
public class PreKadai3_DrawManual
{

    private Hardware hardware = Hardware.getInstance(); //ハードウェアのインスタンスを取得する
    public void drawManual() {
        while ( true ) { //無限に繰り返す
            int potXvalue = hardware.manipulator.potX.getValue(); //ポテンショメーターXの値を取得す
            System.out.print( "PotX = " + potXvalue ); //potXの値を表示する
            System.out.print( "            " ); //表示での適度なスペースをとる
            hardware.drawUnit.servoX.setValue( potXvalue ); //サーボモーターXに値をセットする
            int potYvalue = hardware.manipulator.potY.getValue(); //ポテンショメーターYの値を取得す
            System.out.print( "PotY = " + potYvalue ); //potXの値を表示する
            System.out.print( "            " ); //表示での適度なスペースをとる
            hardware.drawUnit.servoY.setValue( potYvalue ); //サーボモーターYに値をセットする
             if ( hardware.manipulator.sw1.isPush() &&  hardware.manipulator.sw2.isPush() ) { 
                System.out.print( "" ); 
                System.exit( 0 );
            }else if ( hardware.manipulator.sw1.isPush() ) { //sw1が押されているか調べる
                System.out.print( "SW1 = " );
                System.out.print( "Pushed" ); //押されていたら「Pushed」と表示する
                System.out.print( "            " ); //表示での適度なスペースをとる
                hardware.drawUnit.laser.on(); //レーザーをonにする
            }else if ( hardware.manipulator.sw2.isPush() ){
                System.out.print( "SW2 = " );
                System.out.print( "Pushed" ); //押されていたら「Pushed」と表示する
                System.out.print( "            " ); //表示での適度なスペースをとる
                hardware.drawUnit.laser.off(); //レーザーをoffにする
            }

            System.out.println(); //改行する
            wait_ms( 10 ); //10ms 待つ（サーボモータの仕様上必要
        }
    }

    private void wait_ms( int waitTime_ms ) { // ms単位で指定するウェイト
        try{
            Thread.sleep( waitTime_ms ); 
        }catch ( Exception e ) {
            // nothing to do （この課題の用途では処理することがない）
        }
    }
}

