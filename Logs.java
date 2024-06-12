public class Logs {
    private static int logNumber = 0;

    public static String getLog() {
        return log;
    }

    private static String log = "";

    public static void Comment() {
        if(log.equals("")){
            return;
        }
        System.out.println(log);
    }

    public static void AddLogToRead(String text) {
        if(logNumber < 30){
            log += text + "\n";
            logNumber++;
        }
    }

    public static void ClearLogs() {
        log="";
        logNumber=0;
    }
}
