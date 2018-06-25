package com.dsg.common.conf;

import com.dsg.common.utils.StringUtils;

import java.util.concurrent.TimeUnit;


/**
 * Description���̳��� Hadoop {@link Configuration}���ڲ�ö��{@link ConfVars} �о���������Ҫ���������Ĭ��ֵ��
 * �������ʱֻ��Ҫ�޸�ö�ٵĵڶ���ֵ��
 *  <p/>
 * 
 * Project�� seagull 
 * <p/>
 * Version: Session V 0.0, 2017/11/17
 * @author: <a href="mailto liuyuanyuan@lvmama.com">Liu Yuanyuan</a>
 *    Created on 11/20/17  10:47:12<br/> 
 * 
 */
public class DSGConfig extends Configuration {
    public static DSGConfig conf = null;

//    static{
//        PropertyConfigurator.configure(Constants.SEAGULL_CONG_DIR + "log4j.properties");
//    }

    private DSGConfig() {
        super();
    }

    public synchronized static DSGConfig getConf() {
        if (null == conf) {
            conf = new DSGConfig();

            conf.addResource( "dsg-site.xml");

//            System.out.println( System.getProperty("project.configuration"));
            return conf;
        } else {
            return conf;
        }
    }

//    System.getProperty("user.dir") + File.separator + "conf" + File.separator
//                + "log4j.properties"
    /**
     * Description��������Ҫ��������ö�٣���һ��ֵΪ�������  {@code key},�ڶ���ֵΪ���õľ���ֵ��������ֵΪ���������������
     * �����Ӧ���������������ļ���û��ָ������ֵΪ��ʱ������ȡö���е�Ĭ��ֵ��
     *  <br/> 
     * Created on 2017/11/17��Project�� seagull�� <br/> 
     * @author: <a href="mailto liuyuanyuan@lvmama.com">Liu Yuanyuan</a>
     * @version: DSGConfig.ConfVars V 0.0, 2017/11/17 11:47:12
     * <br/>
     */
    public static enum ConfVars {
//com.mysql.jdbc.Driver
        /** ĳЩ��Դ�������ڴ���������ʱ�䣬������Щʱ��û�б����ʻ��߱�ʹ�ã����ᱻ��������������Ŀ��hive�ӿڵ�Session,Ԫ���ݿ��JDBC����ᱻ������һ����Դ������ */
        RESOURCE_LIMIT_UNUSEDTIME("com.dsglyy.common.resource.aliveTime", "600s",
        		"ĳЩ��Դ�������ڴ���������ʱ�䣬������Щʱ��û�б����ʻ��߱�ʹ�ã����ᱻ�������" +
                        "�������Ŀ��hive�ӿڵ�Session,Ԫ���ݿ��JDBC����ᱻ������һ����Դ������"),

        /** ��Ŀ����һ����̨�߳���������Դ��������Դ����Ļ�ȡ��������������������Դ����Ĵ���������������̵߳Ĺ������ʱ�䣬Ĭ��ÿ��10�빤��һ�� */
        RESOURCE_CLEANER_RUN_INTERVAL("com.dsglyy.common.resource.cleaner.runInterval", "10s",
        		"��Ŀ����һ����̨�߳���������Դ��������Դ����" +
                        "�Ļ�ȡ��������������������Դ����Ĵ���������������̵߳Ĺ������ʱ�䣬Ĭ��ÿ��10�빤��һ��"),
        DATABASE_USER("javax.jdo.option.ConnectionUserName", "bigdata_admin", "��ϵ�����ݿ�����û���, (����mysql�� oracle )"),
        DATABASE_PASSWORD("javax.jdo.option.ConnectionPassword", "BIGdata12345688", "��ϵ�����ݿ�����û����룬 (����mysql�� oracle )"),
        DATABASE_HOST("javax.jdo.option.host", "10.17.1.7", "��ϵ�����ݿ�HOST�� (����mysql�� oracle )"),
        DATABASE_PORT("javax.jdo.option.port", 1433, "��ϵ�����ݿ�PORT�� (����mysql�� oracle )"),
        DATABASE_DRIVER("javax.jdo.option.ConnectionDriverName", "com.microsoft.sqlserver.jdbc.SQLServerDriver", "��ϵ�����ݿ�����CLASS�� (����mysql�� oracle )"),
        HIVE_SERVER2_PORT("hive.server2.thrift.port", 10000, "hive server2�ķ��ʶ˿�"),
        HIVE_SERVER2_HOST("hive.server2.thrift.bind.host", "10.201.4.51", "hive server2������"),
        HIVE_SERVER2_USER("hive.server2.thrift.client.user", "deploy_man", "hive server2�ͻ��˷����û�"),
        HIVE_SERVER2_PASSWORD("hive.server2.thrift.client.password", "", "hive server2�ͻ��˷����û�����"),
        HIVE_SERVER2_DRIVWE("hive.server2.thrift.jdbc.ConnectionDriverName", "org.apache.hive.jdbc.HiveDriver", "hive server2�ͻ��˵�jdbc����")
        ;
//  hive.server2.thrift.port
//  hive.server2.thrift.bind.host

