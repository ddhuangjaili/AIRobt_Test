package com.example.recognition.execute;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import com.alibaba.fastjson.util.IOUtils;
import com.example.recognition.model.OSShellCommand;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: RemoteExecuteCommand
 * @Description: 远程执行Linux命令
 */
public class RemoteExecuteCommand {
    private static String DEFAULTCHART = "UTF-8";
    private Connection conn;
    private String ip;
    private String userName;
    private String userPwd;

    public RemoteExecuteCommand(String ip, String userName, String userPwd){
        this.ip = ip;
        this.userName = userName;
        this.userPwd = userPwd;
    }

    public RemoteExecuteCommand(){}

    /**
     * 远程登录linux主机
     * @return
     * @throws Exception
     */
    public Boolean login() throws Exception {
        boolean flag = false;

        try {
            conn = new Connection(ip);
            //连接
            conn.connect();
            //认证
            flag = conn.authenticateWithPassword(userName, userPwd);
        } catch (IOException e) {
            throw new Exception("远程连接服务器失败",e);
        }

        return flag;
    }

    /**
     * 远程执行shell脚本或者命令
     * @param cmd
     * @return
     * @throws IOException
     */
    public String execute(String cmd) throws Exception {
        String result = "";
        Session session = null;

        try {
            if (login()){
                //打开一个会话
                session = conn.openSession();
                //执行命令
                session.execCommand(cmd);
                result = processStdout(session.getStdout(), DEFAULTCHART);

                if (StringUtils.isBlank(result)){
                    result = processStdout(session.getStderr(), DEFAULTCHART);
                }
                conn.close();
                session.close();
            }
        } catch (IOException e) {
            throw new Exception("命令执行失败",e);
        } finally {
            if (conn != null){
                conn.close();
            }
            if (session != null){
                session.close();
            }
        }
        return result;
    }

    /**
     * 解析脚本执行返回的结果集
     * @param in
     * @param charset
     * @return
     * @throws Exception
     */
    private String processStdout(InputStream in, String charset) throws Exception {
        InputStream stdout = new StreamGobbler(in);
        StringBuffer buffer = new StringBuffer();
        InputStreamReader isr = null;
        BufferedReader br = null;

        try {
            isr = new InputStreamReader(stdout, charset);
            br = new BufferedReader(isr);
            String line =null;
            while ((line = br.readLine()) != null){
                buffer.append(line + "\n");
            }
        } catch (UnsupportedEncodingException e) {
            throw new Exception("不支持的编码字符集异常", e);
        } catch (IOException e) {
            throw new Exception("读取指纹失败", e);
        }finally {
            IOUtils.close(br);
            IOUtils.close(isr);
            IOUtils.close(stdout);
        }

        return buffer.toString();
    }

    /**###########################################################################################################*/

    /**
     * 获取各服务运行进程数
     * @param
     * @return
     * @throws Exception
     */
    public Map<String,Integer> execute_num() throws Exception {
        Map<String,Integer> resultMap = new HashMap<>();
        Session session = null;

        try {
            if (login()){
                //循环体
                for (OSShellCommand com : OSShellCommand.values()) {
                    Integer num = 0;
                    //打开一个会话
                    session = conn.openSession();
                    //执行命令
                    session.execCommand(com.getCommand());
                    num = processNum(session.getStdout(), DEFAULTCHART);

                    resultMap.put(com.getOsFlag(),num);
                }

                conn.close();
                session.close();

            }
        } catch (IOException e) {
            throw new Exception("命令执行失败",e);
        } finally {
            if (conn != null){
                conn.close();
            }
            if (session != null){
                session.close();
            }
        }

        return resultMap;
    }

    /**
     * 解析脚本执行返回的进程数
     * @param in
     * @param charset
     * @return
     * @throws Exception
     */
    private Integer processNum(InputStream in, String charset) throws Exception {
        int num = 0;

        InputStream stdout = new StreamGobbler(in);
        InputStreamReader isr = null;
        BufferedReader br = null;

        try {
            isr = new InputStreamReader(stdout, charset);
            br = new BufferedReader(isr);
            while (br.readLine() != null){
                // buffer.append(line + "\n");
                num += 1;
            }
        } catch (UnsupportedEncodingException e) {
            throw new Exception("不支持的编码字符集异常", e);
        } catch (IOException e) {
            throw new Exception("读取指纹失败", e);
        }finally {
            IOUtils.close(br);
            IOUtils.close(isr);
            IOUtils.close(stdout);
        }

        return num;
    }
}
