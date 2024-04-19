
import hardware.*; //実験で実機を使用する場合にはコメントを外してこれを使用する
//import stub.*; //予習などで実機を使用せずに動作を確認する場合には、これを使用する

public class Kadai7_DrawCircle2
{
    private Hardware hardware = Hardware.getInstance(); //ハードウェアのインスタンスを取得する

    public void drawFigure() {
        System.out.println( "Kadai 7 Start!" );
        hardware.manipulator.sw2.addListener( () -> systemExit() );

        hardware.drawUnit.servoX.setMinMax( 60, 215); //X座標の最小値と最大値を入れる
        hardware.drawUnit.servoY.setMinMax( 45, 205); //Y座標の最小値と最大値を入れる
        double r1 = 40; //外側の円の半径
        double r2 = 20; //内側の円の半径
        double x0 = 50; // 中心座標 x0
        double y0 = 50; // 中心座標 y0
        hardware.drawUnit.laser.on();
        while(true){
            for ( int th=0 ; th<360 ; th += 3 ) { //中心角を3°間隔で360°まわす。
                double radian = Math.toRadians( th );
                double x = x0 + r2 * Math.cos( radian );
                double y = y0 + r2 * Math.sin( radian );

                hardware.drawUnit.servoX.setValue( convertX( (int)x ) );
                hardware.drawUnit.servoY.setValue( convertY( (int)y ) );
                UtilityTools.wait_ms( 10 );
            }
            hardware.drawUnit.laser.off(); //レーザーをoffにする
            for ( int th=0 ; th<360 ; th += 3 ) { //中心角を3°間隔で360°まわす。
                double radian = Math.toRadians( th );
                double x = x0 + r1 * Math.cos( radian );
                double y = y0 + r1 * Math.sin( radian );
                hardware.drawUnit.servoX.setValue( convertX( (int)x ) );
                hardware.drawUnit.servoY.setValue( convertY( (int)y ) );
                UtilityTools.wait_ms( 10);
            }
            hardware.drawUnit.laser.on();
            for ( int th=0 ; th<360 ; th += 3 ) { //中心角を3°間隔で360°まわす。
                double radian = Math.toRadians( th );
                double x = x0 + r1 * Math.cos( radian );
                double y = y0 + r1 * Math.sin( radian );
                hardware.drawUnit.servoX.setValue( convertX( (int)x ) );
                hardware.drawUnit.servoY.setValue( convertY( (int)y ) );
                UtilityTools.wait_ms( 10);
            }
            hardware.drawUnit.laser.off(); //レーザーをoffにする
            for ( int th=0 ; th<360 ; th += 3 ) { //中心角を3°間隔で360°まわす。
                double radian = Math.toRadians( th );
                double x = x0 + r2 * Math.cos( radian );
                double y = y0 + r2 * Math.sin( radian );

                hardware.drawUnit.servoX.setValue( convertX( (int)x ) );
                hardware.drawUnit.servoY.setValue( convertY( (int)y ) );
                UtilityTools.wait_ms( 10 );
            }
            hardware.drawUnit.laser.on();
        }
    }

    private int convertX( int x ) {
        int xMin = hardware.drawUnit.servoX.getMin();
        int xMax = hardware.drawUnit.servoX.getMax();
        return(((xMax-xMin)/100)*x+xMin);
    }

    private int convertY( int y ) {
        int yMin = hardware.drawUnit.servoY.getMin();
        int yMax = hardware.drawUnit.servoY.getMax();
        return (((yMax-yMin)/100)*y+yMin);
    }

    private void systemExit() {
        System.out.println( "Kadai 7 End." );
        System.exit( 0 );
    }
}