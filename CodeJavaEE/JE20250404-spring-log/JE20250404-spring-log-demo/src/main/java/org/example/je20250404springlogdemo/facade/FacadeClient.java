package org.example.je20250404springlogdemo.facade;

/**
 * Created with IntelliJ IDEA.
 * Description: 一个遥控器控制所有的灯
 * User: 姚东名
 * Date: 2025-04-04
 * Time: 11:50
 */
public class FacadeClient implements Light {
    private LivingRoomLight livingRoomLight = new LivingRoomLight();
    private BedroomLight bedroomLight = new BedroomLight();
    private HallLight hallLight = new HallLight();

    @Override
    public void on() {
        livingRoomLight.on();
        bedroomLight.on();
        hallLight.on();
    }

    @Override
    public void off() {
        livingRoomLight.off();
        bedroomLight.off();
        hallLight.off();
    }
}
