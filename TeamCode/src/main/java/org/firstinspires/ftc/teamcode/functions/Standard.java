package org.firstinspires.ftc.teamcode.functions;

public interface Standard {
    void start();
    default void activity(){}
    default boolean finish(){ return false; };
}
