package br.rigolao.desafio_4_etapa_backend.utils;

public abstract class LogUtil<T> {

    public void logInfo(T object) {
        System.out.println("[INFO] " + object);
    }

}
