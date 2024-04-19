
import hardware.*; //実験で実機を使用する場合にはコメントを外してこれを使用する
// import stub.*; //予習などで実機を使用せずに動作を確認する場合には、これを使用する

public class Kadai2_SetDrawArea
{
    private Hardware hardware = Hardware.getInstance(); //ハードウェアのインスタンスを取得すru
    public void setValueUnlimited(){
        hardware.manipulator.potX.addListener( () -> potXchanged() );
        hardware.manipulator.sw1.addListener( () -> sw1changed() );
        hardware.manipulator.potY.addListener( () -> potYchanged() );
        hardware.manipulator.sw2.addListener( () -> sw2changed() );
        while ( true ) { //無限に繰り返す
            // nothing to do （この課題の用途では処理することがない）
        }       
    }

    // ポテンショメータX変化時の処理メソッド
    private void potXchanged() {
        int potXvalue = hardware.manipulator.potX.getValue();
        //ポテンショメーターXの値を取得する
        System.out.println( "PotX = " + potXvalue ); //potXの値を表示する
        hardware.drawUnit.servoX.setValue( potXvalue ); //サーボモーターXに値をセットする
        System.out.print( "            " ); //表示での適度なスペースをとる
    }

    private void potYchanged() {
        int potYvalue = hardware.manipulator.potY.getValue();
        //ポテンショメーターXの値を取得する
        System.out.println( "PotY = " + potYvalue ); //potYの値を表示する
        hardware.drawUnit.servoY.setValue( potYvalue ); //サーボモーターXに値をセットする
        System.out.print( "            " ); //表示での適度なスペースをとる
    }

    private void sw1changed() {
        System.out.print( "SW1 = " );
        if ( hardware.manipulator.sw1.isPush() ) { //sw1が押されているか調べる
            System.out.println( "Pushed" ); //押されていたら「Pushed」と表示する
            hardware.drawUnit.laser.on(); 
        } else {
            System.out.println( "Released" ); //押されてなければ「Released」と表示する
            hardware.drawUnit.laser.off(); //レーザーをoffにする
        }
    }  

    private void sw2changed() {
        System.out.print( "SW2 = " );
        if ( hardware.manipulator.sw2.isPush() ) { //sw1が押されているか調べる
            System.out.print( "Pushed" ); 
            System.exit( 0 );
        } else {
            System.out.println( "Released" ); //押されてなければ「Released」と表示する
            hardware.drawUnit.laser.off(); //レーザーをoffにする
        }
    }      
}

