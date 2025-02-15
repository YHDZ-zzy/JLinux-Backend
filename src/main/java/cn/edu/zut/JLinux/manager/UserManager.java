package cn.edu.zut.JLinux.manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.edu.zut.JLinux.dao.User;

public class UserManager {
    private HashMap<Integer, User> userMap;
    public static String basePath = "\\etc\\passwd";
    private Logger logger = LoggerFactory.getLogger("UserLogger");
    static UserManager instance = new UserManager();

    public UserManager() {
        userMap = new HashMap<Integer, User>();
    }

    public static UserManager getInstance() {
        return instance;
    }

    public Boolean modifyUser(User user) {
        if (userMap.containsKey(user.getUid())) {
            userMap.put(user.getUid(), user);
            logger.info("modify user " + user.getUid() + " success");
            toFile(basePath);
            return true;
        }
        logger.info("modify user " + user.getUid() + " failed");
        toFile(basePath);
        return false;
    }

    public Boolean removeUser(User user) {
        if (userMap.containsKey(user.getUid())) {
            userMap.remove(user.getUid());
            logger.info("remove user " + user.getUid() + " success");
            toFile(basePath);
            return true;
        }
        logger.info("remove user " + user.getUid() + " failed");
        toFile(basePath);
        return false;
    }

    public Boolean addUser(int uid, User user) {
        userMap.put(uid, user);
        logger.info("add user " + user.getUid() + " success");
        toFile(basePath);
        return true;
    }

    public Boolean addUser(User user) {
        userMap.put(user.getUid(), user);
        logger.info("add user " + user.getUid() + " success");
        toFile(basePath);
        return true;
    }

    public User getUserName(int uid) {
        return userMap.get(uid);
    }

    public void removeUser(int uid) {
        userMap.remove(uid);
        logger.info("remove user " + uid + " success");
        toFile(basePath);
    }

    public boolean isUserExist(int uid) {
        return userMap.containsKey(uid);
    }

    public boolean isUserExist(User user) {
        return userMap.containsValue(user);
    }

    public int getUid(String userName) {
        for (User user : userMap.values()) {
            if (user.getUserName().equals(userName)) {
                return user.getUid();
            }
        }
        return -1;
    }

    public void clear() {
        userMap.clear();
    }

    public int getSize() {
        return userMap.size();
    }

    public boolean isEmpty() {
        return userMap.isEmpty();
    }

    public void print() {
        for (Integer uid : userMap.keySet()) {
            System.out.println(uid + " " + userMap.get(uid));
        }
    }

    public void fromFile(String fileName) {
        // username : password : uid : gid : gecos : homedir : shell

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] userInfo = line.split(":");
                User user = new User(userInfo[0], Integer.parseInt(userInfo[2]), Integer.parseInt(userInfo[3]),
                        userInfo[4], userInfo[5], userInfo[6]);
                userMap.put(Integer.parseInt(userInfo[2]), user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void toFile(String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Integer uid : userMap.keySet()) {
                bw.write(userMap.get(uid).toString());
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}