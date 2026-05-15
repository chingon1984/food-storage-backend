package com.chingon.FoodStorageApp.shared.constants;

public final class Routes {
    public static final String API = "/api/v1";

    private Routes() {
    }

    public static final class Household {
        public static final String BASE = "/household";
        public static final String BY_ID = "/{publicId}";
    }

    public static final class Storage {
        public static final String BASE = "/storages";
        public static final String BY_ID = BASE + "/{storageId}";
    }

    public static final class Container {
        public static final String BASE = "/containers";
        public static final String BY_ID = BASE + "/{containerId}";
        public static final String MOVE = "/move/{targetStorageId}";
    }
}
