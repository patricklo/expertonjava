//package pratice.java.database.berkeleydb;
//
//import com.sleepycat.je.*;
//import org.apache.log4j.Logger;
//
//import java.io.File;
//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//
///**
// * Created by patrick on 12/17/14.
// */
//public class TryDb {
//    static Logger log = Logger.getLogger(TryDb.class.getName());
//    //数据库环境
//    private Environment myDbEnvironment = null;
//    //数据库配置
//    private DatabaseConfig dbConfig=null;
//    //数据库游标
//    //private  Cursor myCursor = null;
//    //数据库对象
//    private Database myDatabase = null;
//    //数据库文件名
//    private  String fileName = "";
//    //数据库名称
//    private  String dbName = "";
//
//    /*
//     * 打开当前数据库
//     */
//    public  void openDatabase() {
//        // TODO Auto-generated method stub
//        try{
//            log.info("打开数据库: " + dbName);
//            EnvironmentConfig envConfig = new EnvironmentConfig();
//            envConfig.setAllowCreate(true);
//            envConfig.setTransactional(true);
//            envConfig.setReadOnly(false);
//            envConfig.setTxnTimeout(10000);
//            envConfig.setLockTimeout(10000);
//            /*
//             *   其他配置 可以进行更改
//                EnvironmentMutableConfig envMutableConfig = new EnvironmentMutableConfig();
//                envMutableConfig.setCachePercent(50);//设置je的cache占用jvm 内存的百分比。
//                envMutableConfig.setCacheSize(123456);//设定缓存的大小为123456Bytes
//                envMutableConfig.setTxnNoSync(true);//设定事务提交时是否写更改的数据到磁盘，true不写磁盘。
//                //envMutableConfig.setTxnWriteNoSync(false);//设定事务在提交时，是否写缓冲的log到磁盘。如果写磁盘会影响性能，不写会影响事务的安全。随机应变。
//             *
//             */
//            File file = new File(fileName);
//            if(!file.exists())
//                file.mkdirs();
//            myDbEnvironment = new Environment(file,envConfig);
//
//            dbConfig = new DatabaseConfig();
//            dbConfig.setAllowCreate(true);
//            dbConfig.setTransactional(true);
//            dbConfig.setReadOnly(false);
//            //dbConfig.setSortedDuplicates(false);
//            /*
//                setBtreeComparator 设置用于B tree比较的比较器，通常是用来排序
//                setDuplicateComparator 设置用来比较一个key有两个不同值的时候的大小比较器。
//                setSortedDuplicates 设置一个key是否允许存储多个值，true代表允许，默认false.
//                setExclusiveCreate 以独占的方式打开，也就是说同一个时间只能有一实例打开这个database。
//                setReadOnly 以只读方式打开database,默认是false.
//                setTransactional 如果设置为true,则支持事务处理，默认是false，不支持事务。
//            */
//            if(myDatabase == null)
//                myDatabase = myDbEnvironment.openDatabase(null, dbName, dbConfig);
//
//            log.info(dbName + "数据库中的数据个数: " + myDatabase.count());
//            /*
//             *  Database.getDatabaseName()
//                取得数据库的名称
//                如：String dbName = myDatabase.getDatabaseName();
//
//                Database.getEnvironment()
//                取得包含这个database的环境信息
//                如：Environment theEnv = myDatabase.getEnvironment();
//
//                Database.preload()
//                预先加载指定bytes的数据到RAM中。
//                如：myDatabase.preload(1048576l); // 1024*1024
//
//                Environment.getDatabaseNames()
//                返回当前环境下的数据库列表
//                Environment.removeDatabase()
//                删除当前环境中指定的数据库。
//                如：
//                String dbName = myDatabase.getDatabaseName();
//                myDatabase.close();
//                myDbEnv.removeDatabase(null, dbName);
//
//                Environment.renameDatabase()
//                给当前环境下的数据库改名
//                如：
//                String oldName = myDatabase.getDatabaseName();
//                String newName = new String(oldName + ".new", "UTF-8");
//                myDatabase.close();
//                myDbEnv.renameDatabase(null, oldName, newName);
//
//                Environment.truncateDatabase()
//                清空database内的所有数据，返回清空了多少条记录。
//                如：
//                Int numDiscarded= myEnv.truncate(null,
//                myDatabase.getDatabaseName(),true);
//                log.info("一共删除了 " + numDiscarded +" 条记录 从数据库 " + myDatabase.getDatabaseName());
//             */
//        }
//        catch(DatabaseException e){
//            log.info(e.getMessage());
//
//        }
//    }
//
//    /*
//     * 关闭当前数据库
//     */
//    public  void closeDatabase() {
//        // TODO Auto-generated method stub
//        try{
//            if (myDatabase != null) {
//                myDatabase.close();
//            }
//            if (myDbEnvironment != null) {
//                log.info("关闭数据库: " + dbName);
//                myDbEnvironment.cleanLog();
//                myDbEnvironment.close();
//            }
//        }catch(DatabaseException e){
//            log.error("Error:\n"+e.getMessage());
//        }
//
//
//    }
//
//    /*
//     * 遍历数据库中的所有记录，返回list
//     */
//    public ArrayList<String> getEveryItem() {
//        // TODO Auto-generated method stub
//        log.info("===========遍历数据库" + dbName + "中的所有数据==========");
//        Cursor myCursor = null;
//        ArrayList<String> resultList = new ArrayList<String>();
//        Transaction txn = null;
//        try{
//            txn = this.myDbEnvironment.beginTransaction(null, null);
//            CursorConfig cc = new CursorConfig();
//            cc.setReadCommitted(true);
//            if(myCursor==null)
//                myCursor = myDatabase.openCursor(txn, cc);
//            DatabaseEntry foundKey = new DatabaseEntry();
//            DatabaseEntry foundData = new DatabaseEntry();
//            // 使用cursor.getPrev方法来遍历游标获取数据
//            if(myCursor.getFirst(foundKey, foundData, LockMode.DEFAULT)
//                    == OperationStatus.SUCCESS)
//            {
//                String theKey = new String(foundKey.getData(), "UTF-8");
//                String theData = new String(foundData.getData(), "UTF-8");
//                resultList.add(theKey);
//                log.info("Key | Data : " + theKey + " | " + theData + "");
//                while (myCursor.getNext(foundKey, foundData, LockMode.DEFAULT)
//                        == OperationStatus.SUCCESS)
//                {
//                    theKey = new String(foundKey.getData(), "UTF-8");
//                    theData = new String(foundData.getData(), "UTF-8");
//                    resultList.add(theKey);
//                    log.info("Key | Data : " + theKey + " | " + theData + "");
//                }
//            }
//            myCursor.close();
//            txn.commit();
//            return resultList;
//        }
//        catch (UnsupportedEncodingException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            return null;
//        }
//        catch (Exception e)
//        {
//            log.info("getEveryItem处理出现异常");
//            log.info(e.getMessage().toString());
//            log.info(e.getCause().toString());
//            try {
//                txn.abort();
//                if (myCursor != null) {
//                    myCursor.close();
//                }
//            }catch(DatabaseException databaseEx){
//                log.error(databaseEx.getMessage());
//            }
//            return null;
//        }
//
//
//    }
//
//    public static void main(String[] args){
//        TryDb tryDb = new TryDb();
//        tryDb.openDatabase();
//    }
//}