        public final String varname;
        public final String defaultStrVal;
        public final String description;
        public final boolean defaultBoolVal;
        public final long defaultLonglVal;
        public final int defaultIntVal;
        public final float defaultFloatVal;

        ConfVars(String varname, boolean defaultBoolVal, String description) {
            this(varname, "", -1l, defaultBoolVal, -1, -1.0f, description);
        }

        ConfVars(String varname, String defaultStrVal, String description) {
            this(varname, defaultStrVal, -1l, false, -1, -1.0f, description);
        }

        ConfVars(String varname, long defaultLonglVal, String description) {
            this(varname, "", defaultLonglVal, false, -1, -1.0f, description);
        }

        ConfVars(String varname, int defaultIntVal, String description) {
            this(varname, "", -1l, false, defaultIntVal, -1.0f, description);
        }

        ConfVars(String varname, float defaultFloatVal, String description) {
            this(varname, "", -1l, false, -1, defaultFloatVal, description);
        }

        ConfVars(String varname, String defaultStrVal, long defaultLonglVal, boolean defaultBoolVal, int defaultIntVal, float defaultFloatVal, String description) {
            this.defaultBoolVal = defaultBoolVal;
            this.defaultLonglVal = defaultLonglVal;
            this.defaultStrVal = defaultStrVal;
            this.description = description;
            this.varname = varname;
            this.defaultIntVal = defaultIntVal;
            this.defaultFloatVal = defaultFloatVal;
        }

        @Override
        public String toString() {
            return varname;
        }

    }


    public static long multiplierFor(String unit) {
        unit = unit.trim().toLowerCase();
        if (unit.isEmpty() || unit.equals("b") || unit.equals("bytes")) {
            return 1;
        } else if (unit.equals("kb") || unit.equals("k")) {
            return 1024;
        } else if (unit.equals("mb") || unit.equals("m")) {
            return 1024 * 1024;
        } else if (unit.equals("gb") || unit.equals("g")) {
            return 1024 * 1024 * 1024;
        } else if (unit.equals("tb") || unit.equals("t")) {
            return 1024 * 1024 * 1024 * 1024;
        } else if (unit.equals("pb") || unit.equals("p")) {
            return 1024 * 1024 * 1024 * 1024 * 1024;
        }
        throw new IllegalArgumentException("Invalid size unit " + unit);
    }

    /**
     * Description: ��ȡ�����ļ��е����ݴ�С��������ã������ļ���֧�� {@code k,m,g,y,p,kb,mb,gb,tb,pb} �����ִ�Сд������
     * <br/> 
     * Liu Yuanyuan Nov 24, 2017  <br/> 
     * <br/> 
     *
     * @param var ��Ӧ�������{@link ConfVars}��
     * @return long�ͣ��Ὣ�������ֵת��Ϊ�ֽ������ء�
     *
     */
    public static long getSizeVar(ConfVars var) {
        return toSizeBytes(getVar(var));
    }


    public static int getIntVar(ConfVars var) {
        return getConf().getInt(var.varname, var.defaultIntVal);
    }

    public static String getVar(ConfVars var) {
        return getConf().get(var.varname, getConf().get(var.varname)) == null ?
                var.defaultStrVal : getConf().get(var.varname);
    }

    public static long toSizeBytes(String value) {
        String[] parsed = parseNumberFollowedByUnit(value.trim());
        return Long.parseLong(parsed[0].trim()) * multiplierFor(parsed[1].trim());
    }

    public static float getFloatVar(ConfVars var) {
        return getConf().getFloat(var.varname, var.defaultFloatVal);
    }


    private static String[] parseNumberFollowedByUnit(String value) {
        char[] chars = value.toCharArray();
        int i = 0;
        for (; i < chars.length && (chars[i] == '-' || Character.isDigit(chars[i])); i++) {
        }
        return new String[]{value.substring(0, i), value.substring(i)};
    }

    public static long getLongVar(ConfVars var) {
        return getConf().getLong(var.varname, var.defaultLonglVal);
    }


    public static boolean getBoolVar(ConfVars var) {
        return getConf().getBoolean(var.varname, var.defaultBoolVal);
    }


    public static String[] getTrimmedStringsVar(ConfVars var) {
        String[] result = getConf().getTrimmedStrings(var.varname, (String[]) null);
        if (result != null) return result;
        return StringUtils.getTrimmedStrings(var.defaultStrVal);
    }

