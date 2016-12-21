package com.don.bigdata.hive;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hive.service.auth.PasswdAuthenticationProvider;

import javax.security.sasl.AuthenticationException;

/**
 * Created by Administrator on 2016/12/1 0001.
 */
public class HiveServer2PasswdSecurity implements PasswdAuthenticationProvider,Configurable {
    private static final Log LOG = LogFactory.getLog(HiveServer2PasswdSecurity.class );
    private Configuration conf = null;
    private static final String HIVE_SERVER2_AUTH_PREFIX="hive.jdbc_passwd.auth.%s";

    @Override
    public void Authenticate(String user, String passwd)throws AuthenticationException {

        String pass = getConf().get( String.format( HIVE_SERVER2_AUTH_PREFIX , user) );

        if( pass == null || !pass.equals( passwd ) ){
            throw new AuthenticationException( "用户登录HIVESERVER2验证失败 ！" );
        }
    }

    @Override
    public Configuration getConf() {

        return conf;
    }

    @Override
    public void setConf(Configuration conf) {
        this.conf = conf;

    }
}
