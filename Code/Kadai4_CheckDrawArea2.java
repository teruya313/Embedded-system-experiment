import hardware.*; //実験で実機を使用する場合にはコメントを外してこれを使用する
//import stub.*; //予習などで実機を使用せずに動作を確認する場合には、これを使用する

public class Kadai4_CheckDrawArea2
{
    private Hardware hardware = Hardware.getInstance(); //ハードウェアのインスタンスを取得する

    public void checkDrawArea() {
        System.out.println( "Kadai 4 Start!" );
        hardware.manipulator.sw2.addListener( () -> systemExit() );

        hardware.drawUnit.servoX.setMinMax(68 , 202); //X座標の最小値と最大値を入れる
        hardware.drawUnit.servoY.setMinMax(57 , 177); //Y座標の最小値と最大値を入れる

        while ( true ) {
            for ( int[] point : Figure.drawAreaCheck ) {
                hardware.drawUnit.servoX.setValue( convertX( point[0] ) );
                hardware.drawUnit.servoY.setValue( convertY( point[1] ) );
                if ( point[2] != 0 ) {
                    hardware.drawUnit.laser.on();
                } else {
                    hardware.drawUnit.laser.off();
                }
                System.out.println( "(" + point[0] + "," + point[1] + ")" );
                UtilityTools.wait_ms( 1000 );
            }
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
        System.out.println( "Kadai 4 End." );
        System.exit( 0 );
    }
}

