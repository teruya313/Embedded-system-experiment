/** 
 * 描画領域の限界値の確認 * 
 * @author (学籍番号 氏名)
 * @version (プログラムを作成した日付)
 */
import hardware.*; //実験で実機を使用する場合にはコメントを外してこれを使用する
// import stub.*; //予習などで実機を使用せずに動作を確認する場合には、これを使用する

public class Kadai_5b

{
    private Hardware hardware = Hardware.getInstance(); //ハードウェアのインスタンスを取得する
    private int[] xA = new int[8]; //getMin(),getMax()での座標データ配列
    private int[] yA = new int[8]; //getMin(),getMax()での座標データ配列
    // 左下 左上 右上 右下 左下 右上 左上 右下
    private int[] xB = { 0, 0, 0, 0, 0, 0,0,0};
    private int[] yB = { 0, 0, 0, 0, 0,0,0,0};
    private int[] drawX; //描画用データ配列(X座標)
    private int[] drawY; //描画用データ配列(Y座標)
    public void checkDrawArea() {
        hardware.manipulator.sw1.addListener( () -> selectDataA() );
        hardware.manipulator.sw2.addListener( () -> selectDataB() );
        hardware.drawUnit.servoX.setMinMax( 60, 215); //X座標の最小値と最大値を入れる
        hardware.drawUnit.servoY.setMinMax( 45, 205); //Y座標の最小値と最大値を入れる

        int xMin = hardware.drawUnit.servoX.getMin(); //X座標の最小値を取得する
        int xMax = hardware.drawUnit.servoX.getMax(); //X座標の最大値を取得する
        int yMin = hardware.drawUnit.servoY.getMin(); //Y座標の最小値を取得する
        int yMax = hardware.drawUnit.servoY.getMax(); //Y座標の最大値を取得する

        xA[0] = 75; //左下 //座標データの格納
        yA[0] = 114;
        xA[1] = 85; //左上
        yA[1] = 160;
        xA[2] = 128; //右上
        yA[2] = 172;
        xA[3] = 165; //右下
        yA[3] = 160;
        xA[4] = 185; //左下
        yA[4] = 120;
        xA[5] = 165; //右上
        yA[5] = 80; 
        xA[6] = 128; //右上
        yA[6] = 69; 
        xA[7] = 90; //右上
        yA[7] = 82; 
        
       
        selectDataA(); // 描画するデータにxA[],yA[]を選択する
        hardware.drawUnit.laser.on();
        while ( true ) { for ( int i=0 ; i<drawX.length ; i++ ) {
                hardware.drawUnit.servoX.setValue( drawX[i] );
                hardware.drawUnit.servoY.setValue( drawY[i] );
                UtilityTools.wait_ms( 200 ); //サーボモーターの移動を考慮したウェイト
            }
        }
    }

    private void selectDataA() {
        drawX = xA;
        drawY = yA;
        hardware.raspIfBoard.setLed( 0x08 );
        for ( int i=0 ; i<drawX.length ; i++ ) { 
            System.out.printf( "(%3d,%3d)" , drawX[i] , drawY[i] );
        }
        System.out.println();
    }
    
     private void selectDataB() {
        drawX = xB;
        drawY = yB;
        hardware.raspIfBoard.setLed( 0x01 );
        for ( int i=0 ; i<drawX.length ; i++ ) {
            System.out.printf( "(%3d,%3d)" , drawX[i] , drawY[i] );
        }
       System.out.println();
     }   
}