    public static int getTTLTime(ConfVars var) {
        long time = getTimeVar(var, TimeUnit.SECONDS);
        long Interval = getTimeVar(ConfVars.RESOURCE_CLEANER_RUN_INTERVAL,TimeUnit.SECONDS);
        if ((time % Interval) > 0) return (int) (time / Interval + 1);
        return (int) (time / Interval);
    }

    public static String stringFor(TimeUnit timeunit) {
        switch (timeunit) {
            case DAYS: return "day";
            case HOURS: return "hour";
            case MINUTES: return "min";
            case SECONDS: return "sec";
            case MILLISECONDS: return "msec";
            case MICROSECONDS: return "usec";
            case NANOSECONDS: return "nsec";
        }
        throw new IllegalArgumentException("Invalid timeunit " + timeunit);
    }

    public static TimeUnit unitFor(String unit, TimeUnit defaultUnit) {
        unit = unit.trim().toLowerCase();
        if (unit.isEmpty() || unit.equals("l")) {
            if (defaultUnit == null) {
                throw new IllegalArgumentException("Time unit is not specified");
            }
            return defaultUnit;
        } else if (unit.equals("d") || unit.startsWith("day")) {
            return TimeUnit.DAYS;
        } else if (unit.equals("h") || unit.startsWith("hour")) {
            return TimeUnit.HOURS;
        } else if (unit.equals("m") || unit.startsWith("min")) {
            return TimeUnit.MINUTES;
        } else if (unit.equals("s") || unit.startsWith("sec")) {
            return TimeUnit.SECONDS;
        } else if (unit.equals("ms") || unit.startsWith("msec")) {
            return TimeUnit.MILLISECONDS;
        } else if (unit.equals("us") || unit.startsWith("usec")) {
            return TimeUnit.MICROSECONDS;
        } else if (unit.equals("ns") || unit.startsWith("nsec")) {
            return TimeUnit.NANOSECONDS;
        }
        throw new IllegalArgumentException("Invalid time unit " + unit);
    }

    private static TimeUnit getDefaultTimeUnit(ConfVars var) {
        String value = getVar(var);
        String[] parsed = parseNumberFollowedByUnit(value.trim());
        return unitFor(parsed[1].trim(),TimeUnit.SECONDS);
    }

    /**
     * Description:��ȡ�������ļ���ʱ���������ã������ļ���֧�� {@code d,h,m,s,ms,us,ns,day...,hour..., min..., ... }�����ִ�Сд�����á�
     * <br/> 
     * Liu Yuanyuan Nov 24, 2017  <br/> 
     * <br/> 
     *
     * @param var ��Ӧ��������ö�٣�{@link ConfVars}��
     * @param outUnit ö��  {@link TimeUnit} ��ʾ�������ص�ʱ�䵥λ 
     * @return long �ͣ����������е�ֵת��Ϊ������ʱ�䵥λ���ء�
     *
     */
    public static long getTimeVar( ConfVars var, TimeUnit outUnit) {
        return toTime(getVar( var), getDefaultTimeUnit(var), outUnit);
    }


    class TimeValidator  {

        private final TimeUnit timeUnit;

        private final Long min;
        private final boolean minInclusive;

        private final Long max;
        private final boolean maxInclusive;

        public TimeValidator(TimeUnit timeUnit) {
            this(timeUnit, null, false, null, false);
        }

        public TimeValidator(TimeUnit timeUnit,
                             Long min, boolean minInclusive, Long max, boolean maxInclusive) {
            this.timeUnit = timeUnit;
            this.min = min;
            this.minInclusive = minInclusive;
            this.max = max;
            this.maxInclusive = maxInclusive;
        }

        public TimeUnit getTimeUnit() {
            return timeUnit;
        }

        public String validate(String value) {
            try {
                long time = DSGConfig.toTime(value, timeUnit, timeUnit);
                if (min != null && (minInclusive ? time < min : time <= min)) {
                    return value + " is smaller than " + timeString(min);
                }
                if (max != null && (maxInclusive ? time > max : time >= max)) {
                    return value + " is bigger than " + timeString(max);
                }
            } catch (Exception e) {
                return e.toString();
            }
            return null;
        }


        private String timeString(long time) {
            return time + " " + DSGConfig.stringFor(timeUnit);
        }
    }


    public void setTimeVar(ConfVars var, long time, TimeUnit outUnit) {
        setTimeVar( var, time, outUnit);
    }


    public static long toTime(String value, TimeUnit inputUnit, TimeUnit outUnit) {
        String[] parsed = parseNumberFollowedByUnit(value.trim());
        return outUnit.convert(Long.parseLong(parsed[0].trim()), unitFor(parsed[1].trim(), inputUnit));
    }
}
