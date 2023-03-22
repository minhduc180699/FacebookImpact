package com.saltlux.documentconverter.utils;

//import org.elasticsearch.client.RestClient;

public class AGlobalConf {
    public final static String KAFKA_BROKER_LIST = "35.239.93.122:9094,34.134.222.24:9094";
    public static String TOPIC_CONTENT_NORMAL_ENG = "go_realtime_crawler_en";
    public static String TOPIC_CONTENT_NORMAL_KOR = "go_realtime_crawler_ko";
    public static String TOPIC_CONTENT_RECOMMEND_ENG = "go_realtime_crawler_recommend_en";
    public static String TOPIC_CONTENT_RECOMMEND_KOR = "go_realtime_crawler_recommend_ko";
    public static String TOPIC_SUB_REQUEST = "go_realtime_crawler_sub_request";
    public static String TOPIC_REQUEST_STATISTIC = "go_realtime_crawler_request_statistic";
    public static String TOPIC_RECOMMEND_REQUEST_STATISTIC = "go_realtime_crawler_request_recommend_statistic";
    public static String TOPIC_CRAWLING_STATUS = "go_realtime_crawler_sub_request_crawling_statistic";
    public static String TOPIC_RECOMMEND_CRAWLING_STATUS = "go_realtime_crawler_sub_request_crawling_recommend_statistic";
    public final static String KAFKA_SYNC_UPLOAD_IMPORTED_ENG = "go_realtime_crawler_upload_en";
    public final static String KAFKA_SYNC_UPLOAD_IMPORTED_KOR = "go_realtime_crawler_upload_ko";
    public final static String FOLDER_PATH = "realtimedata";
    public final static String TEMP_FOLDER = "tempdata";
    public final static String DS_INDEX_PREFIX_GLOBAL = "ds-global-";
    public final static String DS_INDEX_PREFIX_PRIVATE = "ds-private-";
    public static int count = 0;
    public final static String[] REDIS_CLUSTER_HOST = {"my-release-redis-cluster-headless.redis.svc.cluster.local"};
    public final static String REDIS_CLUSTER_HOST_1 = "my-release-redis-cluster-headless.redis.svc.cluster.local";
    public final static int REDIS_CLUSTER_PORT = 6379;
    public final static char[] REDIS_PASSWORD = "6mHwLFOuPy".toCharArray();
    public final static String REDIS_PASSWORD_STRING = "6mHwLFOuPy";
    final public static String PREFIX_CACHE = "go_realtime_crawler:";
    public final static String REDIS_KEY_SELECTOR = PREFIX_CACHE + "key:";
    public static String driverPath = "./chromedriver";
    public static String browserPath = "/opt/google/chrome/chrome";
    final public static long REDIS_CACHE_DOC_EXPIRED_TIME = 60 * 60 * 1;
    public static String[] documentURLRequestList = {"id", "path", "type", "name", "createdDate", "lastModifiedDate", "connectomeId", "lang", "date", "searchType"};
}
