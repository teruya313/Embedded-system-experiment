
/**
 * クラス Kadai5_FigureTraceTool の注釈をここに書きます.
 * 
 * @author (あなたの名前)
 * @version (バージョン番号もしくは日付)
 */
//import hardware.*; //実験で実機を使用する場合にはコメントを外してこれを使用する
 import stub.*; //予習などで実機を使用せずに動作を確認する場合には、これを使用する

public class Kadai5_FigureTraceTool
{
    private Hardware hardware = Hardware.getInstance(); //ハードウェアのインスタンスを取得する
    private int[] xB = { 0, 0, 100, 100, 0, 100, 0, 100};
    private int[] yB = { 0, 100,100, 0, 0, 100,100, 0};

    public void figureTraceTool(){
        hardware.drawUnit.servoX.setMinMax(68 , 202); //X座標の最小値と最大値を入れる
        hardware.drawUnit.servoY.setMinMax(57 , 177); //Y座標の最小値と最大値を入れる
        
        int potXvalue = hardware.manipulator.potX.getValue(); //ポテンショメーターXの値を取得
        System.out.print("PotX = " + potXvalue ); //potXの値を表示する
        hardware.drawUnit.servoX.setValue( potXvalue ); //サーボモーターXに値をセットする
        System.out.print("            " ); //表示での適度なスペースをとる
        int potYvalue = hardware.manipulator.potY.getValue(); //ポテンショメーターYの値を取得
        System.out.print( "PotY = " + potYvalue ); //potXの値を表示する
        hardware.drawUnit.servoY.setValue( potYvalue ); //サーボモーターYに値をセットする
        System.out.print( "            " ); //表示での適度なスペースをとる
        System.out.println(); //改行する
        if ( hardware.manipulator.sw1.isPush() )
        { //sw1が押されているか調べる
            System.out.print( "Pushed" ); //押されていたら「Pushed」と表示する
            hardware.drawUnit.laser.on(); //レーザーをonにする
        }else {
            System.out.print( "Released" ); //押されてなければ「Released」と表示する
            hardware.drawUnit.laser.off(); //レーザーをoffにする
        }
    }
}
