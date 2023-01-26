package org.weatherforecast;

public class NoWeatherDataException extends RuntimeException{
    public NoWeatherDataException() {
        super();
    }
    public NoWeatherDataException(String messgae) {
        super(messgae);
    }
}
