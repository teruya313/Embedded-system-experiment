
import hardware.*; //実験で実機を使用する場合にはコメントを外してこれを使用する
//import stub.*; //予習などで実機を使用せずに動作を確認する場合には、これを使用する

public class Kadai6_DrawCircle1
{
    private Hardware hardware = Hardware.getInstance(); //ハードウェアのインスタンスを取得する

    public void drawFigure() {
        System.out.println( "Kadai 6 Start!" );
        hardware.manipulator.sw2.addListener( () -> systemExit() );

        hardware.drawUnit.servoX.setMinMax( 60, 215); //X座標の最小値と最大値を入れる
        hardware.drawUnit.servoY.setMinMax( 45, 205); //Y座標の最小値と最大値を入れる
        double r = 40; // 半径 r
        double x0 = 50; // 中心座標 x0
        double y0 = 50; // 中心座標 y0
        hardware.drawUnit.laser.on();

        while ( true ) {
            for ( int th=0 ; th<360 ; th += 3 ) { //中心角を3°間隔で360°まわす。
                double radian = Math.toRadians( th );
                double x = x0 + r * Math.cos( radian );
                double y = y0 + r * Math.sin( radian );
                hardware.drawUnit.servoX.setValue( convertX( (int)x ) );
                hardware.drawUnit.servoY.setValue( convertY( (int)y ) );
                UtilityTools.wait_ms( 10 );
            }
        }
    }

    private int convertX( int x ) {
        int xMin = hardware.drawUnit.servoX.getMin();
        int xMax = hardware.drawUnit.servoX.getMax();
        return ( x+73); //この部分に変換式を記述する
    }

    private int convertY( int y ) {
        int yMin = hardware.drawUnit.servoY.getMin();
        int yMax = hardware.drawUnit.servoY.getMax();
        return (y+70); //この部分に変換式を記述する
    }

    private void systemExit() {
        hardware.reset();
        System.out.println( "Kadai 6 End." );
        System.exit( 0 );
    }
}
