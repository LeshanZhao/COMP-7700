import java.util.List;

public interface WebDataAccess {

    public static final int CONNECTION_OPEN_OK = 100;
    public static final int CONNECTION_OPEN_FAILED = 101;

    public static final int CONNECTION_CLOSE_OK = 200;
    public static final int CONNECTION_CLOSE_FAILED = 201;

    public static final int PRODUCT_SAVE_OK = 0;
    public static final int PRODUCT_SAVE_FAILED = 1;

    public static final int PURCHASE_SAVE_OK = 500;
    public static final int PURCHASE_SAVE_FAILED = 501;

    int connect();
    public int connect(String database_URL);

    public int disconnect();



}
