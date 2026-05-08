package com.chingon.FoodStorageApp.shared.constants;

public final class Routes {
    private Routes() {}

    public static final String API = "/api/v1";

    public static final class Storage{
       public static final String BASE = "/storages";
       public static final String BY_ID = BASE + "/{storageId}";
    }

    public static final class Container{
        public static final String BASE = "/containers";
        public static final String BY_ID = BASE + "/{containerId}";
        public static final String MOVE = "/move/{targetStorageId}";
    }
}
