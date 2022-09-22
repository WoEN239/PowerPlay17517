package org.firstinspires.ftc.teamcode.functions;

public interface Standart {
    void start();
    default void activity(){}
    default boolean finish(){ return false; };
}
